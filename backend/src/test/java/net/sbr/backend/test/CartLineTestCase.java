/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.backend.test;

import net.sbr.backend.dao.CartLineDAO;
import net.sbr.backend.dao.ProductDAO;
import net.sbr.backend.dao.UserDAO;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.CartLine;
import net.sbr.backend.dto.Product;
import net.sbr.backend.dto.User;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author sabar
 */
public class CartLineTestCase {

    private static AnnotationConfigApplicationContext context = null;

    private static CartLineDAO cartLineDAO = null;
    private static ProductDAO productDAO = null;
    private static UserDAO userDAO = null;

    private Product product = null;
    private User user = null;
    private Cart cart = null;
    private CartLine cartLine = null;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("net.sbr.backend");
        context.refresh();
        productDAO = (ProductDAO)context.getBean("productDAO");
        userDAO = (UserDAO)context.getBean("userDAO");
        cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
    }

    /*@Test
    public void testAddNewCartLine(){
        
        // 1. get the user
        user = userDAO.getByEmail("fauzan@gmail.com");
        
        // 2. fetch the cart
        cart = user.getCart();
        
        // 3. get the product
        product = productDAO.get(1);
        
        // 4. create a new cartline
        cartLine = new CartLine();
        cartLine.setBuyingPrice(product.getUnitPrice());
        cartLine.setProductCount(cartLine.getProductCount() + 1);
        cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
        cartLine.setCartId(cart.getId());
        cartLine.setProduct(product);
        
        assertEquals("Failed to add the cartline", true, cartLineDAO.add(cartLine));
        
        // update cart
        cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
        cart.setCartLines(cart.getCartLines() + 1);
        
         assertEquals("Failed to update the cart", true, cartLineDAO.updateCart(cart));
    }*/
}
