package services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.UserDAO;
import models.User;
import services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserDAO userDAO;
	
	@Override
	public User getUserByUserName(String username) {
		return userDAO.getUserByUserName(username);
	}
}
