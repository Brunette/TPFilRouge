package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.Cinema;
import bo.Film;

public class FilmDAOHibernateImpl implements FilmDAO {

	private EntityManagerFactory emf;

	public FilmDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Film> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Film> query = em.createNamedQuery("trouverTousFilms", Film.class);
		List<Film> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public List<Film> selectByGenre(List<String> genreNames) {
		EntityManager em = emf.createEntityManager();
		String queryStr = "SELECT f FROM Film f JOIN f.genres g WHERE ";
		for (int i = 0; i < genreNames.size(); i++) {
			System.out.println(genreNames.get(i));
			queryStr += "g.name = :name" + i;
			if (i != genreNames.size() - 1)
				queryStr += " OR ";
		}
		TypedQuery<Film> query = em.createQuery(queryStr, Film.class);
		for (int i = 0; i < genreNames.size(); i++) {
			query.setParameter("name" + i, genreNames.get(i));
		}
		List<Film> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public List<Film> selectByCinema(Cinema cinema) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Film> query = em.createNamedQuery("trouverFilmsByCinema", Film.class);
		query.setParameter("cinemaid", cinema.getId());
		List<Film> resultat = query.getResultList();
		em.close();
		return resultat;

	}

	@Override
	public Film selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Film> query = em.createNamedQuery("trouverFilmById", Film.class);
		query.setParameter("id", id);
		Film resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void insert(Film film) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(film);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();

	}

	@Override
	public void update(Film film) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(film);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();
	}

	@Override
	public void deleteById(int id) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("supprimerFilmById");
		query.setParameter("id", id);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();

	}

}
