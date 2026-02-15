package prefixsum.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * <a href="https://leetcode.com/problems/subarray-sum-equals-k/description/">Link</a>
 */
public class SubarraySumEqualsK {

  // Brute Force - Nested Loop

  /**
   * For each element, iterate through all possible subarrays starting from that element.
   * Compute the subarray sum and if sum is equivalent to k, increment the count of subarray.
   * Time Complexity: O(n^2) where n is the length of the input array.
   * Space Complexity: O(1)
   */
  public int subarraySumBruteForce(int[] nums, int k) {
    int n = nums.length;
    int totalOccurrence = 0;

    for (int i = 0; i < n; i++) {
      int currSum = 0;
      for (int j = i; j < n; j++) {
        currSum += nums[j];

        if (currSum == k)
          totalOccurrence++;
      }
    }

    return totalOccurrence;
  }


  // Optimal - PrefixSum With Hashing

  /**
   * This approach uses a hash map to store the frequency of prefix sums encountered so far.
   * For each element, we calculate the current prefix sum. If `prefixSum - k` exists in the map,
   * it means there's a subarray ending at the current position whose sum is `k`.
   * Time Complexity: O(n) where n is the length of the input array.
   * Space Complexity: O(n) in the worst case (all prefix sums are unique).
   */
  public int subarraySum(int[] nums, int k) {
    int totalOccurrence = 0;
    int prefixSum = 0;

    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    for (int num : nums) {
      // Compute the prefix sum
      prefixSum += num;

      // Check prefixSum-k
      int lookingFor = prefixSum - k;
      if (map.containsKey(lookingFor))
        totalOccurrence += map.get(lookingFor);

      map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }

    return totalOccurrence;
  }

  public static void main(String[] args) {
    var solution = new SubarraySumEqualsK();

    int[] nums = {1, 1, 1};
    int k = 2;

    int totalSubarrays = solution.subarraySum(nums, k);
    System.out.println("Total subarrays: " + totalSubarrays);
  }
}
