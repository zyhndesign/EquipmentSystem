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

import com.cidic.equipment.dao.ColorDao;
import com.cidic.equipment.model.Color;

@Repository
@Component
@Qualifier(value = "colorDaoImpl")
public class ColorDaoImpl implements ColorDao {

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
	public void createColor(Color color) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(color);
	}

	@Override
	public void updateColor(Color color) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(color);
	}

	@Override
	public void deleteColor(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " delete from Color c where c.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
		query.executeUpdate();
	}

	@Override
	public List<Color> getColorByPage(int offset, int limit) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Color ";
		final Query query = session.createQuery(hql); 
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		final List<Color> list = query.list(); 
		return list;
	}

	@Override
	public int getCountColor() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(c) from Color c"; 
        final Query query = session.createQuery(hql); 
        return (Integer)query.uniqueResult();
	}

	@Override
	public List<Color> getAllColor() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Color";
		final Query query = session.createQuery(hql);  
        @SuppressWarnings("unchecked")
		final List<Color> list = query.list(); 
		return list;
	}

	@Override
	public Optional<Color> getDataByColorId(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Color where Id = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
        @SuppressWarnings("unchecked")
		List<Color> list = query.list();
        if (list.size() > 0){
        	Optional<Color> color = Optional.ofNullable(list.get(0));
     		return color;
        }
        else{
        	return Optional.empty();
        }
	}

}
