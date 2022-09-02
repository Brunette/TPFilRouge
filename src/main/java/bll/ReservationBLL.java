package bll;

import java.util.List;

import bo.Reservation;
import bo.User;
import dal.ReservationDAO;
import dal.ReservationDAOHibernateImpl;

public class ReservationBLL {
	private ReservationDAO dao;

	public ReservationBLL() {
		dao = new ReservationDAOHibernateImpl();
	}

	public List<Reservation> selectAll() {
		return null;
	}

	public Reservation selectById(int id) {
		return null;
	}

	public List<Reservation> selectByUser(User user) {
		return dao.selectByUser(user);
	}

	public void update(Reservation reservation) {

	}

	public void deleteById(int id) {

	}

	public void insert(Reservation reservation) {
		dao.insert(reservation);
	}
}
