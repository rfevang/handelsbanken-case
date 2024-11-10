package com.handelsbanken.testapp.db;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Handles applying discounts for purchases of a number of units of the same type.
 */
public interface Discount {

  /**
   * Calculates the price of a set of units after applying the discount.
   *
   * @param units the number of units being purchased.
   * @param pricePerUnitBeforeDiscount the price of a single unit without discount
   * @return the price after applying the discount.
   */
  int calculatePrice(int units, int pricePerUnitBeforeDiscount);

  /** There is no discount. */
  public static final Discount NO_DISCOUNT = new Discount() {
    @Override
    public int calculatePrice(int units, int pricePerUnitBeforeDiscount) {
      return units * pricePerUnitBeforeDiscount;
    }
  };

  /**
   * Returns a discount of the form "buy X units for Y price". Any units being purchased that
   * don't fit into a whole number of packages will be charged full price.
   *
   * <p>For instance, if the normal price of an item is 500, but there is a 3 for 750 discount,
   * the price for buying 5 will be 1750 (750 for the first 3, then 1000 for the last two).
   * Buying 6 would have cost 1500.
   *
   * @param units the number of units needed to trigger the discount
   * @param combinedPrice the price for buying {@code units} units.
   * @return the {@code Discount} object representing the discount.
   */
  public static Discount xForBatchPrice(int unitsPerDiscount, int batchPrice) {
    // Non-positive batch size doesn't make sense.
    checkArgument(unitsPerDiscount > 0);
    return new Discount() {
      @Override
      public int calculatePrice(int units, int pricePerUnitBeforeDiscount) {
        // Negative number of units not supported.
        checkArgument(units >= 0);
        int discountBatches = units / unitsPerDiscount;
        int fullPriceUnits = units % unitsPerDiscount;
        return discountBatches * batchPrice
            + fullPriceUnits * pricePerUnitBeforeDiscount;
      }
    };
  }
}
