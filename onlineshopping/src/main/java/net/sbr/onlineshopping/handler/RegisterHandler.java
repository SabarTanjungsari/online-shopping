/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.handler;

import net.sbr.backend.dao.UserDAO;
import net.sbr.backend.dto.Address;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.User;
import net.sbr.onlineshopping.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author sabar
 */
@Component
public class RegisterHandler {

    @Autowired
    private UserDAO userDAO;

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String validateUser(User user, MessageContext error) {
        String transitionValue = "success";

        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Password does not match the confirm password!")
                    .build());
            transitionValue = "failure";
        }

        if (userDAO.getByEmail(user.getEmail()) != null) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Email address is already used!")
                    .build());
            transitionValue = "failure";
        }

        return transitionValue;
    }

    public String saveAll(RegisterModel model) {
        String transitionValue = "success";

        // fetch the user
        User user = model.getUser();

        if (user.getRole().equals("USER")) {
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        userDAO.addUser(user);

        // get the address
        Address billing = model.getBilling();
        billing.setUser(user);
        billing.setBilling(true);
        userDAO.addAddress(billing);

        return transitionValue;
    }
}
