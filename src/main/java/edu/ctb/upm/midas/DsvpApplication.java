package edu.ctb.upm.midas;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableFeignClients
//@EnableCircuitBreaker
//@EnableHystrix
//@EnableDiscoveryClient
@EnableScheduling
public class DsvpApplication  implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(DsvpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//cerateEmployee();
		//getEmployeeById();
		//employeeDAO.updateEmployeeEmailById(3, "kishan.cs2111@gamil.com");
		//employeeDAO.deleteEmployeeById(3);

		System.out.println("DataSource==="+dataSource);
	}

	@Value("${spring.datasource.url}")
	private String spring_datasource_url;
	@Value("${spring.datasource.username}")
	private String spring_datasource_username;
	@Value("${spring.datasource.password}")
	private String spring_datasource_password;

	@PostConstruct
	public void setup(){
		System.out.println(
				"MySQL Variables: URL: " + this.spring_datasource_url + "\n" +
						"USERNAME: " + this.spring_datasource_username + "\n" +
						"PASS: " + this.spring_datasource_password);
	}
}
