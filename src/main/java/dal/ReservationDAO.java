package dal;

import java.util.List;

import bo.Reservation;
import bo.User;

public interface ReservationDAO {
	List<Reservation> selectAll();

	List<Reservation> selectByUser(User user);

	Reservation selectById(int id);

	void update(Reservation reservation);

	void deleteById(int id);

	void insert(Reservation reservation);

}
