package com.fdm.coupon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdm.coupon.dal.UserDAO;
import com.fdm.coupon.model.AbsUser;

@Repository
public class UserService {

	private final UserDAO userDAO;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public AbsUser findUserById(long userId) {
		return userDAO.findById(userId);
	}
}
