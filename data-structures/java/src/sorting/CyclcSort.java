package sorting;

import java.util.Arrays;

/**
 *
 * https://www.geeksforgeeks.org/dsa/cycle-sort/
 */
public class CyclcSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 4, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Only for array range (1-N) or (0-N)
     * Here, we are writing the solution for range 1-N.
     */
    private static void sort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // Correct position of current elements, which is currentElement-1
            // eg. nums[i] = 5, correctPos = 5-1 = 4
            int correctPos = nums[i] - 1;

            if (nums[i] <= nums.length && nums[i] != nums[correctPos]) {
                // When whatever the current elements is not in its correct position
                // swap them
                swap(nums, i, correctPos);
            } else {
                // When Current element in its correct place
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int correctPos) {
        int temp = nums[i];
        nums[i] = nums[correctPos];
        nums[correctPos] = temp;
    }
}
