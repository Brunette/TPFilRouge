package bll;

import java.time.LocalDate;
import java.util.List;

import bo.Cinema;
import bo.Film;
import bo.Salle;
import bo.Seance;
import dal.SeanceDAO;
import dal.SeanceDAOHibernateImpl;

public class SeanceBLL {
	private SeanceDAO dao;

	public SeanceBLL() {
		dao = new SeanceDAOHibernateImpl();
	}

	public List<Seance> selectAll() {
		return dao.selectAll();
	}

	public List<Seance> selectByCinema(Cinema cinema) {
		return dao.selectByCinema(cinema);
	}

	public List<Seance> selectByCinemaAndDay(Cinema cinema, LocalDate date) {
		return dao.selectByCinemaAndDay(cinema, date);
	}

	public List<Seance> selectByCinemaFilmDay(Cinema cinema, Film film, LocalDate date) {
		return dao.selectByCinemaFilmDay(cinema, film, date);
	}

	public List<Seance> selectByCinemaFilm(int cinemaid, Film film) {
		return dao.selectByCinemaFilm(cinemaid, film);
	}

	public void insert(Seance seance) {
		dao.insert(seance);
	}

	public void update(Seance seance) {
		dao.update(seance);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public List<Seance> selectBySalle(Salle salle) {
		return dao.selectBySalle(salle);
	}

	public Seance selectById(int intValue) {
		return dao.selectById(intValue);
	}
}
