package com.cidic.equipment.dao;

import java.util.List;
import java.util.Optional;

import com.cidic.equipment.model.VehicleInfo;

public interface VehicleInfoDao {

	public void createVehicleInfo(VehicleInfo vehicleInfo);
	
	public void updateVehicleInfo(VehicleInfo vehicleInfo);
	
	public void deleteVehicleInfo(int id);
	
	public List<VehicleInfo> getVehicleInfoByPage(int offset, int limit);
	
	public int getVehicleInfoCount();
	
	public Optional<VehicleInfo> getDataByVehicleInfoId(int id);
}
