package dal;

import java.util.List;

import bo.Cinema;
import bo.Film;

public interface FilmDAO {
	List<Film> selectAll();

	List<Film> selectByCinema(Cinema cinema);

	Film selectById(int id);

	void insert(Film film);

	void update(Film film);

	void deleteById(int id);

	List<Film> selectByGenre(List<String> genreNames);
}
