/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.hust.caots.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author caots
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    private int id;
    private String name;
    private float price;
    private String descripton;
    private String time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private TypeProduct typeProduct;

    private int status;

    public Product() {

    }

    public Product(int id, String name, float price, String descripton, String time, Image image, TypeProduct typeProduct, int status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.descripton = descripton;
        this.time = time;
        this.image = image;
        this.typeProduct = typeProduct;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
