package com.cidic.equipment.service;

import java.util.List;
import java.util.Optional;

import com.cidic.equipment.model.Texture;
import com.cidic.equipment.model.TextureTableModel;

public interface TextureService {

	public int createTexture(Texture texture);
	
	public int updateTexture(Texture texture);
	
	public int deleteTexture(int id);
	
	public TextureTableModel getTextureByPage(int offset, int limit);
	
	public List<Texture> getAllTexture();
	
	public Optional<Texture> getDataByTextureId(int id);
}
