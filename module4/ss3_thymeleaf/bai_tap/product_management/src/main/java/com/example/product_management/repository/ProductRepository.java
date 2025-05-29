package com.example.product_management.repository;

import com.example.product_management.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRepository implements IProductRepository {
    private static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1, "Iphone 12", 12000000, "New", "Apple"));
        productList.add(new Product(2, "Samsung", 13000000, "LikeNew", "Samsung"));
        productList.add(new Product(3, "Iphone 14", 14000000, "Old", "Apple"));
        productList.add(new Product(4, "Iphone 15", 15000000, "Fake", "China"));
        productList.add(new Product(5, "Iphone 16", 16000000, "Used", "Balan"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == product.getId()) {
                productList.set(i, product);
            }
        }
    }

    @Override
    public void deleteProduct(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.remove(i);
            }
        }
    }
}
