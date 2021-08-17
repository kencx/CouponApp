package com.fdm.coupon.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdm.coupon.model.AbsCoupon;

@Repository
public class CouponDAO extends EntityDAO<AbsCoupon> {

	@Autowired
	public CouponDAO(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public Class<AbsCoupon> getEntityClass() {
		return AbsCoupon.class;
	}
	
	public void updateCoupon(AbsCoupon coupon) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(coupon);
		et.commit();
		em.close();
	}
}
