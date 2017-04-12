package com.cidic.equipment.dao;

import java.util.List;

import com.cidic.equipment.model.Texture;

public interface TextureDao {

	public void createTexture(Texture texture);
	
	public void updateTexture(Texture texture);
	
	public void deleteTexture(int id);
	
	public List<Texture> getTextureByPage(int offset, int limit);
	
	public int getCountTexture();
	
	public List<Texture> getAllTexture();
}
