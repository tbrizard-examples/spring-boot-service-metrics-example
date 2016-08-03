package com.springbootdemo.clientservice.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.Timed;
import com.springbootdemo.clientservice.business.model.Client;

/**
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Timed
	public Client findClient(String clientId) {
		
		logger.debug(String.format("finding client with ID: %s", clientId));
		
		// incredibly fake code here just to illustrate that this class gets called
		if ("1".equalsIgnoreCase(clientId)) {
			return new Client("1", "Wiggins", "Bob");
		} else if ("2".equalsIgnoreCase(clientId)) {
			return new Client("2", "Falcon", "Scott");
		}
		
		return null;
	}
}
