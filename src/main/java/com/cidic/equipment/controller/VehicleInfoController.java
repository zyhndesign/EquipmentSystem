package com.cidic.equipment.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.equipment.exception.EquipmentException;
import com.cidic.equipment.model.ListResultModel;
import com.cidic.equipment.model.ResultModel;
import com.cidic.equipment.model.Texture;
import com.cidic.equipment.model.VehicleInfo;
import com.cidic.equipment.model.VehicleInfoTableModel;
import com.cidic.equipment.service.VehicleInfoService;
import com.cidic.equipment.util.ResponseCodeUtil;
import com.cidic.equipment.util.WebRequestUtil;

@Controller
@RequestMapping("/vehicleInfo")
public class VehicleInfoController {

	@Autowired
	@Qualifier(value = "vehicleInfoServiceImpl")
	private VehicleInfoService vehicleInfoServiceImpl;

	private ResultModel resultModel = null;

	@ExceptionHandler(EquipmentException.class)
	public @ResponseBody ResultModel handleCustomException(EquipmentException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@RequestMapping(value = "/infoMgr", method = RequestMethod.GET)
	public String infoMgr(Locale locale, Model model) {
		return "infoMgr";
	}
	
	@RequestMapping(value = "/infoCOU", method = RequestMethod.GET)
	public ModelAndView guideCOU(Locale locale, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("infoCOU");
		return modelAndView;
	}

	@RequestMapping(value = "/infoCOU/{id}", method = RequestMethod.GET)
    public ModelAndView updateInfoCOU(HttpServletRequest request, @PathVariable int id) {

		VehicleInfo vehicleInfo = null;
        if (id > 0) {
        	vehicleInfo = vehicleInfoServiceImpl.getDataByVehicleInfoId(id).get();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("infoCOU");
        view.addObject("info", vehicleInfo);
        return view;
    }
	
	@RequestMapping(value = "/createVehicleInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createVehicleInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestBody VehicleInfo vehicleInfo) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		vehicleInfo.setCreateTime(new Date());
		int result = vehicleInfoServiceImpl.createVehicleInfo(vehicleInfo);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	@RequestMapping(value = "/updateVehicleInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateVehicleInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody VehicleInfo vehicleInfo) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		vehicleInfo.setCreateTime(new Date());
		int result = vehicleInfoServiceImpl.updateVehicleInfo(vehicleInfo);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "操作失败！");
		}
	}
	
	@RequestMapping(value = "/deleteVehicleInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteVehicleInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam int id) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		int result = vehicleInfoServiceImpl.deleteVehicleInfo(id);
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
			
			VehicleInfoTableModel vehicleInfoTableModel = vehicleInfoServiceImpl.getVehicleInfoByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(vehicleInfoTableModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int) vehicleInfoTableModel.getCount());
			listResultModel.setiTotalDisplayRecords((int) vehicleInfoTableModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/searchDataByCondition", method = RequestMethod.GET)
	@ResponseBody
	public ListResultModel searchDataByCondition(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=false) String brand,
			@RequestParam int iDisplayLength, @RequestParam int iDisplayStart, @RequestParam(required=false) String startDate, 
			@RequestParam(required=false) String endDate, @RequestParam(required=false) String marketType, @RequestParam String sEcho) {
		
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		ListResultModel listResultModel = new ListResultModel();
		try {
			List<Integer> list = new ArrayList<>();
			if (brand.length() > 1){
				int[] brandsArray = Arrays.stream(brand.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
		        list = Arrays.stream(brandsArray).boxed().collect(Collectors.toList());  
			}
			
			List<Integer> marketTypeList = new ArrayList<>();
	        if (marketType.length() > 1){
	        	int[] marketTypesArray = Arrays.stream(marketType.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
		        marketTypeList = Arrays.stream(marketTypesArray).boxed().collect(Collectors.toList()); 
	        }
	        
	        Map<String,String> timeQuantumMap = new HashMap<String,String>();
	        if (startDate != null && !startDate.equals("")){
	        	timeQuantumMap.put("startYear", startDate);
	        }
	        if (endDate != null && !endDate.equals("")){
	        	timeQuantumMap.put("endYear", endDate);
	        }
	        
			VehicleInfoTableModel vehicleInfoTableModel = vehicleInfoServiceImpl.getVehicleInfoBySearchCondition(list,timeQuantumMap,marketTypeList,iDisplayStart, iDisplayLength);
			listResultModel.setAaData(vehicleInfoTableModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords((int) vehicleInfoTableModel.getCount());
			listResultModel.setiTotalDisplayRecords((int) vehicleInfoTableModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/getVehicleInfoById", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel getVehicleInfoById(HttpServletRequest request, HttpServletResponse response,@RequestParam int id) {
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
}
