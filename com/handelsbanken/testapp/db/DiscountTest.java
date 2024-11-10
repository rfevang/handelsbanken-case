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

  @Test
  void testXForBatchPrice() {
    Discount discount = Discount.xForBatchPrice(3, 50);

    assertEquals(200, discount.calculatePrice(2, 100));
    assertEquals(500, discount.calculatePrice(30, 100));
    assertEquals(600, discount.calculatePrice(31, 100));
    assertEquals(530, discount.calculatePrice(31, 30));
  }
}
