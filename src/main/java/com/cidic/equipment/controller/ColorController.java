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
import com.cidic.equipment.model.Color;
import com.cidic.equipment.model.ColorTableModel;
import com.cidic.equipment.model.ListResultModel;
import com.cidic.equipment.model.ResultModel;
import com.cidic.equipment.service.ColorService;
import com.cidic.equipment.util.ResponseCodeUtil;
import com.cidic.equipment.util.WebRequestUtil;

@Controller
@RequestMapping("/color")
public class ColorController {
	
	@Autowired
	@Qualifier(value = "colorServiceImpl")
	private ColorService colorServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(EquipmentException.class)
	public @ResponseBody ResultModel handleCustomException(EquipmentException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@RequestMapping(value = "/color", method = RequestMethod.GET)
	public String brand(Locale locale, Model model) {
		return "color";
	}
	
	@RequestMapping(value = "/colorCOU", method = RequestMethod.GET)
	public ModelAndView guideCOU(Locale locale, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("colorCOU");
		return modelAndView;
	}

	@RequestMapping(value = "/colorCOU/{id}", method = RequestMethod.GET)
    public ModelAndView updateColorCOU(HttpServletRequest request, @PathVariable int id) {

		Color color = null;
        if (id > 0) {
        	color = colorServiceImpl.getDataByColorId(id).get();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("colorCOU");
        view.addObject("color", color);
        return view;
    }
	
	@RequestMapping(value = "/createColor", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createColor(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name, @RequestParam String colorValue) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Color color = new Color();
		color.setCreateTime(new Date());
		color.setName(name);
		color.setColorValue(colorValue);
		
		int result = colorServiceImpl.createColor(color);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
	
	@RequestMapping(value = "/updateColor", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateColor(HttpServletRequest request, HttpServletResponse response, @RequestParam int id,
			@RequestParam String name, @RequestParam String colorValue) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Color color = new Color();
		color.setCreateTime(new Date());
		color.setName(name);
		color.setColorValue(colorValue);
		color.setId(id);
		
		int result = colorServiceImpl.updateColor(color);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
	
	@RequestMapping(value = "/deleteColor", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteColor(HttpServletRequest request, HttpServletResponse response, @RequestParam int id) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = colorServiceImpl.deleteColor(id);
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
			
			ColorTableModel colorTableModel = colorServiceImpl.getColorByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(colorTableModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int) colorTableModel.getCount());
			listResultModel.setiTotalDisplayRecords((int) colorTableModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/getAllColor", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getAllColor(HttpServletRequest request, HttpServletResponse response) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		try {
			List<Color> list = colorServiceImpl.getAllColor();
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}catch (Exception e) {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
}
