package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SimpleTest2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SimpleTest2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate rest = new RestTemplate();
		
		Map<String,Map<String,String>> topMap = new HashMap<>();
		Map<String,String> innerMap = new HashMap<>();
		innerMap.put("Stranger in a stanger land", "IDE_..._STRING_FPE");
		topMap.put("bookName", innerMap);
		
		//preparing second request
		innerMap = new HashMap<>();
		innerMap.put("Robert Anson Heinlein", "IDE_..._STRING_FPE");
		topMap.put("authorName", innerMap);
		
		ResponseEntity<Map> response = rest.postForEntity("http://localhost:8080/rest/voltage", topMap, Map.class);
		
		response.getBody().forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
		
	}

}
