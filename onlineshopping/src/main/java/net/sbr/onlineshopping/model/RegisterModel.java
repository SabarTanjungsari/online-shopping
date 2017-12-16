/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.model;

import java.io.Serializable;
import net.sbr.backend.dto.Address;
import net.sbr.backend.dto.User;

/**
 *
 * @author sabar
 */
public class RegisterModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private Address billing;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }

}