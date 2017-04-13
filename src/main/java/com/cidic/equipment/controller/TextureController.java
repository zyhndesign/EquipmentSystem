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
import com.cidic.equipment.model.Texture;
import com.cidic.equipment.model.TextureTableModel;
import com.cidic.equipment.model.Color;
import com.cidic.equipment.model.ListResultModel;
import com.cidic.equipment.model.ResultModel;
import com.cidic.equipment.service.TextureService;
import com.cidic.equipment.util.ResponseCodeUtil;
import com.cidic.equipment.util.WebRequestUtil;

@Controller
@RequestMapping("/texture")
public class TextureController {
	
	@Autowired
	@Qualifier(value = "textureServiceImpl")
	private TextureService textureServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(EquipmentException.class)
	public @ResponseBody ResultModel handleCustomException(EquipmentException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@RequestMapping(value = "/texture", method = RequestMethod.GET)
	public String texture(Locale locale, Model model) {
		return "texture";
	}
	
	@RequestMapping(value = "/textureCOU", method = RequestMethod.GET)
	public ModelAndView textureCOU(Locale locale, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("textureCOU");
		return modelAndView;
	}

	@RequestMapping(value = "/textureCOU/{id}", method = RequestMethod.GET)
    public ModelAndView updateTextureCOU(HttpServletRequest request, @PathVariable int id) {

		Texture texture = null;
        if (id > 0) {
        	texture = textureServiceImpl.getDataByTextureId(id).get();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("textureCOU");
        view.addObject("texture", texture);
        return view;
    }
	
	@RequestMapping(value = "/createTexture", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createTexture(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name, @RequestParam String icon, @RequestParam String description) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Texture Texture = new Texture();
		Texture.setCreateTime(new Date());
		Texture.setName(name);
		Texture.setDescription(description);
		Texture.setIcon(icon);
		
		int result = textureServiceImpl.createTexture(Texture);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
	
	@RequestMapping(value = "/updateTexture", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateTexture(HttpServletRequest request, HttpServletResponse response, @RequestParam int id,
			@RequestParam String name, @RequestParam String icon, @RequestParam String description) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		Texture Texture = new Texture();
		Texture.setCreateTime(new Date());
		Texture.setName(name);
		Texture.setDescription(description);
		Texture.setIcon(icon);
		Texture.setId(id);
		
		int result = textureServiceImpl.updateTexture(Texture);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
	
	@RequestMapping(value = "/deleteTexture", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteTexture(HttpServletRequest request, HttpServletResponse response, @RequestParam int id) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = textureServiceImpl.deleteTexture(id);
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
			
			TextureTableModel TextureTableModel = textureServiceImpl.getTextureByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(TextureTableModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int) TextureTableModel.getCount());
			listResultModel.setiTotalDisplayRecords((int) TextureTableModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/getAllTexture", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getAllTexture(HttpServletRequest request, HttpServletResponse response) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		try {
			List<Texture> list = textureServiceImpl.getAllTexture();
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}catch (Exception e) {
			throw new EquipmentException(500, "����ʧ��");
		}
	}
}
