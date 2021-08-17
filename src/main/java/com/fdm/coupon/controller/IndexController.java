package com.fdm.coupon.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.coupon.model.AbsUser;
import com.fdm.coupon.service.UserService;

@Controller
public class IndexController {
	
	private final UserService userService;
	
	@Autowired
	public IndexController(UserService userService) {
		this.userService = userService;
	}
	

	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public String goToIndexPage() {
		return "index";
	}
	
	
	@RequestMapping(value={"/", "/index"}, method=RequestMethod.POST)
	public String listAllCoupons(Model model, HttpSession session, @RequestParam Long userId) {
		
		AbsUser user = userService.findUserById(userId);
		
		if (user == null) {
			model.addAttribute("message", "No such user exists. Please try again.");
			return "index";
		}
		
		session.setAttribute("userId", userId);
		return "redirect:/coupons";
	}
}
