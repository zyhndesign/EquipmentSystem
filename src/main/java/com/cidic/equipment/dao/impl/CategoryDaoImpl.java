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

import com.cidic.equipment.dao.CategoryDao;
import com.cidic.equipment.model.Category;

@Repository
@Component
@Qualifier(value = "categoryDaoImpl")
public class CategoryDaoImpl implements CategoryDao {

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
	public int createCategory(Category category) {
		Session session = this.getSessionFactory().getCurrentSession();
		int id = (Integer) session.save(category);
		return id;
	}

	@Override
	public void updateCategory(Category category) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(category);
	}

	@Override
	public void deleteCategory(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " delete from Category c where c.id = ? ";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
		query.executeUpdate();
	}

	@Override
	public List<Category> getCategoryByPage(int offset, int limit) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Category";
		final Query query = session.createQuery(hql); 
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        @SuppressWarnings("unchecked")
		final List<Category> list = query.list(); 
		return list;
	}

	@Override
	public int getCountCategory() {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " select count(c) from Category c"; 
        final Query query = session.createQuery(hql); 
        long count = (Long)query.uniqueResult();
        return (int)count;
	}

	@Override
	public List<Category> getAllCategory() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Category";
		final Query query = session.createQuery(hql);  
        @SuppressWarnings("unchecked")
		final List<Category> list = query.list(); 
		return list;
	}

	@Override
	public Optional<Category> getDataByCategoryId(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Category where Id = ?";
		Query query = session.createQuery(hql);
        query.setParameter(0, id); 
        @SuppressWarnings("unchecked")
		List<Category> list = query.list();
        if (list.size() > 0){
        	Optional<Category> category = Optional.ofNullable(list.get(0));
     		return category;
        }
        else{
        	return Optional.empty();
        }
	}

	@Override
	public Map<Integer, String> getCategoryMap() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = " from Category";
		final Query query = session.createQuery(hql);  
        @SuppressWarnings("unchecked")
		final List<Category> list = query.list(); 
		Map<Integer,String> map = new HashMap<Integer,String>();
		for (Category category : list){
			map.put(category.getId(), category.getName());
		}
		
		return map;
	}

}
