/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.controller;

import javax.servlet.http.HttpSession;
import net.sbr.backend.dao.UserDAO;
import net.sbr.backend.dto.User;
import net.sbr.onlineshopping.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author sabar
 */
@ControllerAdvice
public class GlobalController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserDAO userDAO;

    private UserModel userModel = null;

    @ModelAttribute("userModel")
    public UserModel getUserModel() {
        if (session.getAttribute("userModel") == null) {
            // add the user model
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            User user = userDAO.getByEmail(authentication.getName());
            if (user != null) {
                // create a new UserModel object to pass the user detail
                userModel = new UserModel();

                userModel.setId(user.getId());
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getFirstName() + " " + user.getLastName());

                if (userModel.getRole().equals("USER")) {
                    // set the cart only if user is a buyer
                    userModel.setCart(user.getCart());
                }

                // set the usermodel in the session
                session.setAttribute("userModel", userModel);

                return userModel;
            }
        }

        return (UserModel) session.getAttribute("userModel");
    }
}
