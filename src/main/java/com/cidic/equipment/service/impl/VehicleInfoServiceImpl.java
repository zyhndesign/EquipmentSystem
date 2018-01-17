package com.cidic.equipment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.equipment.dao.BrandDao;
import com.cidic.equipment.dao.CategoryDao;
import com.cidic.equipment.dao.VehicleInfoDao;
import com.cidic.equipment.model.Category;
import com.cidic.equipment.model.VehicleInfo;
import com.cidic.equipment.model.VehicleInfoTableModel;
import com.cidic.equipment.service.VehicleInfoService;
import com.cidic.equipment.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "vehicleInfoServiceImpl")
public class VehicleInfoServiceImpl implements VehicleInfoService {

	@Autowired
	@Qualifier("vehicleInfoDaoImpl")
	private VehicleInfoDao vehicleInfoDaoImpl;
	
	@Autowired
	@Qualifier("brandDaoImpl")
	private BrandDao brandDaoImpl;
	
	@Autowired
	@Qualifier("categoryDaoImpl")
	private CategoryDao categoryDaoImpl;
	
	@Override
	public int createVehicleInfo(VehicleInfo vehicleInfo) {
		try{
			
			vehicleInfoDaoImpl.createVehicleInfo(vehicleInfo);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateVehicleInfo(VehicleInfo vehicleInfo) {
		try{
			vehicleInfoDaoImpl.deleteVehicleColor(vehicleInfo.getId());
			vehicleInfoDaoImpl.deleteVehicleTexture(vehicleInfo.getId());
			vehicleInfoDaoImpl.updateVehicleInfo(vehicleInfo);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteVehicleInfo(int id) {
		try{
			vehicleInfoDaoImpl.deleteVehicleInfo(id);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public VehicleInfoTableModel getVehicleInfoByPage(int offset, int limit){
		VehicleInfoTableModel vehicleInfoTableModel = new VehicleInfoTableModel();
		List<VehicleInfo> list = vehicleInfoDaoImpl.getVehicleInfoByPage(offset, limit);
		int count = vehicleInfoDaoImpl.getVehicleInfoCount();
		Map<Integer,String> brandMap = brandDaoImpl.getBrandMap();
		Map<Integer,String> categoryMap = categoryDaoImpl.getCategoryMap();
		for (VehicleInfo vehicleInfo : list){
			vehicleInfo.setBrandName(brandMap.get(vehicleInfo.getBrandId()));
			vehicleInfo.setCategoryName(categoryMap.get(vehicleInfo.getCategoryId()));
	       
		}
		vehicleInfoTableModel.setList(list);
		vehicleInfoTableModel.setCount(count);
		
		
		return vehicleInfoTableModel;
	}

	@Override
	public Optional<VehicleInfo> getDataByVehicleInfoId(int id) {
		return vehicleInfoDaoImpl.getDataByVehicleInfoId(id);
	}

	@Override
	public List<VehicleInfo> getDataByBrandId(int id) {
		return vehicleInfoDaoImpl.getDataByBrandId(id);
	}

	@Override
	public List<VehicleInfo> getDataByCategoryId(int id) {
		return vehicleInfoDaoImpl.getDataByCategoryId(id);
	}

	@Override
	public VehicleInfoTableModel getVehicleInfoBySearchCondition(List<Integer> brandList, int offset, int limit) {
		VehicleInfoTableModel vehicleInfoTableModel = new VehicleInfoTableModel();
		List<VehicleInfo> list = vehicleInfoDaoImpl.getDataBySearchCondition(brandList, offset, limit);
		int count = vehicleInfoDaoImpl.getDataCountBySearchCondition(brandList);
		Map<Integer,String> brandMap = brandDaoImpl.getBrandMap();
		Map<Integer,String> categoryMap = categoryDaoImpl.getCategoryMap();
		for (VehicleInfo vehicleInfo : list){
			vehicleInfo.setBrandName(brandMap.get(vehicleInfo.getBrandId()));
			vehicleInfo.setCategoryName(categoryMap.get(vehicleInfo.getCategoryId()));
	       
		}
		vehicleInfoTableModel.setList(list);
		vehicleInfoTableModel.setCount(count);
		
		
		return vehicleInfoTableModel;
	}

}
