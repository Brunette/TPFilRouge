package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.Salle;

public class SalleDAOHibernateImpl implements SalleDAO {

	private EntityManagerFactory emf;

	public SalleDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Salle> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Salle> query = em.createNamedQuery("trouverTousSalles", Salle.class);
		List<Salle> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public Salle selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Salle> query = em.createNamedQuery("trouverSalleById", Salle.class);
		query.setParameter("id", id);
		Salle resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void insert(Salle salle) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(salle);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();

	}

	@Override
	public void update(Salle salle) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(salle);
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
		Query query = em.createNamedQuery("supprimerSalleById");
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
	public List<Salle> selectByCinema(int cinemaid) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Salle> query = em.createNamedQuery("trouverSallesByCinema", Salle.class);
		query.setParameter("cinemaid", cinemaid);
		List<Salle> resultat = query.getResultList();
		em.close();
		return resultat;
	}

}
