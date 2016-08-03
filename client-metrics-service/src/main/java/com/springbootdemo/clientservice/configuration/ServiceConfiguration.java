package com.springbootdemo.clientservice.configuration;

import java.util.concurrent.TimeUnit;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import com.springbootdemo.clientservice.business.service.ClientService;
import com.springbootdemo.clientservice.business.service.ClientServiceImpl;
import com.springbootdemo.clientservice.util.metrics.HttpConnectionPoolMetrics;

@Configuration
@EnableMetrics
public class ServiceConfiguration extends MetricsConfigurerAdapter {
	
	@Bean
	public ClientService clientService() {
		return new ClientServiceImpl();
	}
	
	 @Override
	 public void configureReporters(MetricRegistry metricRegistry) {
		 // registerReporter allows the MetricsConfigurerAdapter to
		 // shut down the reporter when the Spring context is closed
		 registerReporter(ConsoleReporter.forRegistry(metricRegistry).build()).start(1, TimeUnit.MINUTES);		 
	 }

	 @Bean
	 public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		 // this will be used to demonstrate using a Gauge. in a real service we would externalize the
		 // configuration and use create a CloseableHttpClient that uses this pool manager, etc.
		 PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
		 connMgr.setMaxTotal(200);
		 connMgr.setDefaultMaxPerRoute(20);
		 
		 return connMgr;
	 }
	 
	 @Bean
	 public HttpConnectionPoolMetrics HttpConnectionPoolMetrics() {
		 return new HttpConnectionPoolMetrics();
	 }
}
