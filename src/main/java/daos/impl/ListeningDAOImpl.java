package daos.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.ListeningDAO;
import models.Listeninganswer;

@Repository
@Transactional

public class ListeningDAOImpl implements ListeningDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Listeninganswer> getListeningAnswer(int no) {
		Query q = sessionFactory.getCurrentSession().createQuery("from listeninganswer where no = :no order by question"); 
		q.setParameter("no", no);
		return (List<Listeninganswer>) q.list();
	}
}
