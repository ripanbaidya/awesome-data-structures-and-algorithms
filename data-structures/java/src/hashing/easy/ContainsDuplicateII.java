package hashing.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. Contains Duplicate II
 * <a href="https://leetcode.com/problems/contains-duplicate-ii/description/">Click here.</a>
 */
public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentElement = nums[i];

            if (mp.containsKey(currentElement) && Math.abs(mp.get(currentElement) - i) <= k) {
                return true;
            }

            mp.put(currentElement, i);
        }

        return false;
    }

    // Main
    public static void main(String[] args) {
        var solution = new ContainsDuplicateII();

        int[] nums = {1, 2, 3, 1};
        int k = 3;

        boolean result = solution.containsNearbyDuplicate(nums, k);
        System.out.println(result);
    }
}
