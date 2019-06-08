package daos.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.ReadingDAO;
import models.Reading;
import models.Readinganswer;

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

	@Override
	public List<Readinganswer> getReadingAnswer(int no) {
		Query q = sessionFactory.getCurrentSession().createQuery("from readinganswer where no = :no order by question"); 
		q.setParameter("no", no);
		return (List<Readinganswer>) q.list();
	}
}
