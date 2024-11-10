package com.handelsbanken.testapp.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TestWatchDatabaseTest {
  private final TestWatchDatabase database = new TestWatchDatabase();

  @Test
  void testGetWatch() {
    Optional<Watch> maybeWatch = database.getWatch("003");
    assertTrue(maybeWatch.isPresent());

    Watch watch = maybeWatch.get();
    assertEquals("003", watch.id());
  }

  @Test
  void testGetWatch_missing() {
    Optional<Watch> maybeWatch = database.getWatch("010");
    assertTrue(maybeWatch.isEmpty());
  }

  @Test
  void testGetWatches() {
    Map<String, Watch> watches = database.getWatches(Set.of("002", "004"));
    assertEquals(2, watches.size());
    assertTrue(watches.containsKey("002"));
    assertEquals("002", watches.get("002").id());
    assertTrue(watches.containsKey("004"));
    assertEquals("004", watches.get("004").id());
  }

  @Test
  void testGetWatches_ignoresUnknown() {
    Map<String, Watch> watches = database.getWatches(Set.of("111", "002", "222", "004", "333"));
    assertEquals(2, watches.size());
    assertTrue(watches.containsKey("002"));
    assertEquals("002", watches.get("002").id());
    assertTrue(watches.containsKey("004"));
    assertEquals("004", watches.get("004").id());
  }
}
