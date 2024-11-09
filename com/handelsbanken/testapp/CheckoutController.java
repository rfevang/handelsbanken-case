package com.handelsbanken.testapp;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

	@PostMapping("/checkout")
	CheckoutResponse checkout(@RequestBody List<Integer> items) {
		System.out.println("Checking out");
		return new CheckoutResponse(32.4);
	}
}
