package com.springbootdemo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.springbootdemo.clientservice.business.model.Client;
import com.springbootdemo.clientservice.business.service.ClientService;
import com.springbootdemo.web.resource.ClientResource;


@RestController
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientService clientService;	
	
	@RequestMapping(value="clients/{clientId}/", method=RequestMethod.GET)
	@Timed
	public ClientResource findClient(@PathVariable String clientId) {
		
		logger.debug(String.format("finding client with ID: %s", clientId));
		
		// really simple implementation just to show that this class gets called
		// for the correct request mapping
		Client client = clientService.findClient(clientId);
				
		if (client != null) {
			ClientResource res = new ClientResource(client.getId(), client.getLastName(), client.getFirstName());
			return res;
		}
		
		throw new ResourceNotFoundException();
	}
}
