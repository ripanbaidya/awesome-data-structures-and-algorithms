package array.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 442. Find All Duplicates in an Array
 * <a href="https://leetcode.com/problems/find-all-duplicates-in-an-array/description/">...</a>
 *
 * @author Ripan Baidya
 * @date 01.02.2026
 */
public class FindAllDuplicatedInAnArray {

    /* ---------------- Better: Hashing ---------------- */

    /**
     * Using hashset to store the all elements, at each iteration checking whether current element
     * already present in the set, if then add it to result.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n), using hashset
     */
    public List<Integer> findDuplicateNumbersBetter(int[] numbers) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (int number : numbers) {
            // Check if the number is already in the set of unique numbers
            if (uniqueNumbers.contains(number)) {
                result.add(number);
            }
            uniqueNumbers.add(number);
        }

        return result;
    }

    /* ---------------- Optimal: Cyclic Sort ---------------- */

    /**
     * Uses cyclic sort to place each number at its correct index (value - 1).
     * <p>
     * Since all numbers are in the range [1, n], each element has a unique correct position.
     * While sorting, if a number is already present at its correct index, it means we have
     * encountered a duplicate and simply move on.
     * <p>
     * After the cyclic sort, any index i where nums[i] != i + 1 indicates a duplicated value,
     * because the correct position is already occupied by the same number.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1) (in-place, excluding output list)
     */

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int i = 0;

        while (i < nums.length) {
            int correct = nums[i] - 1;

            if (nums[i] != nums[correct]) {
                // swap nums[i] with nums[correct]
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            } else {
                i++;
            }
        }
        // collect duplicates
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }

        return result;
    }

    // Main
    public static void main(String[] args) {
        var solution = new FindAllDuplicatedInAnArray();

        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};

        List<Integer> result = solution.findDuplicates(arr);
        System.out.println(result);
    }
}
