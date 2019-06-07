package daos.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.WritingDAO;
import models.Writing;

@Repository
@Transactional
public class WritingDAOImpl implements WritingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Writing getWrtingByID(String id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from writing where id = :id"); 
		q.setParameter("id", id);
		return (Writing) q.uniqueResult();
	}
}
