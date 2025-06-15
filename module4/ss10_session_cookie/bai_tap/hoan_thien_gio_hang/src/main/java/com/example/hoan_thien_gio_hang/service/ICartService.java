package com.example.hoan_thien_gio_hang.service;

import com.example.hoan_thien_gio_hang.model.CartItem;
import com.example.hoan_thien_gio_hang.model.Product;

import java.util.Map;

public interface ICartService {
    void addToCart(Product product);

    void updateCartItem(Long productId, int quantity);

    void removeFromCart(Long productId);

    Map<Long, CartItem> getCartItems();

    double getTotal();
    void clearCart();
}