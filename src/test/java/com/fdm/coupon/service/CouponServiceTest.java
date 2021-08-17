package com.fdm.coupon.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.coupon.dal.CouponDAO;
import com.fdm.coupon.model.AbsCoupon;
import com.fdm.coupon.model.Coupon;
import com.fdm.coupon.model.User;

public class CouponServiceTest {

	@Mock
	private UserService userService;
	
	@Mock
	private CouponDAO couponDAO;
	
	@InjectMocks
	private CouponService couponService;
	
	private User u1;
	private Coupon c1, c2;
	private List<AbsCoupon> listOfCoupons = new ArrayList<>();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		u1 = new User(0, "john", "adams");
		c1 = new Coupon(0, 25, 1);
		c2 = new Coupon(1, 25, 3);
		
		listOfCoupons.add(c1);
		listOfCoupons.add(c2);
		u1.addCoupon(c1);
		u1.addCoupon(c2);
	}
	
	@Test
	public void getAllCouponsTest() {
		
		long id = u1.getUserId();
		when(userService.findUserById(id)).thenReturn(u1);
		
		List<AbsCoupon> resultList = couponService.getAllCoupons(id);
		verify(userService, times(1)).findUserById(id);
		assertTrue(resultList.size() == 2);
		assertTrue(resultList.contains(c1));
		assertTrue(resultList.contains(c2));
	}
	
	@Test
	public void redeemCouponSuccessTest() {
		long userId = u1.getUserId();
		long couponId = c1.getCouponId();
		
		when(couponDAO.findById(couponId)).thenReturn(c1);
		
		boolean result = couponService.redeemCoupon(userId, couponId);
		String resultMessage = couponService.getMessage();
		
		verify(couponDAO, times(1)).findById(couponId);
		assertTrue(c1.getNumOfRedemptions() == 0);
		assertEquals("You have successfully redeemed your coupon of $25.0. This coupon has been fully redeemed.", resultMessage);
		assertTrue(result);
	}
	
	
	@Test
	public void redeemCouponSuccessWithRemainingRedemptionsTest() {
		long userId = u1.getUserId();
		long couponId = c2.getCouponId();
		
		when(couponDAO.findById(couponId)).thenReturn(c2);
		
		boolean result = couponService.redeemCoupon(userId, couponId);
		String resultMessage = couponService.getMessage();
		
		verify(couponDAO, times(1)).findById(couponId);
		assertTrue(c2.getNumOfRedemptions() == 2);
		assertEquals("You have successfully redeemed your coupon of $25.0. This coupon has 2 redemptions left.", resultMessage);
		assertTrue(result);
	}
	
	@Test
	public void redeemCouponFailNullTest() {
		long userId = u1.getUserId();
		long couponId = 0;
		
		c1 = null;
		when(couponDAO.findById(couponId)).thenReturn(c1);
		
		boolean result = couponService.redeemCoupon(userId, couponId);
		String resultMessage = couponService.getMessage();
		
		assertEquals("This coupon is invalid!", resultMessage);
		assertFalse(result);
	}

	@Test
	public void redeemCouponFailNotUserTest() {
		long userId = u1.getUserId();
		long couponId = c1.getCouponId();
		
		User u2 = new User(3, "Ben", "Tan");
		c1.setUser(u2);
		when(couponDAO.findById(couponId)).thenReturn(c1);
		
		boolean result = couponService.redeemCoupon(userId, couponId);
		String resultMessage = couponService.getMessage();
		
		verify(couponDAO, times(1)).findById(couponId);
		assertEquals("This coupon does not belong to you!", resultMessage);
		assertFalse(result);
	}
	
	@Test
	public void redeemCouponFailFullyRedeemedTest() {
		long userId = u1.getUserId();
		long couponId = c1.getCouponId();
		
		c1.setNumOfRedemptions(0);
		when(couponDAO.findById(couponId)).thenReturn(c1);
		
		boolean result = couponService.redeemCoupon(userId, couponId);
		String resultMessage = couponService.getMessage();
		
		verify(couponDAO, times(1)).findById(couponId);
		assertEquals("This coupon has been fully redeemed!", resultMessage);
		assertFalse(result);
	}
}
