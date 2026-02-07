package twopointer.easy;

import java.io.FileWriter;

public class ValidPalindrome {

  /* ---------------- Optimal: Two Pointer ---------------- */

  /**
   * Place two pointers at the start and end of the string. Move them towards each other,
   * skipping non-alphanumeric characters. If characters don't match, return false.
   * If all characters match, return true.
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      char leftChar = s.charAt(left);
      char rightChar = s.charAt(right);

      // Skip all non-alphanumeric characters from the left
      while (!Character.isLetterOrDigit(leftChar)) {
        left++;
      }
      // Skip all non-alphanumeric characters from the right
      while (!Character.isLetterOrDigit(rightChar)) {
        right--;
      }
      // If characters don't match, it's not a palindrome
      if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }

  // Main
  public static void main(String[] args) {
    var solution = new ValidPalindrome();

    String s = "A man, a plan, a canal: Panama";
    System.out.println(solution.isPalindrome(s));
  }
}
