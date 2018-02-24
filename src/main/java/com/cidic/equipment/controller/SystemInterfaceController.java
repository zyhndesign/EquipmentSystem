package com.cidic.equipment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.equipment.exception.EquipmentException;
import com.cidic.equipment.model.Brand;
import com.cidic.equipment.model.Category;
import com.cidic.equipment.model.ResultModel;
import com.cidic.equipment.model.VehicleInfo;
import com.cidic.equipment.service.BrandService;
import com.cidic.equipment.service.CategoryService;
import com.cidic.equipment.service.VehicleInfoService;
import com.cidic.equipment.util.WebRequestUtil;

@Controller
@RequestMapping("/esApi")
public class SystemInterfaceController {

	@Autowired
	@Qualifier(value = "brandServiceImpl")
	private BrandService brandServiceImpl;

	@Autowired
	@Qualifier(value = "vehicleInfoServiceImpl")
	private VehicleInfoService vehicleInfoServiceImpl;
	
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
	
	/**
	 * 获取所有品牌
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAllBrand", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getAllBrand(HttpServletRequest request, HttpServletResponse response) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		
		resultModel = new ResultModel();
		try {
			List<Brand> list = brandServiceImpl.getAllBrand();
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}catch (Exception e) {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	/**
	 * 根据品牌id获取该品牌产品详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProductByBrand/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getProductByBrand(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		
		resultModel = new ResultModel();
		try {
			List<VehicleInfo> list = vehicleInfoServiceImpl.getDataByBrandId(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}catch (Exception e) {
			e.printStackTrace();
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	/**
	 * 根据产品种类id获取该品牌产品详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProductByCategory/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getProductByCategory(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		
		resultModel = new ResultModel();
		try {
			List<VehicleInfo> list = vehicleInfoServiceImpl.getDataByCategoryId(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}catch (Exception e) {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	/**
	 * 根据id获取该产品详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProductDetail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getProductDetail(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {

		WebRequestUtil.AccrossAreaRequestSet(request, response);
		
		resultModel = new ResultModel();
		try {
			VehicleInfo vehicleInfo = vehicleInfoServiceImpl.getDataByVehicleInfoId(id).get();
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(vehicleInfo);
			return resultModel;
		}catch (Exception e) {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	/**
	 * 获取所有品类和结构
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAllCategory", method = RequestMethod.GET)
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
	
	/**
	 * 根据父节点ID获取所有该节点下所有节点
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCategoryByParentId/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getCategoryByParentId(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		try {
			List<Category> list = categoryServiceImpl.getCategoryByParentId(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}catch (Exception e) {
			throw new EquipmentException(500, "操作失败！");
		}
	}
}
