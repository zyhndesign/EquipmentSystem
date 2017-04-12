package com.cidic.equipment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.equipment.dao.CategoryDao;
import com.cidic.equipment.model.Category;
import com.cidic.equipment.model.CategoryTableModel;
import com.cidic.equipment.service.CategoryService;
import com.cidic.equipment.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	@Qualifier("categoryDaoImpl")
	private CategoryDao categoryDaoImpl;
	
	@Override
	public int createCategory(Category category) {
		try{
			categoryDaoImpl.createCategory(category);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateCategory(Category category) {
		try{
			categoryDaoImpl.updateCategory(category);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteCategory(int id) {
		try{
			categoryDaoImpl.deleteCategory(id);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public CategoryTableModel getCategoryByPage(int offset, int limit) {
		CategoryTableModel categoryTableModel = new CategoryTableModel();
		List<Category> list = categoryDaoImpl.getCategoryByPage(offset, limit);
		int count = categoryDaoImpl.getCountCategory();
		categoryTableModel.setList(list);
		categoryTableModel.setCount(count);
		return categoryTableModel;
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDaoImpl.getAllCategory();
	}

}
