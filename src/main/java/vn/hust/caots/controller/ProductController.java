/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.hust.caots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hust.caots.entities.Product;
import vn.hust.caots.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caots
 */

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;


    /* ---------------- GET PRODUCT BY ID ------------------------ */
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        Product product = productService.getProduct(id);
        if (product != null & product.getStatus() == 1) {
            return new ResponseEntity<Object>(product, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("not found Product", HttpStatus.NO_CONTENT);
    }

    /* ---------------- CREATE NEW USER ------------------------ */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        List<Product> products = productService.getAllProduct();
        Map<Integer, Product> mapProduct = new HashMap<>();
        int i = 0;
        for (Product product1 : products) {
            mapProduct.put(i++, product1);
        }
        if (mapProduct.containsKey(product.getId())) {
            return new ResponseEntity<>("Product Already Exit !", HttpStatus.CONFLICT);
        }
        productService.saveProduct(product);
        return new ResponseEntity<>("creat success!", HttpStatus.CREATED);
    }

    /* ---------------- DELETE USER ------------------------ */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        if (product == null || product.getStatus() == 0) {
            return new ResponseEntity<>("Not Found Product", HttpStatus.OK);
        }
        productService.deleteProduct(product);
        return new ResponseEntity<>("Delete!", HttpStatus.OK);
    }

    /* ---------------- SEARCH /PAGINATION------------------------ */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String str,
                                                       @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                       @RequestParam(required = false, value = "total", defaultValue = "8") int total) {
        List<Product> products = productService.searchProduct(str, page, total);
        if (products != null) {
            for (Product product : products) {
                if (product.getStatus() == 1) {
                    return new ResponseEntity<>(products, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /* ---------------- PAGINATION ------------------------ */

    @GetMapping
    public ResponseEntity<List<Product>> getProductLimit(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                         @RequestParam(required = false, value = "total", defaultValue = "8") int total) {
        int totalRecord = productService.getAllProduct().size();
        int totalPages = (int) Math.ceil(totalRecord / total);
        page = (page - 1) * total;
        List<Product> products = productService.getProductLimit(page, total);
        if (products != null) {
            for (Product product : products) {
                if (product.getStatus() == 1) {
                    return new ResponseEntity<>(products, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
