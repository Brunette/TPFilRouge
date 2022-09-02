package dal;

import java.util.List;

import bo.Modification;

public interface ModificationDAO {
	List<Modification> selectByCinemaID(int cinemaid);

	void insert(Modification modification);

	Modification selectById(int id);

	void deleteById(int id);
}
