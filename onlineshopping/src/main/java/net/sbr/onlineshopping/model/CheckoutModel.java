/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.model;

import java.io.Serializable;
import java.util.List;
import net.sbr.backend.dto.Address;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.CartLine;
import net.sbr.backend.dto.OrderDetail;
import net.sbr.backend.dto.User;

/**
 *
 * @author sabar
 */
public class CheckoutModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private User user;
    private Address shipping;
    private Cart cart;
    private List<CartLine> cartLines;
    private OrderDetail orderDetail;
    private double checkoutTotal;

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getCheckoutTotal() {
        return checkoutTotal;
    }

    public void setCheckoutTotal(double checkoutTotal) {
        this.checkoutTotal = checkoutTotal;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShipping() {
        return shipping;
    }

    public void setShipping(Address shipping) {
        this.shipping = shipping;
    }

}
