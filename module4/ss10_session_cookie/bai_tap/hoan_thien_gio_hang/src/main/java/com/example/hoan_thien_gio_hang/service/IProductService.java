package com.example.hoan_thien_gio_hang.service;

import com.example.hoan_thien_gio_hang.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
