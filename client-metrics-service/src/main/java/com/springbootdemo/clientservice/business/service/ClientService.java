package com.springbootdemo.clientservice.business.service;

import com.springbootdemo.clientservice.business.model.Client;

/**
 *
 */
public interface ClientService {

	Client findClient(String clientId);
}
