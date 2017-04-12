package com.cidic.equipment.dao;

import java.util.List;

import com.cidic.equipment.model.Brand;

public interface BrandDao {

	public void createBrand(Brand brand);
	
	public void updateBrand(Brand brand);
	
	public void deleteBrand(int id);
	
	public List<Brand> getBrandByPage(int offset, int limit);
	
	public int getCountBrand();
	
	public List<Brand> getAllBrand();
}
