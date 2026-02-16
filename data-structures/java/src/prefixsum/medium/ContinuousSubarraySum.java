package prefixsum.medium;

import java.util.HashMap;

/**
 * 523. Continuous Subarray Sum
 * <a href="https://leetcode.com/problems/continuous-subarray-sum/description/">Link</a>
 */
public class ContinuousSubarraySum {

  // Brute Force: Nested Loop

  /**
   * Time Complexity: O(n^2)
   * Space Complexity: O(1)
   */
  public boolean checkSubarraySumBruteForce(int[] nums, int k) {
    for (int i = 0; i < nums.length; i++) {
      int prefSum = 0;
      for (int j = i; j < nums.length; j++) {
        prefSum += nums[j];
        int len = j - i + 1;

        if (prefSum % k == 0 && len >= 2)
          return true;
      }
    }

    return false;
  }

  // Optimal: PrefixSum + Hashing

  /**
   * If two prefix sums give the same remainder when divided by k, their difference
   * (i.e., the subarray between them) is divisible by k. So we store the first occurrence
   * of each remainder and check if the distance between indices is at least 2 to satisfy
   * the subarray length condition.
   * Time Complexity: O(n)
   * Space Complexity: O(n), due to hashmap
   */
  public boolean checkSubarraySum(int[] nums, int k) {
    if (k == 0) {
      for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i] == 0 && nums[i + 1] == 0)
          return true;
      }
      return false;
    }

    int prefixSum = 0;

    // {remainder, index}
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);

    for (int i = 0; i < nums.length; i++) {
      // Computer the prefix sum
      prefixSum += nums[i];

      // Normalize remainder to handle negative values
      int remainder = (prefixSum % k + k) % k;
      if (map.containsKey(remainder)) {
        // Check the length, true if >= 2
        if (i - map.get(remainder) >= 2)
          return true;
      } else {
        map.put(remainder, i);
      }
    }

    return false;
  }

  public static void main(String[] args) {
    var solution = new ContinuousSubarraySum();

    int[] nums = {23, 2, 4, 6, 7};
    int k = 6;

    boolean result = solution.checkSubarraySum(nums, k);
    System.out.println(result);
  }
}
