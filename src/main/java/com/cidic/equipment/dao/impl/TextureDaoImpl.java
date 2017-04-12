package com.cidic.equipment.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.equipment.model.Texture;
import com.cidic.equipment.dao.TextureDao;

@Repository
@Component
@Qualifier(value = "textureDaoImpl")
public class TextureDaoImpl implements TextureDao {

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
	public void createTexture(Texture texture) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(texture);
	}

	@Override
	public void updateTexture(Texture texture) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(texture);		
	}

	@Override
	public void deleteTexture(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " delete from Texture t where t.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
		query.executeUpdate();
	}

	@Override
	public List<Texture> getTextureByPage(int offset, int limit) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Texture ";
		final Query query = session.createQuery(hql); 
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		final List<Texture> list = query.list(); 
		return list;
	}

	@Override
	public int getCountTexture() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(t) from Texture t"; 
        final Query query = session.createQuery(hql); 
        return (Integer)query.uniqueResult();
	}

	@Override
	public List<Texture> getAllTexture() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Texture";
		final Query query = session.createQuery(hql);  
        @SuppressWarnings("unchecked")
		final List<Texture> list = query.list(); 
		return list;
	}

}
