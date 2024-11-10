package com.handelsbanken.testapp.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DiscountTest {

  @Test
  void testNoDiscount() {
    Discount discount = Discount.NO_DISCOUNT;
    // Large volume
    assertEquals(15000, discount.calculatePrice(5000, 3));
    // No items
    assertEquals(0, discount.calculatePrice(0, 15));
    // Free
    assertEquals(0, discount.calculatePrice(30, 0));
  }
}
