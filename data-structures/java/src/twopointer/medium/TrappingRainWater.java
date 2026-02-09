package twopointer.medium;

/**
 * 42. Trapping Rain Water
 * <a href="https://leetcode.com/problems/trapping-rain-water/description/">Link</a>
 */
public class TrappingRainWater {

  /* ---------------- Brute Force: Nested Loop ---------------- */

  /**
   * Time Complexity: O(n^2)
   * Space Complexity: O(1)
   */
  public int trapBruteForce(int[] height) {
    int n = height.length;

    // total water trapped
    int total = 0;

    // for each element find the maximum from the left & right side
    for (int i = 0; i < n; i++) {
      int leftMax = 0, rightMax = 0;
      // find maximum from the left side
      for (int j = 0; j <= i; j++) {
        leftMax = Math.max(leftMax, height[j]);
      }
      // find maximum from the right side
      for (int k = i; k < n; k++) {
        rightMax = Math.max(rightMax, height[k]);
      }

      // calculate total water
      total += Math.min(leftMax, rightMax) - height[i];
    }

    return total;
  }

  /* ---------------- Optimal: Two Pointer ---------------- */

  /**
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public int trap(int[] height) {
    // Initialize two pointers
    int left = 0, right = height.length - 1;
    // Variable to store the maximum from the left and right
    int leftMax = 0, rightMax = 0;
    // Total water trapped
    int total = 0;

    while (left < right) {
      // Right side is at least as tall as left side
      if (height[left] <= height[right]) {
        if (height[left] >= leftMax) {
          // Update leftMax if current height is greater
          leftMax = height[left];
        } else {
          // Compute the water trapped at the current position
          total += leftMax - height[left];
        }
        // Move left pointer to the right
        left++;
      }
      // Left side is taller than right side
      else {
        // Update rightMax if current height is greater
        if (height[right] >= rightMax) {
          rightMax = height[right];
        } else {
          // Compute the water trapped at the current position
          total += rightMax - height[right];
        }
        // Move right pointer to the left
        right--;
      }
    }

    // Return the result
    return total;
  }

  // Main
  public static void main(String[] args) {
    var solution = new TrappingRainWater();

    int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    int result = solution.trap(height);
    System.out.println("Total water trapped: " + result);
  }
}
