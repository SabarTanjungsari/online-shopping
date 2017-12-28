/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.controller;

import net.sbr.onlineshopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sabar
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
        ModelAndView mv = new ModelAndView("page");

        if (result != null) {
            switch (result) {
                case "added":
                    mv.addObject("message", "Cartline has been added successfully!");
                    break;
                case "unavailable":
                    mv.addObject("message", "Product quantity is not available!");
                    break;
                case "updated":
                    mv.addObject("message", "Cartline has been updated successfully!");
                    cartService.validateCartLine();
                    break;
                case "modified":
                    mv.addObject("message", "One or more items inside cart has been modified!");
                    break;
                case "maximum":
                    mv.addObject("message", "Maximum limit for the item has been reached!");
                    break;
                case "deleted":
                    mv.addObject("message", "Cartline has been deleted successfully!");
                    break;
                case "error":
                    mv.addObject("message", "Something wrong!");
                    break;
            }
        }

        mv.addObject("title", "User Cart");
        mv.addObject("userClickShowCart", true);
        mv.addObject("cartLines", cartService.getCartLines());

        return mv;
    }

    @RequestMapping("/{cartLineId}/update")
    public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {
        String response = cartService.updateCartLine(cartLineId, count);
        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/{cartLineId}/delete")
    public String updateCart(@PathVariable int cartLineId) {
        String response = cartService.deleteCartLine(cartLineId);
        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/add/{productId}/product")
    public String addCart(@PathVariable int productId) {
        String response = cartService.addCartLine(productId);
        return "redirect:/cart/show?" + response;
    }

    /* after validating it redirect to checkout
	 * if result received is success proceed to checkout 
	 * else display the message to the user about the changes in cart page
	 * */
    @RequestMapping("/validate")
    public String validateCart() {
        String response = cartService.validateCartLine();
        if (!response.equals("result=success")) {
            return "redirect:/cart/show?" + response;
        } else {
            return "redirect:/cart/checkout";
        }
    }
}
