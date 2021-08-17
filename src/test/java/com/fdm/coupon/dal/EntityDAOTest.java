package com.fdm.coupon.dal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.coupon.model.AbsUser;
import com.fdm.coupon.model.User;


public class EntityDAOTest {

	@Mock
	private EntityManagerFactory emf;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private EntityTransaction et;

	@InjectMocks
	private UserDAO userDAO;
	private User u1;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		when(emf.createEntityManager()).thenReturn(em);
		when(em.getTransaction()).thenReturn(et);
		
		doNothing().when(et).begin();
		doNothing().when(et).commit();
		doNothing().when(em).close();
		
		u1 = new User();
	}
	
	@Test
	public void addEntityTest() {
		doNothing().when(em).persist(u1);
		userDAO.add(u1);
		
		verify(et, times(1)).begin();
		verify(em, times(1)).persist(u1);
		verify(et, times(1)).commit();
		verify(em, times(1)).close();
	}
	
	@Test
	public void deleteEntityTest() {
		long id = u1.getUserId();
		when(em.find(AbsUser.class, id)).thenReturn(u1);

		userDAO.delete(id);
		verify(et, times(1)).begin();
		verify(em, times(1)).find(AbsUser.class, id);
		verify(em, times(1)).remove(u1);
		verify(et, times(1)).commit();
		verify(em, times(1)).close();
	}
	
	@Test
	public void findEntityByIdTest() {
		long id = u1.getUserId();
		when(em.find(AbsUser.class, id)).thenReturn(u1);
		
		AbsUser user = userDAO.findById(u1.getUserId());
		assertSame(u1, user);
		verify(em, times(1)).close();
	}
	
	
	@Test
	public void findAllTest() {
		List<AbsUser> users = new ArrayList<>();
		TypedQuery<AbsUser> query = mock(TypedQuery.class);
		
		String queryString = "SELECT e FROM " + AbsUser.class.getName() + " e";
		when(em.createQuery(queryString, AbsUser.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(users);
		
		List<AbsUser> result = userDAO.findAll();
		verify(em, times(1)).createQuery(queryString, AbsUser.class);
		verify(query, times(1)).getResultList();
		assertSame(users, result);
	}
}
