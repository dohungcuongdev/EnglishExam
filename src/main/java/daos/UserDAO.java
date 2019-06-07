package daos;

import models.User;

public interface UserDAO {

	public User getUserByUserName(String username);
	public void addNewUser(User username);
}
