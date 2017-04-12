package com.cidic.equipment.dao;

import java.util.List;

import com.cidic.equipment.model.Color;

public interface ColorDao {

	public void createColor(Color color);
	
	public void updateColor(Color color);
	
	public void deleteColor(int id);
	
	public List<Color> getColorByPage(int offset, int limit);
	
	public int getCountColor();
	
	public List<Color> getAllColor();
}
