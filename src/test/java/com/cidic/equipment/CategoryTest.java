package com.cidic.equipment;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.equipment.model.Category;
import com.cidic.equipment.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CategoryTest {


	@Autowired
	@Qualifier(value="categoryServiceImpl")
	private CategoryService categoryServiceImpl;
	
	@Test
	public void createCategory(){
		Category category = new Category();
		category.setParentId(1);
		category.setCreateTime(new Date());
		category.setName("汽车");
		categoryServiceImpl.createCategory(category);
	}
}
