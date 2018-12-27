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
@Table(name = "user")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String name;
    private String address;
    private Byte sex;
    private int phone;
    private String email;
    private String password;
    private String facebookid;
    private String facebooklink;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role_id;
    private int status = 1;

    public User() {

    }

    public User(int id, String name, String address, Byte sex, int phone, String email, String password, String facebookid, String facebooklink, Role role_id, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.facebookid = facebookid;
        this.facebooklink = facebooklink;
        this.role_id = role_id;
        this.status = status;
    }

    public User(String email, String password, Role role_id) {

        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }

    public User(int id, String name, String address, Byte sex, int phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }

    public String getFacebookid() {
        return facebookid;
    }

    public void setFacebooklink(String facebooklink) {
        this.facebooklink = facebooklink;
    }

    public String getFacebooklink() {
        return facebooklink;
    }

    public Role getRole() {
        return role_id;
    }

    public void setRole(Role role_id) {
        this.role_id = role_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int Status) {
        this.status = Status;
    }

}
