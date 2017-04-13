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
import com.cidic.equipment.model.VehicleInfo;

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
		session.save(vehicleInfo);
	}

	@Override
	public void updateVehicleInfo(VehicleInfo vehicleInfo) {
		Session session = this.getSessionFactory().getCurrentSession();
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
        @SuppressWarnings("unchecked")
		final List<VehicleInfo> list = query.list(); 
		return list;
	}

	@Override
	public int getVehicleInfoCount() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(v) from VehicleInfo v"; 
        final Query query = session.createQuery(hql); 
        return (Integer)query.uniqueResult();
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

}