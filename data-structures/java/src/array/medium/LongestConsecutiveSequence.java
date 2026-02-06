package array.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ripan Baidya
 * @date 31-07-2025
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements
 * sequence. You must write an algorithm that runs in O(n) time.
 * <p>
 * Example:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

    /* ---------------- Brute Force: Iterative + Searching ---------------- */

    /**
     * Time Complexity: O(n) * O(n), because we iterate over each element once. Inside the loop, we use a search
     * to find the next consecutive element.
     * Space Complexity: O(1) constant space is used.
     */
    public int longestConsecutiveBruteForce(int[] nums) {
        // Longest Consecutive sequence
        int longestSequence = 0;

        for (int i = 0; i < nums.length; i++) {
            // Current elements and count
            int currentElement = nums[i];
            int currentCount = 1;

            // Check whether the next consecutive elements is present in the array
            // Continue, till condition fails
            while (isPresent(currentElement + 1, nums)) {
                // Update the count and element
                currentCount++;
                currentElement++;
            }
            // Update the longest sequence
            longestSequence = Math.max(longestSequence, currentCount);
        }

        return longestSequence;
    }

    private boolean isPresent(int target, int[] nums) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    /* ---------------- Better: Sorting ---------------- */

    /**
     * Time Complexity: O(n log n) + O(n), where 'n' is the length of the array, sorting takes nlogn and
     * another iteration takes O(n)
     * Space Complexity: O(1), if we never consider the space required to sort the array
     */
    public int longestConsecutiveBetter(int[] nums) {
        // Edge case - if array is empty, no sequence exists
        if (nums.length == 0) {
            return 0;
        }

        // Sort the array to bring consecutive numbers together
        Arrays.sort(nums);
        // At least one number means longest sequence is at least 1
        int longestSequence = 1;
        // Tracks length of the current consecutive sequence
        int currentCount = 1;

        // Traverse the sorted array
        for (int i = 1; i < nums.length; i++) {
            // Duplicate - Ignore duplicates as they do not affect the sequence length
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            // Consecutive element - Increase the current sequence count
            else if (nums[i] == nums[i - 1] + 1) {
                currentCount++;
                longestSequence = Math.max(longestSequence, currentCount);
            }

            // Non-consecutive element - Reset current sequence count
            else {
                currentCount = 1;
            }
        }

        // Return the length of the longest consecutive sequence
        return longestSequence;
    }

    /* ---------------- Optimal: Hashing ---------------- */

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // Add all unique elements into the set
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestSequence = 1;

        for (int num : numSet) {
            // When the current number is the starting of consecutive sequence
            if (!numSet.contains(num - 1)) {
                int currentCount = 1;
                int currentElement = num;

                while (numSet.contains(currentElement + 1)) {
                    currentCount++;
                    currentElement++;
                }

                longestSequence = Math.max(longestSequence, currentCount);
            }
        }

        return longestSequence;
    }

    public static void main(String[] args) {
        var solution = new LongestConsecutiveSequence();

        int[] nums = {100, 4, 200, 1, 3, 2};

        int result = solution.longestConsecutive(nums);
        System.out.println("Length of longest consecutive sequence: " + result);
    }
}
