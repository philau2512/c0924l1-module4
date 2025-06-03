package com.example.product_management.repository;

import com.example.product_management.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRepository implements IProductRepository {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

//    private static List<Product> productList = new ArrayList<>();
//
//    static {
//        productList.add(new Product(1, "Iphone 12", 12000000, "New", "Apple"));
//        productList.add(new Product(2, "Samsung", 13000000, "LikeNew", "Samsung"));
//        productList.add(new Product(3, "Iphone 14", 14000000, "Old", "Apple"));
//        productList.add(new Product(4, "Iphone 15", 15000000, "Fake", "China"));
//        productList.add(new Product(5, "Iphone 16", 16000000, "Used", "Balan"));
//    }

    @Override
    public List<Product> getAllProducts() {
        String queryStr = "select p from Product as p";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        return query.getResultList();
    }

    @Override
    public Product getProductById(int id) {
        String queryStr = "select p from Product as p where p.id = :id";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        Product origin;
        if (product.getId() != 0) {
            origin = new Product();
        } else {
            origin = getProductById(product.getId());
        }
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            origin.setName(product.getName());
            origin.setPrice(product.getPrice());
            origin.setDescription(product.getDescription());
            origin.setManufacturer(product.getManufacturer());
            session.saveOrUpdate(origin);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Error when addProduct: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void updateProduct(Product product) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Product existingProduct = getProductById(product.getId());
            if (existingProduct != null) {
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setManufacturer(product.getManufacturer());
                session.update(existingProduct);
                transaction.commit();
            }
            session.close();
        } catch (Exception e) {
            System.out.println("Error when updateProduct: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void deleteProduct(int id) {
        Product product = getProductById(id);
        if (product != null) {
            Transaction transaction = null;
            try {
                Session session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.delete(product);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                System.out.println("Error when deleteProduct: " + e.getMessage());
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
