package com.aol.alkuznetsov.panda.server.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class offering methods for creating sample objects mainly for testing purposes.
 *
 */
public class CreationUtils {

  /**
   * Return a random element of the supplied array
   * @param <T> the type of array elements
   * @param arr the array
   * @return a random element of the array
   */
  public static <T> T getRandomElement(T[] arr) {
    return arr[ThreadLocalRandom.current().nextInt(arr.length)];
  }

}
