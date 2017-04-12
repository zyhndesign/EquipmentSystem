package com.cidic.equipment.dao;

import java.util.List;

import com.cidic.equipment.model.Texture;

public interface TextureDao {

	public void createTexture(Texture texture);
	
	public void updateTexture(Texture texture);
	
	public void deleteBrand(int id);
	
	public List<Texture> getBrandByPage(int offset, int limit);
	
	public int getCountTexture();
	
	public List<Texture> getAllTexture();
}
