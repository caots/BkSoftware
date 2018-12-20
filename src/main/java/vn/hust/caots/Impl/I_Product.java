package vn.hust.caots.Impl;

import vn.hust.caots.entities.Product;

import java.util.List;

public interface I_Product {
    List<Product> getAllProduct();

    List<Product> getProductLimit(int start, int total);

    Product getProduct(int id);

    void deleteProduct(Product product);

    void updateProduct(Product product);

    void saveProduct(Product product);

    List<Product> searchProduct(String str, int page, int total);
}
