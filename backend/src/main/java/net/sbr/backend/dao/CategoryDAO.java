package net.sbr.backend.dao;

import java.util.List;

import net.sbr.backend.dto.Category;

public interface CategoryDAO {

	List<Category> list();

	Category get(int id);
}
