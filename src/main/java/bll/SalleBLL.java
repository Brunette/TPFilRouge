package bll;

import java.util.List;

import bo.Salle;
import dal.SalleDAO;
import dal.SalleDAOHibernateImpl;

public class SalleBLL {
	private SalleDAO dao;

	public SalleBLL() {
		dao = new SalleDAOHibernateImpl();
	}

	public List<Salle> selectByCinema(int cinemaid) {
		return dao.selectByCinema(cinemaid);
	}

	public Salle selectById(int id) {
		return dao.selectById(id);
	}
}
