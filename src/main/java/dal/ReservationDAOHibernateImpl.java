package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.Reservation;
import bo.User;

public class ReservationDAOHibernateImpl implements ReservationDAO {
	private EntityManagerFactory emf;

	public ReservationDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Reservation> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Reservation> query = em.createNamedQuery("trouverTousReservations", Reservation.class);
		List<Reservation> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public List<Reservation> selectByUser(User user) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Reservation> query = em.createNamedQuery("trouverReservationByUser", Reservation.class);
		query.setParameter("userid", user.getId());
		List<Reservation> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public Reservation selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Reservation> query = em.createNamedQuery("trouverReservationById", Reservation.class);
		query.setParameter("id", id);
		Reservation resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void update(Reservation reservation) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(reservation);
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
		Query query = em.createNamedQuery("supprimerReservationById");
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
	public void insert(Reservation reservation) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(reservation);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();
	}

}
