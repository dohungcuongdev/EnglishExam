package daos.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.SpeakingDAO;
import models.Speaking;

@Repository
@Transactional
public class SpeakingDAOImpl implements SpeakingDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Speaking getSpeakingByID(String id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from speaking where id = :id");
		q.setParameter("id", id);
		return (Speaking) q.uniqueResult();
	}

	@Override
	public void addNewSpeaking(Speaking speaking) {
		sessionFactory.getCurrentSession().save(speaking);
	}
	
    @Override
    public void editSpeakingInfor(Speaking speaking) {
		sessionFactory.getCurrentSession().saveOrUpdate(speaking);
    }	

}