package twopointer.easy;

/**
 * 680. Valid Palindrome II
 * <a href="https://leetcode.com/problems/valid-palindrome-ii/description/">Link</a>
 */
public class ValidPalindromeII {

  /* ---------------- Optimal: Two Pointer ---------------- */

  /**
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public boolean validPalindrome(String s) {
    return isPalindrome(s, 0, s.length() - 1, false);
  }

  private boolean isPalindrome(String s, int left, int right,
                               boolean deleted) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        if (deleted)
          return false;
        return isPalindrome(s, left + 1, right, true) ||
            isPalindrome(s, left, right - 1, true);
      }

      left++;
      right--;
    }

    return true;
  }

  public static void main(String[] args) {
    var solution = new ValidPalindromeII();

    String s = "abc";

    boolean result = solution.validPalindrome(s);
    System.out.println("Result: " + result);
  }
}