package com.springbootdemo.clientservice.util.metrics;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.codahale.metrics.annotation.Gauge;

/**
 * A simple example showing how we can use gauges to get instantaneous measures of a 
 * value, like the depth of queue, number of active connections, etc. 
 */
public class HttpConnectionPoolMetrics {

	@Autowired
	private PoolingHttpClientConnectionManager poolingHttpClientConnMgr;
	
	@Gauge
	public int getAvailableConnections() {
		return poolingHttpClientConnMgr.getTotalStats().getAvailable();
	}
	
	@Gauge
	public int getLeasedConnections() {
		return poolingHttpClientConnMgr.getTotalStats().getLeased();
	}
	
	@Gauge
	public int getPendingConnections() {
		return poolingHttpClientConnMgr.getTotalStats().getPending();
	}
}
