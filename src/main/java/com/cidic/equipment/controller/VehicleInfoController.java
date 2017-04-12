package com.cidic.equipment.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.equipment.exception.EquipmentException;
import com.cidic.equipment.model.ListResultModel;
import com.cidic.equipment.model.ResultModel;
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
	
	@RequestMapping(value = "/createVehicleInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel createVehicleInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestBody VehicleInfo vehicleInfo) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		
		int result = vehicleInfoServiceImpl.createVehicleInfo(vehicleInfo);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "²Ù×÷Ê§°Ü");
		}
	}
	
	@RequestMapping(value = "/updateVehicleInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel updateVehicleInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody VehicleInfo vehicleInfo) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		resultModel = new ResultModel();
		
		int result = vehicleInfoServiceImpl.updateVehicleInfo(vehicleInfo);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS) {
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} else {
			throw new EquipmentException(500, "²Ù×÷Ê§°Ü");
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
			throw new EquipmentException(500, "²Ù×÷Ê§°Ü");
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
}
