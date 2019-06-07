package daos.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.ReadingDAO;
import models.Reading;

@Repository
@Transactional

public class ReadingDAOImpl implements ReadingDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Reading getReadingById(String id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from reading where id = :id");
		q.setParameter("id", id);
		return (Reading) q.uniqueResult();
	}
	@Override
	public void addNewReading(Reading reading) {
		sessionFactory.getCurrentSession().save(reading);
	}

}
