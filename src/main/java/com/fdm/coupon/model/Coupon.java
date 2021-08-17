package com.fdm.coupon.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Coupon extends AbsCoupon {
	
	public Coupon() {}
	
	public Coupon(double value, int numOfRedemptions) {
		super(value, numOfRedemptions);
	}

	public Coupon(long couponId, double value, int numOfRedemptions) {
		super(couponId, value, numOfRedemptions);
	}

	@Override
	public void redeemOnce() {
		if (numOfRedemptions <= 0) {
			return;
			// log fully redeemed
		}
		numOfRedemptions -= 1;
	}
	
	@Override
	public String toString() {
		return String.format("coupon[id=%d, value=%f, numOfRedemptions=%d]", couponId, value, numOfRedemptions);
	}

}
