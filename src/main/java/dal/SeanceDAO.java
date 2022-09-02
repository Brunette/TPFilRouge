package dal;

import java.time.LocalDate;
import java.util.List;

import bo.Cinema;
import bo.Film;
import bo.Salle;
import bo.Seance;

public interface SeanceDAO {
	List<Seance> selectAll();

	List<Seance> selectByCinema(Cinema cinema);

	List<Seance> selectByCinemaAndDay(Cinema cinema, LocalDate date);

	List<Seance> selectByCinemaFilmDay(Cinema cinema, Film film, LocalDate date);

	List<Seance> selectByCinemaFilm(int cinemaid, Film film);

	Seance selectById(int id);

	void insert(Seance seance);

	void update(Seance seance);

	void deleteById(int id);

	List<Seance> selectBySalle(Salle salle);

}
