package com.handelsbanken.testapp;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {

	@PostMapping("/checkout")
	void checkout(@RequestBody List<Integer> items) {
		System.out.println("Checking out");
	}
}
