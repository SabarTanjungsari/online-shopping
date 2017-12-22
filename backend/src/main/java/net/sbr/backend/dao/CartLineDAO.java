/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.backend.dao;

import java.util.List;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.CartLine;

/**
 *
 * @author sabar
 */
public interface CartLineDAO {

    public CartLine get(int id);

    public boolean add(CartLine cartLine);

    public boolean update(CartLine cartLine);

    public boolean delete(CartLine cartLine);

    public List<CartLine> list(int cartId);

    public List<CartLine> listAvailable(int cartId);

    public CartLine getByCartAndProduct(int cartId, int productId);

    boolean updateCart(Cart cart);
}
