package edu.ctb.upm.midas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableDiscoveryClient
@EnableScheduling
public class DsvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsvpApplication.class, args);
	}
}