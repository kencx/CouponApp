package com.fdm.coupon.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.coupon.model.AbsCoupon;
import com.fdm.coupon.service.CouponService;

@Controller
public class RedemptionController {

	private final CouponService couponService;
	
	@Autowired
	public RedemptionController(CouponService couponService) {
		this.couponService = couponService;
	}
	
	
	@RequestMapping(value="/coupons", method=RequestMethod.GET)
	public String goToCouponsPage(HttpSession session) {
		
		long userId = (long) session.getAttribute("userId");
		List<AbsCoupon> listOfCoupons = couponService.getAllCoupons(userId);
		
		session.setAttribute("listOfCoupons", listOfCoupons);
		return "coupons";
	}
	
	
	@RequestMapping(value="/coupons", method=RequestMethod.POST)
	public String redeemCoupon(HttpSession session, 
								@RequestParam(required = false) Long couponId) {
		
		long userId = (long) session.getAttribute("userId");
		
		if (couponId != null) {
			couponService.redeemCoupon(userId, couponId);
			String message = couponService.getMessage();
			session.setAttribute("message", message);
		}
		
		return "redirect:/coupons";
	}
}
