package com.fdm.coupon.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="COUPONS")
public abstract class AbsCoupon {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long couponId;
	
	protected double value;
	protected int numOfRedemptions;
	
	@ManyToOne
	@JoinColumn(name="fk_userId")
	protected AbsUser user;

	public AbsCoupon() {}
	
	public AbsCoupon(double value, int numOfRedemptions) {
		this.value = value;
		this.numOfRedemptions = numOfRedemptions;
	}
	
	public AbsCoupon(long couponId, double value, int numOfRedemptions) {
		this.couponId = couponId;
		this.value = value;
		this.numOfRedemptions = numOfRedemptions;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getNumOfRedemptions() {
		return numOfRedemptions;
	}

	public void setNumOfRedemptions(int numOfRedemptions) {
		this.numOfRedemptions = numOfRedemptions;
	}

	public AbsUser getUser() {
		return user;
	}

	public void setUser(AbsUser user) {
		this.user = user;
	}

	public long getCouponId() {
		return couponId;
	}
	
	public abstract void redeemOnce();
}
