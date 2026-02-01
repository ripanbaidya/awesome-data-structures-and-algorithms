package array.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 41. First Missing Positive
 * https://leetcode.com/problems/first-missing-positive/description/
 * <p>
 * Hashing, Cyclic Sort
 */
public class FirstMissingPositive {

    /* ---------------- Brute Force:  ---------------- */

    /**
     * We need to find the first missing positive integer from an unsorted array.
     * Our basic approach involves checking starting from 1, then 2, and so on, whether each number
     * is present in the array.
     * We continue this process until we find a number that is not present, which will be our answer.
     * <p>
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int firstMissingPositiveBruteForce(int[] nums) {
        int n = nums.length;
        // Starting from 1, check each integer in the array
        for (int i = 1; i <= n + 1; i++) {
            boolean found = false;
            // Check if i is present in the array
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            // If not found, then it's the missing positive
            if (!found) return i;
        }
        return -1; // The function should never reach here
    }

    /* ---------------- Better: Hashing ---------------- */

    /**
     * Rather than checking every number by iterating through the array, we can store all numbers
     * in a HashSet for O(1) look-up time.
     * Iterate through numbers starting from 1 and check if they are in the HashSet.
     * <p>
     * Time Complexity: O(n) - Linear time to populate the set and checking numbers
     * Space Complexity: O(n) - Space used for storing array elements in the HashSet.
     */
    public int firstMissingPositiveBetter(int[] nums) {
        Set<Integer> set = new HashSet<>();
        // Add all nums into the HashSet
        for (int num : nums) {
            set.add(num);
        }
        // Check from 1 to infinite for the first missing number
        for (int i = 1; i <= nums.length + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1; // The function should never reach here
    }

    /* ---------------- Optimal: Cyclic Sort ---------------- */

    /**
     * Place each number at its correct index (i.e., number 1 at index 0, number 2 at index 1, etc.).
     * Numbers out of this range can be ignored.
     * Finally, the first place where the number is not equal to the index + 1 is the missing number.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int correctPos = nums[i] - 1;

                int temp = nums[i];
                nums[i] = nums[correctPos];
                nums[correctPos] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    // Main
    public static void main(String[] args) {
        var solution = new FirstMissingPositive();

        int[] arr = {3, 4, -1, 1};

        int result = solution.firstMissingPositive(arr);
        System.out.println("First missing positive: " + result);
    }
}
