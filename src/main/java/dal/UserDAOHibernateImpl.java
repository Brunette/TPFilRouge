package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.User;

public class UserDAOHibernateImpl implements UserDAO {

	private EntityManagerFactory emf;

	public UserDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<User> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createNamedQuery("trouverTousUsers", User.class);
		List<User> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public User selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createNamedQuery("trouverUserById", User.class);
		query.setParameter("id", id);
		User resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void insert(User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();

	}

	@Override
	public void update(User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(user);
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
		Query query = em.createNamedQuery("supprimerUserById");
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
	public User selectByUsername(String name) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createNamedQuery("trouverUserByUsername", User.class);
		query.setParameter("username", name);
		User resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public User selectBySalleID(int salleid) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createNamedQuery("trouverUserBySalle", User.class);
		query.setParameter("salleid", salleid);
		User resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

}
