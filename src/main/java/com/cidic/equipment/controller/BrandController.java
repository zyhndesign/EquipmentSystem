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
import com.cidic.equipment.model.Brand;
import com.cidic.equipment.model.BrandTableModel;
import com.cidic.equipment.model.ListResultModel;
import com.cidic.equipment.model.ResultModel;
import com.cidic.equipment.service.BrandService;
import com.cidic.equipment.util.ResponseCodeUtil;
import com.cidic.equipment.util.WebRequestUtil;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	@Qualifier(value = "brandServiceImpl")
	private BrandService brandServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(EquipmentException.class)
	public @ResponseBody ResultModel handleCustomException(EquipmentException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@RequestMapping(value = "/brand", method = RequestMethod.GET)
	public String brand(Locale locale, Model model) {
		return "brand";
	}
	
	@RequestMapping(value = "/brandCOU", method = RequestMethod.GET)
	public ModelAndView guideCOU(Locale locale, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("brandCOU");
		return modelAndView;
	}

	@RequestMapping(value = "/brandCOU/{id}", method = RequestMethod.GET)
    public ModelAndView updateGuideCOU(HttpServletRequest request, @PathVariable int id) {

		Brand brand = null;
        if (id > 0) {
        	brand = brandServiceImpl.getDataByBrandId(id).get();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("brandCOU");
        view.addObject("brand", brand);
        return view;
    }
	
	@RequestMapping(value = "/createBrand", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createBrand(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name, @RequestParam String icon, @RequestParam String description) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Brand brand = new Brand();
		brand.setCreateTime(new Date());
		brand.setName(name);
		brand.setDescription(description);
		brand.setIcon(icon);
		
		int result = brandServiceImpl.createBrand(brand);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
	
	@RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateBrand(HttpServletRequest request, HttpServletResponse response, @RequestParam int id,
			@RequestParam String name, @RequestParam String icon, @RequestParam String description) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Brand brand = new Brand();
		brand.setCreateTime(new Date());
		brand.setName(name);
		brand.setDescription(description);
		brand.setIcon(icon);
		brand.setId(id);
		
		int result = brandServiceImpl.updateBrand(brand);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
	
	@RequestMapping(value = "/deleteBrand", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteBrand(HttpServletRequest request, HttpServletResponse response, @RequestParam int id) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = brandServiceImpl.deleteBrand(id);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
	
	@RequestMapping(value = "/getDataByCondition", method = RequestMethod.GET)
	@ResponseBody
	public ListResultModel getDataByCondition(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int iDisplayLength, @RequestParam int iDisplayStart, @RequestParam String sEcho) {
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			
			BrandTableModel brandTableModel = brandServiceImpl.getBrandByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(brandTableModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int) brandTableModel.getCount());
			listResultModel.setiTotalDisplayRecords((int) brandTableModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/getAllBrand", method = RequestMethod.POST)
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
			throw new EquipmentException(500, "����ʧ��");
		}
	}
}
