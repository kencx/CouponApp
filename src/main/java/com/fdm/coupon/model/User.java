package com.fdm.coupon.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class User extends AbsUser {

	public User() {}

	public User(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	public User(long userId, String firstName, String lastName) {
		super(userId, firstName, lastName);
	}

	@Override
	public String toString() {
		return String.format("user[%s %s]", userId, firstName, lastName);
	}
}
