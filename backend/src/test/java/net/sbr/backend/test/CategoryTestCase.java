package net.sbr.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.sbr.backend.dao.CategoryDAO;
import net.sbr.backend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass	
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.sbr.backend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
	/*@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some description for laptop");
		category.setImageURL("CAT_2.png");
		
		assertEquals("Successfully added a category inside the table!" , true, categoryDAO.add(category));
	}*/
	
	/*@Test
	public void tesGetCategory() {
		category = categoryDAO.get(1);
		assertEquals("Successfully fetch a single category from the table!" , "Television", category.getName());
	}*/
	
	/*@Test
	public void tesUpdateCategory() {
		category = categoryDAO.get(1);
		category.setName("TV");
		assertEquals("Successfully update a single category in the table!" , true, categoryDAO.update(category));
	}*/
	
	/*@Test
	public void tesDeleteCategory() {
		category = categoryDAO.get(1);
		assertEquals("Successfully delete a single category in the table!" , true, categoryDAO.delete(category));
	}*/
	
	/*@Test
	public void tesListCategory() {
		assertEquals("Successfully fetched te list categories from the table!" , 1, categoryDAO.list().size());
	}*/
	
	/*@Test
	public void testCRUDCategory() {
		
		// add operation
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some description for laptop");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added a category inside the table!" , true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("This is some description for Television");
		category.setImageURL("CAT_2.png");
		assertEquals("Successfully added a category inside the table!" , true, categoryDAO.add(category));
		
		// fetching and updating the category
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Successfully update a single category in the table!" , true, categoryDAO.update(category));
		
		// delete the category
		assertEquals("Successfully delete a single category in the table!" , true, categoryDAO.delete(category));
		
		// fetching the list 
		assertEquals("Successfully fetched te list categories from the table!" , 1, categoryDAO.list().size());
	}*/
	
}
