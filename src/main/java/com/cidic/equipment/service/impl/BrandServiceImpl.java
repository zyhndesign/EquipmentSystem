package com.cidic.equipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.equipment.dao.BrandDao;
import com.cidic.equipment.model.Brand;
import com.cidic.equipment.model.BrandTableModel;
import com.cidic.equipment.service.BrandService;
import com.cidic.equipment.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "brandServiceImpl")
public class BrandServiceImpl implements BrandService {

	@Autowired
	@Qualifier("brandDaoImpl")
	private BrandDao brandDaoImpl;
	
	@Override
	public int createBrand(Brand brand) {
		try{
			brandDaoImpl.createBrand(brand);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateBrand(Brand brand) {
		try{
			brandDaoImpl.updateBrand(brand);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteBrand(int id) {
		try{
			brandDaoImpl.deleteBrand(id);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public BrandTableModel getBrandByPage(int offset, int limit) {
		BrandTableModel brandTableModel = new BrandTableModel();
		List<Brand> list = brandDaoImpl.getBrandByPage(offset, limit);
		int count = brandDaoImpl.getCountBrand();
		brandTableModel.setList(list);
		brandTableModel.setCount(count);
		return brandTableModel;
	}

	@Override
	public List<Brand> getAllBrand() {
		return brandDaoImpl.getAllBrand();
	}

	@Override
	public Optional<Brand> getDataByBrandId(int id) {
		// TODO Auto-generated method stub
		return brandDaoImpl.getDataByBrandId(id);
	}

}
