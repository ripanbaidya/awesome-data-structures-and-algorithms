package array.medium;

/*
 * @author Ripan Baidya
 * @date 31/01/26
 *
 * 334. Increasing Triplet Subsequence
 * https://leetcode.com/problems/increasing-triplet-subsequence/description/
 *
 * Follow-up:
 * 1. Print the valid triplets
 * 2. Check for Increasing Subsequence of length k
 */
public class IncreasingTripletSubsequence {

    /* ---------------- Brute Force: Using Nested Loop ---------------- */

    /**
     * The simplest approach to find an increasing triplet subsequence is to use
     * three nested loops to check all possible triplets in the array.
     * <p>
     * Time Complexity: O(n^3), where n is the length of the input array.
     * Space Complexity: O(1), no extra space is used.
     */
    boolean increasingTripletBruteForce(int[] nums) {
        int n = nums.length;
        // Iterate over the array to find the first number
        for (int i = 0; i < n - 2; i++) {
            // Iterate to find the second number which is greater than the first
            for (int j = i + 1; j < n - 1; j++) {
                // Iterate to find the third number which is greater than the second
                for (int k = j + 1; k < n; k++) {
                    // Check if this is an increasing triplet
                    if (nums[i] < nums[j] && nums[j] < nums[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* ---------------- Optimal: Using Two variables ---------------- */

    /**
     * The goal is to find three increasing numbers a, b, c such that a < b < c.
     * We can achieve this in linear time with two variables:
     * first: This will keep track of the smallest number found so far.
     * second: This will keep track of a number larger than first.
     * As we scan through the array, the goal is to gradually refine these candidates.
     * If at any point we encounter a number greater than both first and second,
     * it becomes our third, and the triplet exists.
     * <p>
     * Time Complexity: O(n), where n is the length of the input array.
     * Space Complexity: O(1), no extra space is used.
     */
    boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                // This means we have found the third number
                return true;
            }
        }
        return false; // No increasing triplet found
    }

    // Main
    public static void main(String[] args) {
        var solution = new IncreasingTripletSubsequence();

        int[] nums = {2, 1, 5, 0, 4, 6};
        int[] nums2 = {3, 5, 0, 3, 1, -1, 2};

        System.out.println(solution.increasingTriplet(nums));
        System.out.println(solution.increasingTriplet(nums2));
    }
}
