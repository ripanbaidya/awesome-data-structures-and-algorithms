package twopointer.medium;

/**
 * 1750. Minimum Length of String After Deleting Similar Ends
 * <a href="https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/description/">Link</a>
 */
public class MinimumLengthOfStringAfterDeletingSimilarEnds {

  /* ---------------- Optimal: Two Pointer ---------------- */

  /**
   * Idea is very simple, we start with two pointers at the start and end of the string.
   * Move them towards each other, skipping all identical characters from both sides.
   * If the characters don't match, return the length of the string.
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public int minimumLength(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      char leftChar = s.charAt(left);
      char rightChar = s.charAt(right);

      // if ends match, remove all identical chars from both sides
      if (leftChar == rightChar) {
        // Remove identical chars from left side
        while (left <= right && s.charAt(left) == leftChar) {
          left++;
        }
        // Remove identical chars from right side
        while (right >= left && s.charAt(right) == rightChar) {
          right--;
        }
      }
      // if ends don't match, return the length of the string
      else {
        return right - left + 1;
      }
    }

    /*
     * Note: After the loop ends, two cases are possible:
     * 1) left > right  → all characters have been removed, so remaining length = 0
     * 2) left == right → exactly one character remains, so remaining length = 1
     * We cannot return 0 directly because the string may still contain a valid single
     * character. Hence, compute the remaining length as (right - left + 1) and guard
     * against negative values.
     */
    return Math.max(0, right - left + 1);
  }

  // Main
  public static void main(String[] args) {
    var solution = new MinimumLengthOfStringAfterDeletingSimilarEnds();

    String s = "aabccabba";
    int minimumLength = solution.minimumLength(s);
    System.out.println("Minimum length after deleting similar ends: " + minimumLength);
  }
}
