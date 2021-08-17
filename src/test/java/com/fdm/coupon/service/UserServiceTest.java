package com.fdm.coupon.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.coupon.dal.UserDAO;
import com.fdm.coupon.model.AbsUser;
import com.fdm.coupon.model.User;

public class UserServiceTest {

	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private UserService userService;
	
	private User u1;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		u1 = new User(0, "john", "adams");
	}
	
	
	@Test
	public void findUserByIdTest() {
		
		long id = u1.getUserId();
		when(userDAO.findById(id)).thenReturn(u1);
		AbsUser user = userService.findUserById(id);
		
		verify(userDAO, times(1)).findById(id);
		assertEquals(u1.getFirstName(), user.getFirstName());
	}
}
