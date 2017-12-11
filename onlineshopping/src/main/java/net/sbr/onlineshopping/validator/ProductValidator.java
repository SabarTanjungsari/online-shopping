/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.validator;

import net.sbr.backend.dto.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author sabar
 */
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        // whether file has been selected or not
        if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
            errors.rejectValue("file", null, "Please select an image file to upload!");
            return;
        }

        if (!(product.getFile().getContentType().equals("image/jpeg")
                || product.getFile().getContentType().equals("image/png")
                || product.getFile().getContentType().equals("image/gif"))) {
            errors.rejectValue("file", null, "Please use only image file for upload!");
            return;
        }
    }

}
