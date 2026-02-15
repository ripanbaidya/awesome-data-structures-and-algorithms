package prefixsum.easy;

/**
 * 303. Range Sum Query - Immutable
 * <a href="https://leetcode.com/problems/range-sum-query-immutable/description/">Link</a>
 */
class NumArray {
  // Creating a prefix array
  int[] prefix;
  
  public NumArray(int[] nums) {
    if (nums.length == 0) {
      this.prefix = new int[0];
      return;
    }
    this.prefix = new int[nums.length];

    // Calculating the prefix sum
    prefix[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      prefix[i] = nums[i] + prefix[i - 1];
    }
  }

  public int sumRange(int left, int right) {
    return prefix[right] - (left > 0 ? prefix[left-1] : 0);
  }
}

public class RangeSumQueryImmutable {
  public static void main(String[] args) {
    int[] nums = {-2, 0, 3, -5, 2, -1};
    NumArray numArray = new NumArray(nums);

    System.out.println(numArray.sumRange(0, 2)); // return (-2) + 0 + 3 = 1
    System.out.println(numArray.sumRange(2, 5)); // return 3 + (-5) + 2 + (-1) = -1
    System.out.println(numArray.sumRange(0, 5)); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
  }
}
