package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import bo.Genre;

public class GenreDAOHibernateImpl implements GenreDAO {

	private EntityManagerFactory emf;

	public GenreDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Genre> selectUsed() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Genre> query = em.createNamedQuery("trouverTousGenresUsed", Genre.class);
		List<Genre> resultat = query.getResultList();
		em.close();
		return resultat;
	}

}
