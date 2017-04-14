package com.cidic.equipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.equipment.dao.VehicleInfoDao;
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
	public VehicleInfoTableModel getVehicleInfoByPage(int offset, int limit) {
		VehicleInfoTableModel vehicleInfoTableModel = new VehicleInfoTableModel();
		List<VehicleInfo> list = vehicleInfoDaoImpl.getVehicleInfoByPage(offset, limit);
		int count = vehicleInfoDaoImpl.getVehicleInfoCount();
		vehicleInfoTableModel.setList(list);
		vehicleInfoTableModel.setCount(count);
		return vehicleInfoTableModel;
	}

	@Override
	public Optional<VehicleInfo> getDataByVehicleInfoId(int id) {
		// TODO Auto-generated method stub
		return vehicleInfoDaoImpl.getDataByVehicleInfoId(id);
	}

}
