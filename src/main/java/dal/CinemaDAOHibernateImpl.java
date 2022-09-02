package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.Cinema;

public class CinemaDAOHibernateImpl implements CinemaDAO {

	private EntityManagerFactory emf;

	public CinemaDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Cinema> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Cinema> query = em.createNamedQuery("trouverTousCinemas", Cinema.class);
		List<Cinema> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public Cinema selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Cinema> query = em.createNamedQuery("trouverCinemaById", Cinema.class);
		query.setParameter("id", id);
		Cinema resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void insert(Cinema cinema) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(cinema);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();

	}

	@Override
	public void update(Cinema cinema) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(cinema);
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
		Query query = em.createNamedQuery("supprimerCinemaById");
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
	public List<Cinema> selectByCritere(String filter) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Cinema> query = em.createNamedQuery("trouverCinemaByCritere", Cinema.class);
		query.setParameter("val", filter);
		List<Cinema> resultat = query.getResultList();
		em.close();
		return resultat;
	}
}
