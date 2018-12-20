/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.hust.caots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.caots.dao.ProductDao;
import vn.hust.caots.Impl.I_Product;
import vn.hust.caots.entities.Product;

/**
 * @author caots
 */
@Service
public class ProductService implements I_Product {

    @Autowired
    ProductDao productDao;

    @Override
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Override
    public List<Product> getProductLimit(int page, int total) {
        return productDao.getProductLimit(page,total);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public List<Product> searchProduct(String str, int page, int total) {
        return productDao.searchProduct(str,page,total );
    }


}
