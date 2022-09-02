package dal;

import java.util.List;

import bo.Salle;

public interface SalleDAO {

	List<Salle> selectAll();

	Salle selectById(int id);

	void insert(Salle salle);

	void update(Salle salle);

	void deleteById(int id);

	List<Salle> selectByCinema(int cinemaId);

}
