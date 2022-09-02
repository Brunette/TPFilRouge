package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.Modification;

public class ModificationDAOHibernateImpl implements ModificationDAO {

	private EntityManagerFactory emf;

	public ModificationDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Modification> selectByCinemaID(int cinemaid) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Modification> query = em.createNamedQuery("trouverModifByCinema", Modification.class);
		query.setParameter("cinemaid", cinemaid);
		List<Modification> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public void insert(Modification modification) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(modification);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();
	}

	@Override
	public Modification selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Modification> query = em.createNamedQuery("trouverModificationById", Modification.class);
		query.setParameter("id", id);
		Modification resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void deleteById(int id) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("supprimerModificationById");
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
