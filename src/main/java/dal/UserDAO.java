package dal;

import java.util.List;

import bo.User;

public interface UserDAO {
	List<User> selectAll();

	User selectById(int id);

	void insert(User user);

	void update(User user);

	void deleteById(int id);

	User selectByUsername(String name);

	User selectBySalleID(int salleid);
}
