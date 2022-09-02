package bll;

import java.util.List;

import bo.Cinema;
import dal.CinemaDAO;
import dal.CinemaDAOHibernateImpl;

public class CinemaBLL {
	private CinemaDAO dao;

	public CinemaBLL() {
		dao = new CinemaDAOHibernateImpl();
	}

	public List<Cinema> selectAll() {
		return dao.selectAll();
	}

	public Cinema selectById(int id) {
		return dao.selectById(id);
	}

	void insert(Cinema cinema) {
		dao.insert(cinema);
	}

	void update(Cinema cinema) {
		dao.update(cinema);
	}

	void deleteById(int id) {
		dao.deleteById(id);
	}

	public List<Cinema> selectByCritere(String filter) {
		// TODO Auto-generated method stub
		return dao.selectByCritere(filter);
	}

}
