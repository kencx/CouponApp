package com.fdm.coupon.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class EntityDAO<T> {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;

	@Autowired
	public EntityDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public void add(T t) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(t);
		et.commit();
		em.close();
	}
	
	public void delete(long id) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		T t = em.find(getEntityClass(), id);
		em.remove(t);
		et.commit();
		em.close();
	}	
	
	public T findById(long id) {
		em = emf.createEntityManager();
		T t = em.find(getEntityClass(), id);
		em.close();
		return t;
	}
	
	
	public List<T> findAll() {
		em = emf.createEntityManager();
		Class<T> entityClass = getEntityClass();
		TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass);
		List<T> listOfT = query.getResultList();

		em.close();
		return listOfT;
	}
	
	
	/**
	 * Gets the actual class of given type argument T.
	 * @return Class of type argument T
	 */
	protected abstract Class<T> getEntityClass();
}
