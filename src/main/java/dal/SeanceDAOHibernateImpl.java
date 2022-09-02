package dal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.Cinema;
import bo.Film;
import bo.Salle;
import bo.Seance;

public class SeanceDAOHibernateImpl implements SeanceDAO {

	private EntityManagerFactory emf;

	public SeanceDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Seance> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Seance> query = em.createNamedQuery("trouverTousSeances", Seance.class);
		List<Seance> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public Seance selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Seance> query = em.createNamedQuery("trouverSeanceById", Seance.class);
		query.setParameter("id", id);
		Seance resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void insert(Seance seance) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(seance);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();

	}

	@Override
	public void update(Seance seance) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(seance);
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
		Query query = em.createNamedQuery("supprimerSeanceById");
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

	@Override
	public List<Seance> selectByCinema(Cinema cinema) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Seance> query = em.createNamedQuery("trouverSeanceByCinema", Seance.class);
		query.setParameter("cinemaid", cinema.getId());
		List<Seance> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public List<Seance> selectByCinemaAndDay(Cinema cinema, LocalDate date) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Seance> query = em.createNamedQuery("trouverSeanceByCinemaDay", Seance.class);
		query.setParameter("cinemaid", cinema.getId());
		query.setParameter("date", java.util.Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		List<Seance> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public List<Seance> selectByCinemaFilmDay(Cinema cinema, Film film, LocalDate date) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Seance> query = em.createNamedQuery("trouverSeanceByCinemaDayFilm", Seance.class);
		query.setParameter("cinemaid", cinema.getId());
		query.setParameter("date", java.util.Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		query.setParameter("filmid", film.getId());
		List<Seance> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public List<Seance> selectByCinemaFilm(int cinemaid, Film film) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Seance> query = em.createNamedQuery("trouverSeanceByCinemaFilm", Seance.class);
		query.setParameter("cinemaid", cinemaid);
		query.setParameter("filmid", film.getId());
		List<Seance> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public List<Seance> selectBySalle(Salle salle) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Seance> query = em.createNamedQuery("trouverSeanceBySalle", Seance.class);
		query.setParameter("salleid", salle.getId());
		List<Seance> resultat = query.getResultList();
		em.close();
		return resultat;
	}

}
