/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.sbr.backend.dao.CartLineDAO;
import net.sbr.backend.dao.ProductDAO;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.CartLine;
import net.sbr.backend.dto.Product;
import net.sbr.onlineshopping.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sabar
 */
@Service("cartService")
public class CartService {

    @Autowired
    private CartLineDAO cartLineDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private HttpSession session;

    // return the cart of the user who has logged in
    private Cart getCart() {
        return ((UserModel) session.getAttribute("userModel")).getCart();
    }

    // retuns the entire cart line
    public List<CartLine> getCartLines() {
        return cartLineDAO.list(this.getCart().getId());
    }

    public String updateCartLine(int cartLineId, int count) {
        // fetch the cart line
        CartLine cartLine = cartLineDAO.get(cartLineId);

        if (cartLine == null) {
            return "result=error";
        } else {
            Product product = cartLine.getProduct();
            double oldTotal = cartLine.getTotal();

            if (product.getQuantity() < count) {
                count = product.getQuantity();
            }

            cartLine.setProductCount(count);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setTotal(product.getUnitPrice() * count);

            cartLineDAO.update(cartLine);

            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
            cartLineDAO.updateCart(cart);

            return "result=updated";
        }
    }

    public String deleteCartLine(int cartLineId) {
        // fetch  the cartline
        CartLine cartLine = cartLineDAO.get(cartLineId);
        if (cartLine == null) {
            return "result=error";
        } else {
            // update the cart
            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
            cart.setCartLines(cart.getCartLines() - 1);
            cartLineDAO.updateCart(cart);

            // remove the cart line
            cartLineDAO.remove(cartLine);

            return "result=deleted";
        }
    }

    public String addCartLine(int productId) {
        String response = null;
        Cart cart = this.getCart();
        CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);

        if (cartLine == null) {
            // add a new cartline
            cartLine = new CartLine();

            // fetch the product
            Product product = productDAO.get(productId);
            cartLine.setCartId(cart.getId());
            cartLine.setProduct(product);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setProductCount(1);
            cartLine.setTotal(product.getUnitPrice());
            cartLine.setAvailable(true);

            cartLineDAO.add(cartLine);

            cart.setCartLines(cart.getCartLines() + 1);
            cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
            cartLineDAO.update(cartLine);

            return "result=added";
        }

        return response;
    }

    public String validateCartLine() {
        Cart cart = this.getCart();
        List<CartLine> cartLines = cartLineDAO.list(cart.getId());
        double grandTotal = 0.0;
        int lineCount = 0;
        String response = "result=success";
        boolean changed = false;
        Product product = null;
        for (CartLine cartLine : cartLines) {
            product = cartLine.getProduct();
            changed = false;
            // check if the product is active or not
            // if it is not active make the availability of cartLine as false
            if ((!product.isActive() && product.getQuantity() == 0) && cartLine.isAvailable()) {
                cartLine.setAvailable(false);
                changed = true;
            }
            // check if the cartLine is not available
            // check whether the product is active and has at least one quantity available
            if ((product.isActive() && product.getQuantity() > 0) && !(cartLine.isAvailable())) {
                cartLine.setAvailable(true);
                changed = true;
            }

            // check if the buying price of product has been changed
            if (cartLine.getBuyingPrice() != product.getUnitPrice()) {
                // set the buying price to the new price
                cartLine.setBuyingPrice(product.getUnitPrice());
                // calculate and set the new total
                cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
                changed = true;
            }

            // check if that much quantity of product is available or not
            if (cartLine.getProductCount() > product.getQuantity()) {
                cartLine.setProductCount(product.getQuantity());
                cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
                changed = true;

            }

            // changes has happened
            if (changed) {
                //update the cartLine
                cartLineDAO.update(cartLine);
                // set the result as modified
                response = "result=modified";
            }

            grandTotal += cartLine.getTotal();
            lineCount++;
        }

        cart.setCartLines(lineCount++);
        cart.setGrandTotal(grandTotal);
        cartLineDAO.updateCart(cart);

        return response;
    }
}
