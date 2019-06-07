package daos.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import daos.UserDAO;
import models.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getUserByUserName(String username) {
		Query q = sessionFactory.getCurrentSession().createQuery("from user where username = :username"); 
		q.setParameter("username", username);
		return (User) q.uniqueResult();
	}

	@Override
	public void addNewUser(User username) {
		sessionFactory.getCurrentSession().save(username);
	}
}
