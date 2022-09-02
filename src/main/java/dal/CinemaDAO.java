package dal;

import java.util.List;

import bo.Cinema;

public interface CinemaDAO {
	List<Cinema> selectAll();

	Cinema selectById(int id);

	void insert(Cinema cinema);

	void update(Cinema cinema);

	void deleteById(int id);

	List<Cinema> selectByCritere(String filter);

}
