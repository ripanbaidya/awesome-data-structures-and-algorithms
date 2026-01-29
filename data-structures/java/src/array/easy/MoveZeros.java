package array.easy;

import java.util.Arrays;

/*
 * @author Ripan Baidya
 * @date 29-01-2026
 *
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order
 * of the non-zero elements. Note that you must do this in-place without making a copy of the array.
 * Example:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class MoveZeros {

    /* ---------------- Better 1: Two Pointers - Two Pass ---------------- */

    /*
     * The idea is to move all the zeros to the end of the array while maintaining the relative
     * order of non-zero elements using two traversals.
     *
     * Time Complexity: O(n), as we are traversing the array only twice.
     * Auxiliary Space: O(1)
     */
    public void moveZeroesBetter1(int[] nums) {
        int n = nums.length;
        int count = 0; // count non-zero elements

        // If the current element is not zero, place it at the 'count' position
        // and move the 'count' pointer to the next position.
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[count++] = nums[i];
            }
        }

        // Now all non-zero elements have been shifted to
        // the front. Make all elements 0 from count to end.
        while (count < n) {
            nums[count++] = 0;
        }
    }

    /* ---------------- Better 2: Two Pointer - One Pass ---------------- */

    /*
     * We can fine-tune our previous two-pointer approach by swapping in place.
     * Maintain two indices:
     * - writePos points to the next position where a non-zero should live.
     * - readPos scans the array left to right.
     * Whenever nums[readPos] is non-zero, swap it with nums[writePos]
     * (only if readPos != writePos) and advance writePos. This compacts non-zeros
     * in a single pass and leaves zeros behind naturally.
     *
     * Time Complexity: O(n), as the array is traversed once.
     * Space Complexity: O(1), no additional data structures are used.
     */
    public void moveZeroesBetter2(int[] nums) {
        int writePos = 0; // next slot for a non-zero
        for (int readPos = 0; readPos < nums.length; readPos++) {
            if (nums[readPos] != 0) {
                if (readPos != writePos) {
                    int tmp = nums[readPos];
                    nums[readPos] = nums[writePos];
                    nums[writePos] = tmp;
                }
                writePos++;
            }
        }
    }

    public static void main(String[] args) {
        var solution = new MoveZeros();
        
        int nums[] = {0, 1, 0, 3, 12};

        solution.moveZeroesBetter1(nums);
    }
}
