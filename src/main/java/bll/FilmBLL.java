package bll;

import java.util.List;

import bo.Cinema;
import bo.Film;
import dal.FilmDAO;
import dal.FilmDAOHibernateImpl;

public class FilmBLL {
	private FilmDAO dao;

	public FilmBLL() {
		dao = new FilmDAOHibernateImpl();
	}

	public List<Film> selectAll() {
		return dao.selectAll();
	}

	public Film selectById(int id) {
		return dao.selectById(id);
	}

	public void insert(Film film) {
		dao.insert(film);
	}

	public void update(Film film) {
		dao.update(film);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public List<Film> selectByGenre(List<String> genreNames) {
		// TODO Auto-generated method stub
		return dao.selectByGenre(genreNames);
	}

	public List<Film> selectByCinema(Cinema cinema) {
		return dao.selectByCinema(cinema);
	}
}