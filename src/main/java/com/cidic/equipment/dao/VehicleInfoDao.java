package com.cidic.equipment.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cidic.equipment.model.VehicleInfo;

public interface VehicleInfoDao {

	public void createVehicleInfo(VehicleInfo vehicleInfo);
	
	public void updateVehicleInfo(VehicleInfo vehicleInfo);
	
	public void deleteVehicleInfo(int id);
	
	public List<VehicleInfo> getVehicleInfoByPage(int offset, int limit);
	
	public int getVehicleInfoCount();
	
	public Optional<VehicleInfo> getDataByVehicleInfoId(int id);
	
	public void deleteVehicleColor(int vehicleId);
	
	public void deleteVehicleTexture(int vehicleId);
	
	public List<VehicleInfo> getDataByBrandId(int id);
	
	public List<VehicleInfo> getDataByCategoryId(int id);
	
	public List<VehicleInfo> getDataBySearchCondition(List<Integer> brandList, Map<String,String> timeQuantumMap, List<Integer> marketTypeList, int offset, int limit);
	
	public int getDataCountBySearchCondition(List<Integer> brandList, Map<String,String> timeQuantumMap, List<Integer> marketTypeList);
}
