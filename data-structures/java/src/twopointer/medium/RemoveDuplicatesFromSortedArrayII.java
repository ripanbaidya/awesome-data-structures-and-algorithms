package twopointer.medium;

import java.util.Map;
import java.util.TreeMap;

public class RemoveDuplicatesFromSortedArrayII {

  /* ---------------- BruteForce: Map ---------------- */

  /**
   * Time Complexity: O(n log n)
   * Time Complexity: O(n)
   */
  public int removeDuplicatesBruteForce(int[] nums) {
    // Count the frequency of each element
    TreeMap<Integer, Integer> freq = new TreeMap<>();
    for (int num : nums) {
      freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    int k = 0;
    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
      // for each number, will check there frequency and store at most 2 elements if freq > 2
      if (entry.getValue() >= 2) {
        for (int i = 0; i < 2; i++) {
          nums[k++] = entry.getKey();
        }
      } else {
        nums[k++] = entry.getKey();
      }
    }

    return k;
  }

  /* ---------------- Optimal: Two Pointer ---------------- */

  /**
   * Time Complexity: O(n log n)
   * Time Complexity: O(n)
   */
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0)
      return 0;

    // Points to the last valid position in the modified array
    int writeIndex = 0;

    // Tracks how many times the current number has appeared
    // at most twice is allowed
    int occurrenceCount = 1;

    for (int readIndex = 1; readIndex < nums.length; readIndex++) {
      int lastWrittenValue = nums[writeIndex];
      int currentValue = nums[readIndex];

      // If the current value is the same as the last written value,
      // we only allow it if it appears less than twice.
      if (currentValue == lastWrittenValue) {
        if (occurrenceCount < 2) {
          nums[++writeIndex] = currentValue;
          occurrenceCount++;
        }
      }
      // If we encounter a new value,
      // write it to the array and reset the counter.
      else {
        nums[++writeIndex] = currentValue;
        occurrenceCount = 1;
      }
    }

    // writeIndex represents the last filled index,
    // so the new length is writeIndex + 1
    return writeIndex + 1;
  }


  public static void main(String[] args) {
    var solution = new RemoveDuplicatesFromSortedArrayII();

    int[] nums = {1, 1, 1, 2, 2, 3};
    int result = solution.removeDuplicates(nums);
    System.out.println(result);
  }
}
