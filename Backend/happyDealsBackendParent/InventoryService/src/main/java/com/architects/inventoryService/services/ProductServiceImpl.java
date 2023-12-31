package com.architects.inventoryService.services;

import com.architects.inventoryService.Repositories.ProductCategoryRepository;
import com.architects.inventoryService.Repositories.ProductRepository;
import com.architects.inventoryService.dto.response.OrderDTO;
import com.architects.inventoryService.dto.request.RequestProductDto;
import com.architects.inventoryService.dto.response.OrderConfirmDTO;
import com.architects.inventoryService.dto.response.OrderResponseDTO;
import com.architects.inventoryService.dto.response.ResponseProductDto;
import com.architects.inventoryService.dto.response.ProductDetailsDTO;
import com.architects.inventoryService.entity.Product;

import com.architects.inventoryService.exception.RestException;
import com.architects.inventoryService.entity.ProductCategory;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    private final WebClient.Builder webClientBuilder;

    public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public void createProduct(RequestProductDto product) {
        UUID uuid = UUID.randomUUID();
        Long productID = uuid.getMostSignificantBits() & Long.MAX_VALUE;

        // Retrieve ProductCategory from the repository based on the provided category ID
        ProductCategory productCategory = productCategoryRepository.findById(product.getProductCategoryId())
                .orElseThrow(() -> new RuntimeException("Product category not found with ID: " + product.getProductCategoryId()));
        Product p = new Product(
                productID,
                product.getProductName(),
                product.getProductDescription(),
                product.getProductUnitPrice(),
                product.getProductQuantityAvailable(),
                product.getProductDiscount(),
                productCategory

        );
        productRepository.save(p);
    }

    // Update Product by productId
    public void updateProduct(Long productId, RequestProductDto updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductDescription(updatedProduct.getProductDescription());
        existingProduct.setProductUnitPrice(updatedProduct.getProductUnitPrice());
        existingProduct.setProductQuantityAvailable(updatedProduct.getProductQuantityAvailable());
        existingProduct.setProductDiscount(updatedProduct.getProductDiscount());

        productRepository.save(existingProduct);
    }

    public void updateProductQuantity(Long productId, BigDecimal updatedQuantity) {

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

        System.out.println("Updated Quantity: " + updatedQuantity);
        existingProduct.setProductQuantityAvailable(updatedQuantity);

        productRepository.save(existingProduct);
    }

    // Delete Product by productId
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // Retrieve all Products
    public List<ResponseProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ResponseProductDto(
                        p.getProductId(),
                        p.getProductName(),
                        p.getProductDescription(),
                        p.getProductUnitPrice(),
                        p.getProductQuantityAvailable(),
                        p.getProductDiscount(),
                        p.getProductCategory().getProductCategoryId()
                ))
                .collect(Collectors.toList());
    }

    


    // Retrieve Product by productId
    public ResponseProductDto getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return new ResponseProductDto(
                    productOptional.get().getProductId(),
                    productOptional.get().getProductName(),
                    productOptional.get().getProductDescription(),
                    productOptional.get().getProductUnitPrice(),
                    productOptional.get().getProductQuantityAvailable(),
                    productOptional.get().getProductDiscount(),
                    productOptional.get().getProductCategory().getProductCategoryId()
            );
        } else {
            throw new EntityNotFoundException("Product not found with ID: " + productId);
        }
    }

    public List<ResponseProductDto> getProductByName(String productName){
        List<Product> products = productRepository.findByProductName(productName);

        return products.stream()
                .map(product -> new ResponseProductDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getProductUnitPrice(),
                        product.getProductQuantityAvailable(),
                        product.getProductDiscount(),
                        product.getProductCategory().getProductCategoryId()
                ))
                .collect(Collectors.toList());
    }

    public List<ResponseProductDto> getAllAvailableProducts() {
        return productRepository.findAll().stream()
                .filter(p -> p.getProductQuantityAvailable().compareTo(BigDecimal.ZERO) > 0)
                .map(p -> new ResponseProductDto(
                        p.getProductId(),
                        p.getProductName(),
                        p.getProductDescription(),
                        p.getProductUnitPrice(),
                        p.getProductQuantityAvailable(),
                        p.getProductDiscount(),
                        p.getProductCategory().getProductCategoryId()
                ))
                .collect(Collectors.toList());

}
    public List<ResponseProductDto> getProductByCategoryId(Long fk_ProductCategory_Id){
        ProductCategory productCategory = productCategoryRepository.findById(fk_ProductCategory_Id)
                .orElseThrow(() -> new EntityNotFoundException("ProductCategory not found"));

        List<Product> products = productRepository.findByProductCategory(productCategory);

        return products.stream()
                .map(product -> new ResponseProductDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getProductUnitPrice(),
                        product.getProductQuantityAvailable(),
                        product.getProductDiscount(),
                        product.getProductCategory() != null ? product.getProductCategory().getProductCategoryId() : null
                ))
                .collect(Collectors.toList());
    }

    public ProductDetailsDTO getProductDetailsById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
        return ProductDetailsDTO.builder()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productUnitPrice(product.getProductUnitPrice())
                .productQuantityAvailable(product.getProductQuantityAvailable())
                .productDiscount(product.getProductDiscount())
                .build();
    }


    public OrderResponseDTO placeOrder(List<OrderDTO> products) {
        OrderResponseDTO response = new OrderResponseDTO();
        List<Long> productsWithInsufficientStock = new ArrayList<>();
        List<OrderConfirmDTO> productsWithSufficientStock = new ArrayList<>();
        List<Product> inventoryToUpdate = new ArrayList<>();


        for (OrderDTO product : products) {
            Long productId = product.getProductId();
            Product productItem = productRepository.findById(productId).orElse(null);

            if (productItem == null) {
                throw new RestException(HttpStatus.NOT_FOUND, "Product not found");
            } else if (productItem.getProductQuantityAvailable().compareTo(product.getQuantityOrdered()) >= 0) {
                BigDecimal newQuantity = productItem.getProductQuantityAvailable().subtract(product.getQuantityOrdered());
                productItem.setProductQuantityAvailable(newQuantity);
                inventoryToUpdate.add(productItem);
                productsWithSufficientStock.add(mapToOrderConfirmDTO(productItem, product.getQuantityOrdered()));
            } else {
                productsWithInsufficientStock.add(product.getProductId());
            }
        }

        if (!productsWithInsufficientStock.isEmpty()) {
            response.setSuccess(false);
            response.setFailedProducts(productsWithInsufficientStock);
        } else {
            productRepository.saveAll(inventoryToUpdate);
            response.setSuccess(true);
            response.setProducts(productsWithSufficientStock);
        }

        return response;
    }

    private OrderConfirmDTO mapToOrderConfirmDTO(Product inventory, BigDecimal quantityOrdered) {
        return OrderConfirmDTO.builder()
                .productId(inventory.getProductId())
                .quantityOrdered(quantityOrdered)
                .productUnitPrice(inventory.getProductUnitPrice())
                .productName(inventory.getProductName())
                .productDiscount(inventory.getProductDiscount())
                .build();
    }


    public boolean cancelOrder(List<OrderDTO> products) {
        try {
            List<Product> inventoryToUpdate = new ArrayList<>();

            for (OrderDTO product : products) {
                Long productId = product.getProductId();
//                try {
//                    productId = new ObjectId(product.getProductId());
//                }catch(Exception e){
//                    continue;
//                }
                Product productItem = productRepository.findById(productId).orElse(null);
                if (productItem != null) {
                    productItem.setProductQuantityAvailable(productItem.getProductQuantityAvailable().add(product.getQuantityOrdered()));
                    inventoryToUpdate.add(productItem);
                }
            }
            productRepository.saveAll(inventoryToUpdate);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
