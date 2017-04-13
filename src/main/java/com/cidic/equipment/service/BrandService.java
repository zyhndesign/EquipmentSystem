package com.cidic.equipment.service;

import java.util.List;
import java.util.Optional;

import com.cidic.equipment.model.Brand;
import com.cidic.equipment.model.BrandTableModel;

public interface BrandService {

	public int createBrand(Brand brand);
	
	public int updateBrand(Brand brand);
	
	public int deleteBrand(int id);
	
	public BrandTableModel getBrandByPage(int offset, int limit);
	
	public List<Brand> getAllBrand();
	
	public Optional<Brand> getDataByBrandId(int id);
}
