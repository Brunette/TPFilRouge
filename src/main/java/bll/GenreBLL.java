package bll;

import java.util.List;

import bo.Genre;
import dal.GenreDAO;
import dal.GenreDAOHibernateImpl;

public class GenreBLL {
	private GenreDAO dao;

	public GenreBLL() {
		dao = new GenreDAOHibernateImpl();
	}

	public List<Genre> selectUsed() {
		return dao.selectUsed();
	}
}
