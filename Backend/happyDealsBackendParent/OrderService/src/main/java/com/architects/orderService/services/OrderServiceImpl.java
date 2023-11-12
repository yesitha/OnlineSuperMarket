package com.architects.orderService.services;

import com.architects.orderService.Repositories.CartItemRepository;
import com.architects.orderService.Repositories.CartRepository;
import com.architects.orderService.Repositories.OrderRepository;
import com.architects.orderService.dto.request.OrderItemDTO;
import com.architects.orderService.dto.request.OrderRequestDTO;
import com.architects.orderService.dto.response.*;
import com.architects.orderService.entity.Cart;
import com.architects.orderService.entity.Order;
import com.architects.orderService.entity.OrderItem;
import com.architects.orderService.entity.OrderStatus;
import com.architects.orderService.exception.RestException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final WebClient.Builder webClientBuilder;


    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;

        this.webClientBuilder = webClientBuilder;
    }

    public void assignDeliveryPersonToOrder(Long orderId, Long deliveryPersonId) {


        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        if (order != null) {
            order.setDeliverPersonId(deliveryPersonId);
            orderRepository.save(order);


            UriTemplate uriTemplate = new UriTemplate("http://delivery-service/api/v1/deliveryPersons/update-delivery-person-status/{deliveryPersonId}/{status}");
            URI uri = uriTemplate.expand(deliveryPersonId, false);

            try {
                webClientBuilder.build().put()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(Void.class)  // Assuming the response is empty
                        .block();


            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public OrderRequestDTO getOrderRequestDTOByCustomerId(Long customerId) {
        // Retrieve the cart details for the given customerId
        Cart cart = cartRepository.findByCustomerId(customerId);

        // Check if the cart is null or empty
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND, "Cart is empty");
        }

        // Map cart items to OrderItemDTO
        List<OrderItemDTO> orderItems = cart.getCartItems().stream()
                .map(cartItem -> new OrderItemDTO(cartItem.getProductId(), cartItem.getQuantityAdded()))
                .collect(Collectors.toList());

        // Create OrderRequestDTO
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setOrderItems(orderItems);

        return orderRequestDTO;
    }

    public OrderResponse placeOrder(Long customerId){
        OrderRequestDTO orderRequestDTO = getOrderRequestDTOByCustomerId(customerId);
        orderRequestDTO.setShippingAddress(" address"+customerId + " address");

        Order order = new Order();

        //Get orderItems details
        List<OrderItem> orderItems = orderRequestDTO.getOrderItems()
                .stream()
                .map(this::mapToEntity)
                .toList();

        order.setCustomerId(customerId);
        order.setOrderItems(orderItems);
        order.setOrderShippingAddress(orderRequestDTO.getShippingAddress());

        //check quantity availability and get product details from inventory service
        InventoryResponseDTO inventoryResponseDTO = webClientBuilder.build()
                .put()
                .uri("http://inventory-service/api/v1/products/place-order")
                .bodyValue(orderItems.stream().map(orderItem -> InventoryConfirmDTO.builder()
                        .productId(orderItem.getProductId())
                        .quantityOrdered(orderItem.getQuantityOrdered())
                        .build()).toList())
                .retrieve()
                .bodyToMono(InventoryResponseDTO.class)
                .onErrorResume(e -> {
                    if(e.getMessage().contains("404")){
                        throw new RestException(HttpStatus.NOT_FOUND, "Product(s) not available");
                    }
                    throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Error connecting to inventory management");
                })
                .block();

        if(inventoryResponseDTO == null){
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Error connecting to inventory management");
        }
        if(!inventoryResponseDTO.isSuccess()){
            String outOfStock = inventoryResponseDTO.getFailedProducts().toString();
            throw new RestException(HttpStatus.NOT_FOUND, "Product(s) out of stock: "+outOfStock);
        }
        for(OrderItem orderItem: orderItems){
            for(InventoryConfirmDTO inventoryConfirmDTO: inventoryResponseDTO.getProducts()){
                if(orderItem.getProductId().equals(inventoryConfirmDTO.getProductId())){
                    orderItem.setOrder(order);
                    orderItem.setProductUnitPrice(inventoryConfirmDTO.getProductUnitPrice());
                    orderItem.setProductName(inventoryConfirmDTO.getProductName());
                    orderItem.setProductDiscount(inventoryConfirmDTO.getProductDiscount());
                    break;
                }
            }
        }

        order.setOrderDate(new Date());
        String orderNumber;
        while(true){
            orderNumber = generateOrderNumber(order,customerId);
            Optional<Order> checkOrder = orderRepository.findByOrderNumber(orderNumber);
            if(checkOrder.isEmpty()){
                break;
            }
        }
        order.setOrderNumber(orderNumber);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderItems(orderItems);
        order.setOrderTotal(orderItems.stream().map(orderItem -> orderItem.getProductUnitPrice().multiply(orderItem.getQuantityOrdered())).reduce(BigDecimal.ZERO, BigDecimal::add));
        this.orderRepository.save(order);

        //clear cart using cartService
        clearProductsInCart(customerId);

        return mapToOrderResponse(order);
    }

    private OrderItem mapToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDTO.getProductId());
        orderItem.setQuantityOrdered(orderItemDTO.getQuantityOrdered());
        return orderItem;
    }

    private String generateOrderNumber(Order order,Long customerId){
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(order.getOrderDate());
        return customerId + "_" + timeStamp + "_" + new Random().nextInt(1001);
    }

    private OrderResponse mapToOrderResponse(Order order){
        return OrderResponse.builder()
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .orderTotal(order.getOrderTotal())
                .orderShippingAddress(order.getOrderShippingAddress())
                .orderShippingCost(order.getOrderShippingCost())
                .orderItems(order.getOrderItems().stream().map(this::mapToOrderItemsDTO).toList())
                .build();
    }

    private OrderItemResponseDTO mapToOrderItemsDTO(OrderItem orderItem){
        return OrderItemResponseDTO.builder()
                .productId(orderItem.getProductId())
                .productName(orderItem.getProductName())
                .productUnitPrice(orderItem.getProductUnitPrice())
                .productDiscount(orderItem.getProductDiscount())
                .quantityOrdered(orderItem.getQuantityOrdered())
                .totalPrice(orderItem.getProductUnitPrice().multiply(orderItem.getQuantityOrdered()))
                .build();
    }

    public void clearProductsInCart(Long customerId) {
        try {
            // Check if a cart already exists for the provided customerId
            if (!cartRepository.existsByCustomerId(customerId)) {
                throw new IllegalStateException("No cart exists for customer with ID: " + customerId);
            }

            // If a cart exists, clear the cartItems
            Cart cart = cartRepository.findByCustomerId(customerId);

            cart.getCartItems().clear();
            cartItemRepository.deleteAllByCart(cart);

            cartRepository.save(cart);

            log.info("Cart {} cleared successfully", cart.getCartId());
//            return cart.getCartId().toString();
        } catch (IllegalStateException e) {
            log.error("Failed to clear cart for customer {}. Reason: {}", customerId, e.getMessage());
            throw e;
        }
    }


    public OrdersResponse getAllByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        if(orders.isEmpty()){
            throw new RestException(HttpStatus.NOT_FOUND, "No orders found");
        }
        return OrdersResponse.builder()
                .orders(orders.stream().map(this::mapToOrderResponse).toList())
                .build();
    }

    public OrderResponse getOrder(String orderNumber, Long customerId) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
        if(order.isEmpty()){
            throw new RestException(HttpStatus.NOT_FOUND, "Order not found");
        }
        System.out.println(customerId);
        if(!order.get().getCustomerId().equals(customerId)){
            throw new RestException(HttpStatus.UNAUTHORIZED, "Unauthorized Access");
        }
        return mapToOrderResponse(order.get());
    }

    public OrderResponse cancelOrder(String orderNumber, Long customerId) {
        Order order = orderRepository.findByOrderNumber(orderNumber).orElse(null);
        if(order == null){
            throw new RestException(HttpStatus.NOT_FOUND, "Order not found");
        }
        if(!order.getCustomerId().equals(customerId)){
            throw new RestException(HttpStatus.UNAUTHORIZED, "Unauthorized Access");
        }
        if(order.getOrderStatus() != OrderStatus.PLACED){
            throw new RestException(HttpStatus.FORBIDDEN, "Order is not cancellable");
        }

        //send details to inventory management to add back the quantities
        String inventoryConfirmation = webClientBuilder.build()
                .put()
                .uri("http://inventory-service/api/v1/products/cancel-order")
                .bodyValue(order.getOrderItems().stream().map(orderItem -> InventoryConfirmDTO.builder()
                        .productId(orderItem.getProductId())
                        .quantityOrdered(orderItem.getQuantityOrdered())
                        .build()).toList())
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Error connecting to inventory management");
                })
                .block();
        if(!Objects.equals(inventoryConfirmation, "Success")){
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong cancelling order");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return mapToOrderResponse(order);
    }


    //ADMIN METHODS
    public OrderResponse getOrder(String orderNumber){
        Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
        if(order.isEmpty()){
            throw new RestException(HttpStatus.NOT_FOUND, "Order not found");
        }
        return mapToOrderResponse(order.get());
    }

    public OrdersResponse getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()){
            throw new RestException(HttpStatus.NOT_FOUND, "No orders found");
        }
        return OrdersResponse.builder()
                .orders(orders.stream().map(this::mapToOrderResponse).toList())
                .build();
    }

    public OrderResponse updateStatusAdmin(String orderNumber){
        Order order = orderRepository.findByOrderNumber(orderNumber).orElse(null);
        if(order == null){
            throw new RestException(HttpStatus.NOT_FOUND, "Order not found");
        }
        if(order.getOrderStatus() == OrderStatus.CANCELLED){
            throw new RestException(HttpStatus.FORBIDDEN, "Order already cancelled");
        }
        if(order.getOrderStatus() != OrderStatus.PLACED){
            throw new RestException(HttpStatus.FORBIDDEN, "Order is already processed");
        }
        order.setOrderStatus(OrderStatus.PROCESSED);
        orderRepository.save(order);
        return mapToOrderResponse(order);
    }
}
