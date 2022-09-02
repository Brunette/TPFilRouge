package bll;

import java.util.List;

import bo.User;
import dal.UserDAO;
import dal.UserDAOHibernateImpl;

public class UserBLL {
	private UserDAO dao;

	public UserBLL() {
		dao = new UserDAOHibernateImpl();
	}

	public List<User> selectAll() {
		return dao.selectAll();
	}

	public User selectById(int id) {
		return dao.selectById(id);
	}

	public User selectByUsername(String name) {
		return dao.selectByUsername(name);
	}

	public User selectBySalleID(int salleid) {
		// TODO Auto-generated method stub
		return dao.selectBySalleID(salleid);
	}

	public void insert(User user) {
		dao.insert(user);
	}

	public void update(User user) {
		dao.update(user);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

}