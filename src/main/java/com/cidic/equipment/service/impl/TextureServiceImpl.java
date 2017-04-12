package com.cidic.equipment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.equipment.dao.TextureDao;
import com.cidic.equipment.model.Texture;
import com.cidic.equipment.model.TextureTableModel;
import com.cidic.equipment.service.TextureService;
import com.cidic.equipment.util.ResponseCodeUtil;

@Repository
@Transactional
@Component
@Qualifier(value = "textureServiceImpl")
public class TextureServiceImpl implements TextureService {

	@Autowired
	@Qualifier("textureDaoImpl")
	private TextureDao textureDaoImpl;
	
	@Override
	public int createTexture(Texture texture) {
		try{
			textureDaoImpl.createTexture(texture);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateTexture(Texture texture) {
		try{
			textureDaoImpl.updateTexture(texture);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteTexture(int id) {
		try{
			textureDaoImpl.deleteTexture(id);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public TextureTableModel getTextureByPage(int offset, int limit) {
		TextureTableModel textureTableModel = new TextureTableModel();
		List<Texture> list = textureDaoImpl.getTextureByPage(offset, limit);
		int count = textureDaoImpl.getCountTexture();
		textureTableModel.setList(list);
		textureTableModel.setCount(count);
		return textureTableModel;
	}

	@Override
	public List<Texture> getAllTexture() {
		return textureDaoImpl.getAllTexture();
	}

}
