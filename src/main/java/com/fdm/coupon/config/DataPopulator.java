package com.fdm.coupon.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdm.coupon.dal.CouponDAO;
import com.fdm.coupon.dal.UserDAO;
import com.fdm.coupon.model.Coupon;
import com.fdm.coupon.model.User;

@Repository
public class DataPopulator {
	
	private final UserDAO userDAO;
	private final CouponDAO couponDAO;
	
	@Autowired
	public DataPopulator(UserDAO userDAO, CouponDAO couponDAO) {
		this.userDAO = userDAO;
		this.couponDAO = couponDAO;
	}
	
	@PostConstruct
	public void run() {

		User u1 = new User("John", "Lim");
		User u2 = new User("Ben", "Tan");
		User u3 = new User("Matt", "Adams");

		Coupon c1 = new Coupon(20.0, 5);
		Coupon c2 = new Coupon(10.0, 3);
		Coupon c3 = new Coupon(50.0, 1);
		Coupon c4 = new Coupon(10.0, 5);
		Coupon c5 = new Coupon(20.0, 4);
		Coupon c6 = new Coupon(30.0, 3);
		Coupon c7 = new Coupon(40.0, 2);
		Coupon c8 = new Coupon(50.0, 1);

		u1.addCoupon(c1);
		u1.addCoupon(c2);
		u1.addCoupon(c3);

		u2.addCoupon(c4);
		u2.addCoupon(c5);
		
		u3.addCoupon(c6);
		u3.addCoupon(c7);
		u3.addCoupon(c8);
		
		userDAO.add(u1);
		userDAO.add(u2);
		userDAO.add(u3);
		couponDAO.add(c1);
		couponDAO.add(c2);
		couponDAO.add(c3);
		couponDAO.add(c4);
		couponDAO.add(c5);
		couponDAO.add(c6);
		couponDAO.add(c7);
		couponDAO.add(c8);
	}
}
