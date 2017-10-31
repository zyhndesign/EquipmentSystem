package com.cidic.equipment.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.equipment.dao.VehicleInfoDao;
import com.cidic.equipment.model.VehicleColor;
import com.cidic.equipment.model.VehicleInfo;
import com.cidic.equipment.model.VehicleTexture;

@Repository
@Component
@Qualifier(value = "vehicleInfoDaoImpl")
public class VehicleInfoDaoImpl implements VehicleInfoDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void createVehicleInfo(VehicleInfo vehicleInfo) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		for (VehicleColor color : vehicleInfo.getVehicleColors()){
			color.setVehicleInfo(vehicleInfo);
		
		}
		
		for (VehicleTexture textures : vehicleInfo.getVehicleTextures()){
			textures.setVehicleInfo(vehicleInfo);
		}
		session.save(vehicleInfo);
	}

	@Override
	public void updateVehicleInfo(VehicleInfo vehicleInfo) {
		Session session = this.getSessionFactory().getCurrentSession();
		for (VehicleColor color : vehicleInfo.getVehicleColors()){
			color.setVehicleInfo(vehicleInfo);
		}
		
		for (VehicleTexture textures : vehicleInfo.getVehicleTextures()){
			textures.setVehicleInfo(vehicleInfo);
		}
		session.update(vehicleInfo);
	}

	@Override
	public void deleteVehicleInfo(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " delete from VehicleInfo t where t.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
		query.executeUpdate();
	}

	@Override
	public List<VehicleInfo> getVehicleInfoByPage(int offset, int limit) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from VehicleInfo";
		final Query query = session.createQuery(hql);  
		query.setFirstResult(offset);    
	    query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		final List<VehicleInfo> list = query.list(); 
		return list;
	}

	@Override
	public int getVehicleInfoCount() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(v) from VehicleInfo v"; 
        final Query query = session.createQuery(hql); 
        long count = (Long)query.uniqueResult();
        return (int)count;
	}

	@Override
	public Optional<VehicleInfo> getDataByVehicleInfoId(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from VehicleInfo where Id = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
        @SuppressWarnings("unchecked")
		List<VehicleInfo> list = query.list();
        if (list.size() > 0){
        	Optional<VehicleInfo> vehicleInfo = Optional.ofNullable(list.get(0));
     		return vehicleInfo;
        }
        else{
        	return Optional.empty();
        }
	}

	@Override
	public void deleteVehicleColor(int vehicleId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " delete from VehicleColor t where t.vehicleInfo.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, vehicleId); 
		query.executeUpdate();
	}

	@Override
	public void deleteVehicleTexture(int vehicleId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " delete from VehicleTexture t where t.vehicleInfo.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, vehicleId); 
		query.executeUpdate();
	}

	@Override
	public List<VehicleInfo> getDataByBrandId(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " select new VehicleInfo(id,imageUrl1,imageUrl2,productCategory,componentInfo) from VehicleInfo where brandId = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
        @SuppressWarnings("unchecked")
		List<VehicleInfo> list = query.list();
        return list;
	}

	@Override
	public List<VehicleInfo> getDataByCategoryId(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " select new VehicleInfo(id,imageUrl1,imageUrl2,productCategory,componentInfo) from VehicleInfo where categoryId = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
        @SuppressWarnings("unchecked")
		List<VehicleInfo> list = query.list();
        return list;
	}

}
