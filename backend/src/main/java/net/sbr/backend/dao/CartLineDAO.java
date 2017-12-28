/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.backend.dao;

import java.util.List;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.CartLine;
import net.sbr.backend.dto.OrderDetail;

/**
 *
 * @author sabar
 */
public interface CartLineDAO {

    public List<CartLine> list(int cartId);

    public CartLine get(int id);

    public boolean add(CartLine cartLine);

    public boolean update(CartLine cartLine);

    public boolean remove(CartLine cartLine);

    // fetch the CartLine based on cartId and productId
    public CartLine getByCartAndProduct(int cartId, int productId);

    // updating the cart
    boolean updateCart(Cart cart);

    // list of available cartLine
    public List<CartLine> listAvailable(int cartId);

    // adding order details
    boolean addOrderDetail(OrderDetail orderDetail);

}
