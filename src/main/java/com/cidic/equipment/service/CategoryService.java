package com.cidic.equipment.service;

import java.util.List;
import java.util.Optional;

import com.cidic.equipment.model.Category;
import com.cidic.equipment.model.CategoryTableModel;

public interface CategoryService {

	public int createCategory(Category category);
	
	public int updateCategory(Category category);
	
	public int deleteCategory(int id);
	
	public CategoryTableModel getCategoryByPage(int offset, int limit);
	
	public List<Category> getAllCategory();
	
	public Optional<Category> getDataByCategoryId(int id);

	public List<Category> getCategoryByParentId(int parentId);
}
