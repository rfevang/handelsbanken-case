package com.handelsbanken.testapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CheckoutControllerTest {

  private final CheckoutController controller = new CheckoutController();

  @Test
  void testCheckout() {
    assertEquals(360, controller.checkout(List.of("001", "002", "001", "004", "003")).price());
  }

  @Test
  void testCheckout_discountApplied() {
    assertEquals(300, controller.checkout(List.of("001", "001", "001", "001")).price());
    assertEquals(240, controller.checkout(List.of("002", "002", "002", "002")).price());
  }

  @Test
  void testCheckout_invalidWatchId() {
    ResponseStatusException e = assertThrows(
        ResponseStatusException.class,
        () -> controller.checkout(List.of("111")));
    assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
  }
}
