package com.springbootdemo.clientservice.business.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.springbootdemo.clientservice.business.model.Client;

/**
 * 
 */
public class ClientServiceImplTest {
	
	private ClientService service;
		
	@Before
	public void setUp() {
	
		service = new ClientServiceImpl();
	}
	
	@Test
	public void testFindClientWithValidID() {
		
		Client actual = service.findClient("1");
		
		assertNotNull(actual);
		assertThat(actual.getId(), is("1"));
		assertThat(actual.getLastName(), is("Wiggins"));
		assertThat(actual.getFirstName(), is("Bob"));
	}
	
	@Test
	public void testFindClientWithInvalidID() {
	
		Client actual = service.findClient("5");
		
		assertNull(actual);
	}
	
	@Test
	public void testFindClientWithNullID() {
	
		Client actual = service.findClient(null);
		
		assertNull(actual);
	}
}
