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
                case "updated":
                    mv.addObject("message", "Cartline has been updated successfully!");
                    break;
                case "deleted":
                    mv.addObject("message", "Cartline has been deleted successfully!");
                    break;
                case "added":
                    mv.addObject("message", "Cartline has been added successfully!");
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
}
