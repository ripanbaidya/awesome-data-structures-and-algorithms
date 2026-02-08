package twopointer.medium;

/**
 * 11. Container With Most Water
 * <a href="https://leetcode.com/problems/container-with-most-water/description/">Link</a>
 */
public class ContainerWithMostWater {

  /* ---------------- Optimal: Two Pointer ---------------- */

  /**
   * Start with two pointers at the extreme ends to get maximum width. the area is
   * determined by: min(height[left], height[right]) * (right - left), To improve area,
   * we must try to increase the limiting height. Hence, move the pointer with the
   * smaller height inward.
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public int maxArea(int[] height) {
    int maximumArea = 0;

    int left = 0;
    int right = height.length - 1;

    while (left < right) {
      // maxArea = min(height[left], height[right]) * (right - left) 
      int width = right - left;
      int currentHeight = Math.min(height[left], height[right]);
      int currentArea = currentHeight * width;

      maximumArea = Math.max(maximumArea, currentArea);

      // Move the pointer pointing to the smaller height
      if (height[left] <= height[right]) {
        left++;
      } else {
        right--;
      }
    }

    return maximumArea;
  }

  // Main
  public static void main(String[] args) {
    var solution = new ContainerWithMostWater();

    int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

    int maximumArea = solution.maxArea(height);
    System.out.println("Maximum area: " + maximumArea);
  }
}
