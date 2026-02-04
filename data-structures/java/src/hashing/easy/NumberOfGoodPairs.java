package hashing.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1512. Number of Good Pairs
 * <a href="https://leetcode.com/problems/number-of-good-pairs/description/">Click here.</a>
 */
public class NumberOfGoodPairs {

    /* ---------------- Brute Force ---------------- */

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int numIdenticalPairsBruteForce(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    /* ---------------- Better ---------------- */

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int numIdenticalPairsBetter(int[] nums) {
        int count = 0;
        Map<Integer, Integer> mp = new HashMap<>();

        // Count the frequency of each element
        for (int num : nums) {
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            // occurrence of current element
            int occ = entry.getValue();
            // for 'n' elements n*(n-1)/2 pairs are possible
            count += occ * (occ - 1) / 2;
        }

        return count;
    }

    // Main
    public static void main(String[] args) {
        var solution = new NumberOfGoodPairs();

        int[] nums = {1, 2, 3, 1, 1, 3};
        int result = solution.numIdenticalPairsBetter(nums);
        System.out.println(result);
    }
}
