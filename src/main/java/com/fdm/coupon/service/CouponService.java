package com.fdm.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.coupon.dal.CouponDAO;
import com.fdm.coupon.model.AbsCoupon;
import com.fdm.coupon.model.AbsUser;

@Service
public class CouponService {

	private String message = null;
	private final UserService userService;
	private final CouponDAO couponDAO;
	
	@Autowired
	public CouponService(UserService userService, CouponDAO couponDAO) {
		this.userService = userService;
		this.couponDAO = couponDAO;
	}
	
	
	public boolean redeemCoupon(long userId, long couponID) {
		
		AbsCoupon coupon = couponDAO.findById(couponID);
		
		if (coupon == null) {
			message = "This coupon is invalid!";
			return false;
			
		} else if (coupon.getUser().getUserId() != userId) {
			message = "This coupon does not belong to you!";
			return false;
			
		} else if (coupon.getNumOfRedemptions() <= 0) {
			message = "This coupon has been fully redeemed!";
			return false;
		}
				
		coupon.redeemOnce();
		couponDAO.updateCoupon(coupon);
		message = "You have successfully redeemed your coupon of $" + coupon.getValue() + ". ";
		
		if (coupon.getNumOfRedemptions() <= 0) {
			message += "This coupon has been fully redeemed.";
		} else {
			message += "This coupon has " + coupon.getNumOfRedemptions() + " redemptions left.";
		}
		
		return true;
	}
	
	
	public List<AbsCoupon> getAllCoupons(long userId) {
		
		AbsUser user = userService.findUserById(userId);
		return user.getAllCoupons();
	}
	

	public String getMessage() {
		return message;
	}
}
