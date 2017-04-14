package com.cidic.equipment.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.equipment.exception.EquipmentException;
import com.cidic.equipment.model.Category;
import com.cidic.equipment.model.CategoryTableModel;
import com.cidic.equipment.model.ListResultModel;
import com.cidic.equipment.model.ResultModel;
import com.cidic.equipment.service.CategoryService;
import com.cidic.equipment.util.ResponseCodeUtil;
import com.cidic.equipment.util.WebRequestUtil;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	@Qualifier(value = "categoryServiceImpl")
	private CategoryService categoryServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(EquipmentException.class)
	public @ResponseBody ResultModel handleCustomException(EquipmentException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String brand(Locale locale, Model model) {
		return "category";
	}
	
	@RequestMapping(value = "/categoryCOU", method = RequestMethod.GET)
	public ModelAndView guideCOU(Locale locale, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("categoryCOU");
		return modelAndView;
	}

	@RequestMapping(value = "/categoryCOU/{id}", method = RequestMethod.GET)
    public ModelAndView updateCategoryCOU(HttpServletRequest request, @PathVariable int id) {

		Category category = null;
        if (id > 0) {
        	category = categoryServiceImpl.getDataByCategoryId(id).get();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("categoryCOU");
        view.addObject("category", category);
        return view;
    }
	
	@RequestMapping(value = "/createCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int parentId, @RequestParam String name) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Category category = new Category();
		category.setCreateTime(new Date());
		category.setName(name);
		category.setParentId(parentId);
		
		int result = categoryServiceImpl.createCategory(category);
		if (result != ResponseCodeUtil.DB_OPERATION_FAILURE) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(result);
			return resultModel;
		} else {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateCategory(HttpServletRequest request, HttpServletResponse response, @RequestParam int id,
			@RequestParam String name, @RequestParam int parentId) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Category category = new Category();
		category.setCreateTime(new Date());
		category.setName(name);
		category.setId(id);
		category.setParentId(parentId);
		
		int result = categoryServiceImpl.updateCategory(category);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteCategory(HttpServletRequest request, HttpServletResponse response, @RequestParam int id) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = categoryServiceImpl.deleteCategory(id);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	@RequestMapping(value = "/getDataByCondition", method = RequestMethod.GET)
	@ResponseBody
	public ListResultModel getDataByCondition(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int iDisplayLength, @RequestParam int iDisplayStart, @RequestParam String sEcho) {
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			
			CategoryTableModel categoryTableModel = categoryServiceImpl.getCategoryByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(categoryTableModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int) categoryTableModel.getCount());
			listResultModel.setiTotalDisplayRecords((int) categoryTableModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/getAllCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getAllCategory(HttpServletRequest request, HttpServletResponse response) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		try {
			List<Category> list = categoryServiceImpl.getAllCategory();
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}catch (Exception e) {
			throw new EquipmentException(500, "操作失败！");
		}
	}
}
