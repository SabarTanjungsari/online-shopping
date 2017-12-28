/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.backend.dao;

import java.util.List;
import net.sbr.backend.dto.Address;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.User;

/**
 *
 * @author sabar
 */
public interface UserDAO {
    
    // user related operation
    User getByEmail(String email);
    User get(int id);
    
    boolean addUser(User user);
    
    // adding and updating a new address
    Address getAddress(int addressId);
    boolean addAddress(Address address);
    boolean updateAddress(Address address);    
    Address getBillingAddress(User user);
    List<Address> listShippingAddress(User user);
    
    boolean updateCart(Cart cart);
}
