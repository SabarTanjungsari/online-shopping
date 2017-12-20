/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.model;

import java.io.Serializable;
import net.sbr.backend.dto.Cart;

/**
 *
 * @author sabar
 */
public class UserModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String fullName;
    private String email;
    private String role;
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "UserModel{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", role=" + role + ", cart=" + cart + '}';
    }
    
}
