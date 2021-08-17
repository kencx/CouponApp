package com.fdm.coupon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="USERS")
public abstract class AbsUser {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long userId;
	
	protected String firstName;
	protected String lastName;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	protected List<AbsCoupon> listOfCoupons = new ArrayList<>();
	
	public AbsUser() {}
		
	public AbsUser(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AbsUser(long userId, String firstName, String lastName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public long getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<AbsCoupon> getAllCoupons() {
		return listOfCoupons;
	}
	
	public void addCoupon(AbsCoupon coupon) {
		listOfCoupons.add(coupon);
		coupon.setUser(this);
	}
	
	public void removeCoupon(AbsCoupon coupon) {
		listOfCoupons.remove(coupon);
		coupon.setUser(null);
	}
}
