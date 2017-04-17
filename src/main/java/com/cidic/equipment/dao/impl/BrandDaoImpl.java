package com.cidic.equipment.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.equipment.dao.BrandDao;
import com.cidic.equipment.model.Brand;

@Repository
@Component
@Qualifier(value = "brandDaoImpl")
public class BrandDaoImpl implements BrandDao {

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
	public void createBrand(Brand brand) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(brand);
	}

	@Override
	public void updateBrand(Brand brand) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(brand);
	}

	@Override
	public void deleteBrand(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " delete from Brand b where b.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
		query.executeUpdate();
	}

	@Override
	public List<Brand> getBrandByPage(int offset, int limit) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Brand";
		final Query query = session.createQuery(hql); 
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		final List<Brand> list = query.list(); 
		return list;
	}

	@Override
	public int getCountBrand() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(b) from Brand b"; 
        final Query query = session.createQuery(hql); 
        long count = (Long)query.uniqueResult();
        return (int)count;
	}

	@Override
	public List<Brand> getAllBrand() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Brand";
		final Query query = session.createQuery(hql);  
        @SuppressWarnings("unchecked")
		final List<Brand> list = query.list(); 
		return list;
	}

	@Override
	public Optional<Brand> getDataByBrandId(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Brand where Id = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
        @SuppressWarnings("unchecked")
		List<Brand> list = query.list();
        if (list.size() > 0){
        	Optional<Brand> user = Optional.ofNullable(list.get(0));
     		return user;
        }
        else{
        	return Optional.empty();
        }
	}

	@Override
	public Map<Integer, String> getBrandMap() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Brand";
		final Query query = session.createQuery(hql);  
        @SuppressWarnings("unchecked")
		final List<Brand> list = query.list(); 
		Map<Integer,String> map = new HashMap<Integer,String>();
		for (Brand brand : list){
			map.put(brand.getId(), brand.getName());
		}
		
		return map;
	}

}
