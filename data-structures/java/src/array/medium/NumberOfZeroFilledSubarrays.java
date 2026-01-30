package array.medium;

/*
 * @author Ripan Baidya
 * @date 30/01/26
 *
 * Given an integer array nums, return the number of subarrays filled with 0.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example:
 * Input: nums = [1,3,0,0,2,0,0,4]
 * Output: 6
 * Explanation:
 * There are 4 occurrences of [0] as a subarray.
 * There are 2 occurrences of [0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 2 filled with 0.
 * Therefore, we return 6.
 */
public class NumberOfZeroFilledSubarrays {

    /* ---------------- Brute Force: Using Nested Loop ---------------- */

    /*
     * The simplest approach to solve this problem is to consider all possible subarrays
     * and count those filled entirely with zeroes. While this method is straightforward,
     * it can be inefficient for large arrays due to the sheer number of potential subarrays.
     *
     * Time Complexity: O(n^2) - As we check each subarray which takes quadratic time.
     * Space Complexity: O(1) - No additional space is used beyond input size.
     */
    public long zeroFilledSubarrayBruteForce(int[] nums) {
        long count = 0;
        int n = nums.length;

        // Check each subarray starting at index `i`
        for (int i = 0; i < n; i++) {
            // Track contiguous zeroes starting from index `i`
            for (int j = i; j < n && nums[j] == 0; j++) {
                // Increment count each time a zero-filled subarray is found
                count++;
            }
        }

        return count;
    }

    /* ---------------- Optimal: Linear Scan ---------------- */

    /*
     * We can achieve a more optimal solution by recognizing runs of zeroes as we iterate
     * over the array. For each segment of contiguous zeroes, the number of zero-filled
     * subarrays can be calculated using combinatorial arithmetic.
     * Specifically, a contiguous run of k zeroes contributes k * (k + 1) / 2 zero-filled
     * subarrays.
     *
     * Time Complexity: O(n) - We pass through the array a single time.
     * Space Complexity: O(1) - Only a constant amount of extra space is used.
     */
    public long zeroFilledSubarray(int[] nums) {
        long result = 0;
        int zeroCount = 0;

        for (int num : nums) {
            // Check if the current number is zero
            if (num == 0) {
                // Extend the current zero sequence
                zeroCount++;
            } else {
                // Calculate subarrays for the zero sequence
                result += zeroCount * (zeroCount + 1L) / 2;
                // Reset zero sequence counter
                zeroCount = 0;
            }
        }

        // If the last element was part of a zero sequence, add its subarrays
        result += zeroCount * (zeroCount + 1L) / 2;

        return result;
    }

    // Main
    public static void main(String[] args) {
        var solution = new NumberOfZeroFilledSubarrays();

        int[] nums = {1, 3, 0, 0, 2, 0, 0, 4};

        long total = solution.zeroFilledSubarray(nums);
        System.out.println("total = " + total);
    }
}
