package net.sbr.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sbr.backend.dao.CategoryDAO;
import net.sbr.backend.dao.ProductDAO;
import net.sbr.backend.dto.Category;
import net.sbr.backend.dto.Product;
import net.sbr.onlineshopping.util.FileUploadUtility;
import net.sbr.onlineshopping.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("userClickManageProducts", true);
        mv.addObject("title", "Manage Products");
        Product nProduct = new Product();

        // Set a few of the fields
        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        mv.addObject("product", nProduct);

        if (operation != null) {
            if (operation.equals("product")) {
                mv.addObject("message", "Product Submitted Successfully!");
            } else if (operation.equals("category")) {
                mv.addObject("message", "Category Submitted Successfully!");
            }
        }

        return mv;
    }

    @RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
    public ModelAndView showEditProducts(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("userClickManageProducts", true);
        mv.addObject("title", "Manage Products");

        // fetch the product from the database
        Product nProduct = productDAO.get(id);

        mv.addObject("product", nProduct);

        return mv;
    }

    //handling product submission
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult result, Model model,
            HttpServletRequest request) {

        // handle image validation for a new product
        if (mProduct.getId() == 0) {
            new ProductValidator().validate(mProduct, result);
        } else {
            if (!mProduct.getFile().getOriginalFilename().equals("")) {
                new ProductValidator().validate(mProduct, result);
            }
        }

        // check if there are any error
        if (result.hasErrors()) {
            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", "Manage Products");
            model.addAttribute("message", "Validation failed for Product Submission!");

            return "page";
        }

        logger.info(mProduct.toString());

        if (mProduct.getId() == 0) {
            // create a new product record if id is 0
            productDAO.add(mProduct);
        } else {
            // update the product if id is not 0
            productDAO.update(mProduct);
        }

        if (!mProduct.getFile().getOriginalFilename().equals("")) {
            FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        return "redirect:/manage/products?operation=product";
    }

    @RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
    @ResponseBody
    public String handleProductActivation(@PathVariable int id) {
        // is going to fetch the product the product from the database
        Product product = productDAO.get(id);
        boolean isActive = product.isActive();
        // activating and deactivating based on the value of active field
        product.setActive(!product.isActive());
        // updating the product
        productDAO.update(product);

        return (isActive)
                ? "You have succesfully deactivated the product with id " + product.getId()
                : "You have succesfully ctivated the product with id " + product.getId();
    }

    // to handle category submission
    @RequestMapping(value = "category", method = RequestMethod.POST)
    public String handleCategorySubmission(@ModelAttribute Category category) {

        // add new category
        categoryDAO.add(category);

        return "redirect:/manage/products?operation=category";
    }

    // Returning categories for all the request mapping
    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryDAO.list();
    }

    @ModelAttribute("category")
    public Category getCategory() {
        return new Category();
    }
}
