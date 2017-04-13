package com.cidic.equipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.equipment.dao.ColorDao;
import com.cidic.equipment.model.Color;
import com.cidic.equipment.model.ColorTableModel;
import com.cidic.equipment.service.ColorService;
import com.cidic.equipment.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "colorServiceImpl")
public class ColorServiceImpl implements ColorService {

	@Autowired
	@Qualifier("colorDaoImpl")
	private ColorDao colorDaoImpl;
	
	@Override
	public int createColor(Color color) {
		try{
			colorDaoImpl.createColor(color);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateColor(Color color) {
		try{
			colorDaoImpl.updateColor(color);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteColor(int id) {
		try{
			colorDaoImpl.deleteColor(id);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public ColorTableModel getColorByPage(int offset, int limit) {
		ColorTableModel colorTableModel = new ColorTableModel();
		List<Color> list = colorDaoImpl.getColorByPage(offset, limit);
		int count = colorDaoImpl.getCountColor();
		colorTableModel.setList(list);
		colorTableModel.setCount(count);
		return colorTableModel;
	}

	@Override
	public List<Color> getAllColor() {
		return colorDaoImpl.getAllColor();
	}

	@Override
	public Optional<Color> getDataByColorId(int id) {
		// TODO Auto-generated method stub
		return colorDaoImpl.getDataByColorId(id);
	}

}
