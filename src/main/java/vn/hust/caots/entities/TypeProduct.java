/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.hust.caots.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author caots
 */
@Entity
@Table(name = "typeproduct")
public class TypeProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int status ;

    public TypeProduct() {

    }

    public TypeProduct(int id,String type, Menu menu, int status) {
        this.id = id;
        this.type = type;
        this.menu = menu;
        this.status = status;
    }

    public TypeProduct(int id, String type, int status) {
        this.id = id;
        this.type = type;
        this.status= status;
    }

    public Menu getMenu_id() {
        return menu;
    }

    public void setMenu_id(Menu menu_id) {
        this.menu = menu_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status=status;
    }

    public int getStatus() {
        return status;
    }
}
