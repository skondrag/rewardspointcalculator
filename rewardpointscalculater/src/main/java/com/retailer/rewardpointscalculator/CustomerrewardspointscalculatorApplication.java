//package com.retailer.rewardpointscalculator;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//@SpringBootApplication
//public class CustomerrewardspointscalculatorApplication {
//
//	public static void main(String[] args) {
//		ConfigurableApplicationContext context=SpringApplication.run(CustomerrewardspointscalculatorApplication.class, args);
//		Object dataSource=context.getBean("dataSource");
//		System.out.println(dataSource);
//
//	}
//
//}
//
//
package com.retailer.rewardpointscalculator;

import com.retailer.rewardpointscalculator.repository.NativeTableInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.*;

@SpringBootApplication
public class CustomerrewardspointscalculatorApplication {
	@Autowired
	NativeTableInsertRepository nativeTableInsertRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerrewardspointscalculatorApplication.class, args);
	}

	@PostConstruct
	void initializeCustomers()  {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\resources\\script.sql"))) {
			bufferedReader.lines().forEach(line -> {
				if (line.contains("INSERT INTO")) {
					nativeTableInsertRepository.insertWithQuery(line);
				}

			});
		} catch (IOException foe) {
			foe.printStackTrace();
		}
	}
}
