package com.architects.orderService.dto.response;

import com.architects.orderService.dto.request.CartItemDetailsDTO;
import com.architects.orderService.dto.request.ProductDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailsDTO {
    private Long cartId;
    private Long customerId;
    private List<CartItemDetailsDTO> cartItems;
}
