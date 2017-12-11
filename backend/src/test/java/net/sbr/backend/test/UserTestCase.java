/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.backend.test;

import static org.junit.Assert.assertEquals;
import net.sbr.backend.dao.UserDAO;
import net.sbr.backend.dto.Address;
import net.sbr.backend.dto.Cart;
import net.sbr.backend.dto.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author sabar
 */
public class UserTestCase {

    private static AnnotationConfigApplicationContext context;
    private static UserDAO userDAO;
    private User user = null;
    private Cart cart = null;
    private Address address = null;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("net.sbr.backend");
        context.refresh();

        userDAO = (UserDAO) context.getBean("userDAO");

    }

    /*@Test
    public void testAdd() {
        user = new User();
        user.setFirstName("Hrithik");
        user.setLastName("Roshan");
        user.setEmail("hr@gmail.com");
        user.setContactNumber("1234512345");
        user.setRole("USER");
        user.setPassword("123456");

        // add the user
        assertEquals("Failed to add user!", true, userDAO.addUser(user));

        address = new Address();
        address.setAddressLineOne("101/B Jado Society, Krissh Nagar");
        address.setAddressLineTwo("Near Kaabil Store");
        address.setCity("Mumbai");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("400001");
        address.setBilling(true);

        // link the user with the address using user id
        address.setUserId(user.getId());

        // add the address
        assertEquals("Failed to add address", true, userDAO.addAddress(address));

        if (user.getRole().equals("USER")) {
            cart = new Cart();
            cart.setUser(user);

            assertEquals("Failed to add cart!", true, userDAO.addCart(cart));

            address = new Address();
            address.setAddressLineOne("201/B Jado Society, Krissh Nagar");
            address.setAddressLineTwo("Near Kuadrat Store");
            address.setCity("Mumbai");
            address.setState("Maharashtra");
            address.setCountry("India");
            address.setPostalCode("400001");
            address.setBilling(true);
            address.setShipping(true);

            address.setUserId(user.getId());
             assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
        }
    }
    @Test
    public void testAdd() {
        user = new User();
        user.setFirstName("Hrithik");
        user.setLastName("Roshan");
        user.setEmail("hr@gmail.com");
        user.setContactNumber("1234512345");
        user.setRole("USER");
        user.setPassword("123456");

        if (user.getRole().equals("USER")) {
            cart = new Cart();
            cart.setUser(user);

            user.setCart(cart);
        }
        
        assertEquals("Failed to add user!", true, userDAO.addUser(user));
    }*/
 /* @Test
    public void testUpdateCart(){
        user = userDAO.getByEmail("hr@gmail.com");
        
        cart = user.getCart();
        cart.setGrandTotal(5555);
        cart.setCartLines(2);
        
        assertEquals("Failed to update cart", true, userDAO.updateCart(cart));
        
    }*/
 /*@Test
    public void testAddAddress() {
        // we need add tthe user
        user = new User();
        user.setFirstName("Hrithik");
        user.setLastName("Roshan");
        user.setEmail("hr@gmail.com");
        user.setContactNumber("1234512345");
        user.setRole("USER");
        user.setPassword("123456");

        // add the user
        assertEquals("Failed to add user!", true, userDAO.addUser(user));

        // add the address
        address = new Address();
        address.setAddressLineOne("101/B Jado Society, Krissh Nagar");
        address.setAddressLineTwo("Near Kaabil Store");
        address.setCity("Mumbai");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("400001");
        address.setBilling(true);

        address.setUser(user);
        assertEquals("Failed to add address", true, userDAO.addAddress(address));

        // shipping address
        address = new Address();
        address.setAddressLineOne("201/B Jado Society, Krissh Nagar");
        address.setAddressLineTwo("Near Kuadrat Store");
        address.setCity("Mumbai");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("400001");
        address.setShipping(true);
        
        address.setUser(user);
        assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
    }*/
 /*@Test
    public void testAddAddress(){
        user = userDAO.getByEmail("hr@gmail.com");
        
        address = new Address();
        address.setAddressLineOne("301/B Jado Society, Kanhaiya Nagar");
        address.setAddressLineTwo("Near Kaabil Store");
        address.setCity("Mumbai");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setPostalCode("400001");
        address.setShipping(true);
        
        address.setUser(user);
        assertEquals("Failed to add address", true, userDAO.addAddress(address));
    }*/
    @Test
    public void testGetAddress() {
        user = userDAO.getByEmail("hr@gmail.com");

        assertEquals("Failed to fetch the list of address and size does not match!", 2,
                userDAO.listShippingAddress(user).size());
        assertEquals("Failed to fetch the billing of address and size does not match!", "Mumbai",
                userDAO.getBillingAddress(user).getCity());
    }
}
