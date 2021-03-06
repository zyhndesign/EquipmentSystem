package com.cidic.equipment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cidic.equipment.model.VehicleInfo;
import com.cidic.equipment.model.VehicleInfoTableModel;

public interface VehicleInfoService {

	public int createVehicleInfo(VehicleInfo vehicleInfo);
	
	public int updateVehicleInfo(VehicleInfo vehicleInfo);
	
	public int deleteVehicleInfo(int id);
	
	public VehicleInfoTableModel getVehicleInfoByPage(int offset, int limit);
	
	public Optional<VehicleInfo> getDataByVehicleInfoId(int id);
	
	public List<VehicleInfo> getDataByBrandId(int id);
	
	public List<VehicleInfo> getDataByCategoryId(int id);
	
	public VehicleInfoTableModel getVehicleInfoBySearchCondition(List<Integer> brandList, Map<String,String> timeQuantumMap, List<Integer> marketTypeList, int offset, int limit);
	
}
