package com.cidic.equipment.service;

import java.util.List;

import com.cidic.equipment.model.Color;
import com.cidic.equipment.model.ColorTableModel;

public interface ColorService {

	public int createColor(Color color);
	
	public int updateColor(Color color);
	
	public int deleteColor(int id);
	
	public ColorTableModel getColorByPage(int offset, int limit);
	
	public List<Color> getAllColor();
}
