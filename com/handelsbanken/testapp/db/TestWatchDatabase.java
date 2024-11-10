package com.handelsbanken.testapp.db;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicates;
import com.google.common.collect.Maps;

/**
 * Implementation of {@link WatchDatabase} used for testing purposes. It holds a fixed list of
 * watches:
 *
 * <p><table>
 *   <tr>
 *     <th>Watch ID</th><th>Watch Name</th><th>Unit Price</th><th>Discount</th>
 *   </tr>
 *   <tr>
 *     <td>001</td><td>Rolex</td><td>100</td><td>3 for 200</td>
 *   </tr>
 *   <tr>
 *     <td>002</td><td>Michael Kors</td><td>80</td><td>2 for 120</td>
 *   </tr>
 *   <tr>
 *     <td>003</td><td>Swatch</td><td>50</td><td></td>
 *   </tr>
 *   <tr>
 *     <td>004</td><td>Casio</td><td>30</td><td></td>
 *   </tr>
 * </table>
 */
public class TestWatchDatabase implements WatchDatabase {
  private final Map<String, Watch> watches;

  public TestWatchDatabase() {
    watches = Map.of(
        "001", new Watch("001", "Rolex", 100, Discount.xForBatchPrice(3, 200)),
        "002", new Watch("002", "Michael Kors", 80, Discount.xForBatchPrice(2, 120)),
        "003", new Watch("003", "Swatch", 50, Discount.NO_DISCOUNT),
        "004", new Watch("004", "Casio", 30, Discount.NO_DISCOUNT));
  }

  @Override
  public Map<String, Watch> getWatches(Set<String> ids) {
    return Maps.filterKeys(watches, Predicates.in(ids));
  }
}
