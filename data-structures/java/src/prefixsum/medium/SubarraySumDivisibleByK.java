package prefixsum.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 974. Subarray Sums Divisible by K
 * <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/description/">Link</a>
 */
public class SubarraySumDivisibleByK {

  // Brute Force - Nested Loop

  /**
   * Iterate through each index as a starting point and expand the subarray to the
   * right while maintaining a running sum.
   * If the running sum divisible by k (sum % k == 0), increment the count of valid
   * subarrays.
   * Time Complexity: O(n^2), where n is the length of the array.
   * Space Complexity: O(1), since no extra data structures are used.
   */
  public int subarraysDivByKBruteForce(int[] nums, int k) {
    int totalOccurrence = 0;

    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];

        if (sum % k == 0)
          totalOccurrence++;
      }
    }

    return totalOccurrence;
  }


  // Optimal - Prefix Sum with Hashing

  /**
   * Uses the Prefix Sum technique with a HashMap to count subarrays divisible by k.
   * If two prefix sums have the same remainder when divided by k, the subarray between
   * them is divisible by k.
   * We normalize negative remainders using: remainder = (prefixSum % k + k) % k
   * Time Complexity: O(n)
   * Space Complexity: O(n), for hashmap to store remainders
   */
  public int subarraysDivByK(int[] nums, int k) {
    int totalOccurrence = 0;
    int prefixSum = 0;

    // Store {remainder, frequency}
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // Base case: prefix sum divisible by k

    for (int num : nums) {
      prefixSum += num;

      // Normalize remainder to handle negative values
      int remainder = (prefixSum % k + k) % k;

      // If this remainder was seen before,
      // all previous occurrences form valid subarrays
      totalOccurrence += map.getOrDefault(remainder, 0);

      // Update frequency of current remainder
      map.put(remainder, map.getOrDefault(remainder, 0) + 1);
    }

    return totalOccurrence;
  }


  public static void main(String[] args) {
    var solution = new SubarraySumDivisibleByK();

    int[] nums = {4, 5, 0, -2, -3, 1};
    int k = 5;

    int totalSubarrays = solution.subarraysDivByK(nums, k);
    System.out.println("Total subarrays (Optimal): " + totalSubarrays); // Expected: 7
  }
}
