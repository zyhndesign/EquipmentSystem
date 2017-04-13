package com.cidic.equipment.dao;

import java.util.List;
import java.util.Optional;

import com.cidic.equipment.model.Category;

public interface CategoryDao {

	public void createCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void deleteCategory(int id);
	
	public List<Category> getCategoryByPage(int offset, int limit);
	
	public int getCountCategory();
	
	public List<Category> getAllCategory();
	
	public Optional<Category> getDataByCategoryId(int id);
}
