package bll;

import java.util.List;

import bo.Modification;
import dal.ModificationDAO;
import dal.ModificationDAOHibernateImpl;

public class ModificationBLL {
	ModificationDAO dao;

	public ModificationBLL() {
		dao = new ModificationDAOHibernateImpl();
	}

	public List<Modification> selectByCinemaID(int cinemaid) {
		return dao.selectByCinemaID(cinemaid);
	}

	public Modification selectById(int id) {
		return dao.selectById(id);
	}

	public void insert(Modification modification) {
		dao.insert(modification);

	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}
}
