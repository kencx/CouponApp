package com.fdm.coupon.dal;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdm.coupon.model.AbsUser;

@Repository
public class UserDAO extends EntityDAO<AbsUser> {

	@Autowired
	public UserDAO(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public Class<AbsUser> getEntityClass() {
		return AbsUser.class;
	}
}
