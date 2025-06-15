package com.example.hoan_thien_gio_hang.service;

import com.example.hoan_thien_gio_hang.model.CartItem;
import com.example.hoan_thien_gio_hang.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService implements ICartService{
    private final Map<Long, CartItem> cartItems = new HashMap<>();

    @Override
    public void addToCart(Product product) {
        cartItems.compute(product.getId(), (id, existingItem) -> {
            if (existingItem == null)
                return new CartItem(product, 1);
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            return existingItem;
        });
    }

    @Override
    public void updateCartItem(Long productId, int quantity) {
        if (cartItems.containsKey(productId)) {
            cartItems.get(productId).setQuantity(quantity);
        }
    }

    @Override
    public void removeFromCart(Long productId) {
        cartItems.remove(productId);
    }

    @Override
    public Map<Long, CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public double getTotal() {
        return cartItems.values().stream()
                .mapToInt(item -> (int) (item.getProduct().getPrice() * item.getQuantity()))
                .sum();
    }

    @Override
    public void clearCart() {
        cartItems.clear();
    }
}
