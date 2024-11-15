package com.handelsbanken.testapp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.handelsbanken.testapp.db.TestWatchDatabase;
import com.handelsbanken.testapp.db.Watch;
import com.handelsbanken.testapp.db.WatchDatabase;

/**
 * Handles checkout-related REST requests.
 */
@RestController
public class CheckoutController {

  private final WatchDatabase database;

  public CheckoutController() {
    database = new TestWatchDatabase();
  }

  @PostMapping("/checkout")
  CheckoutResponse checkout(@RequestBody List<String> items) {
    Map<String, Long> itemCounts = items.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Map<String, Watch> watches = database.getWatches(itemCounts.keySet());

    int totalPrice = 0;
    for (String id : itemCounts.keySet()) {
      if (!watches.containsKey(id)) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Attempting to check out non existent watch: " + id);
      }
      Watch watch = watches.get(id);
      totalPrice += watch.discount().calculatePrice(itemCounts.get(id).intValue(), watch.price());
    }
    return new CheckoutResponse(totalPrice);
  }
}
