package twopointer.easy;

import java.util.Arrays;

/**
 * 344. Reverse String
 * <a href="https://leetcode.com/problems/reverse-string/description/">Link</a>
 */
public class ReverseString {

  /**
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public void reverseString(char[] s) {
    int left = 0, right = s.length-1;

    while (left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;

      left ++;
      right --;
    }
  }

  public static void main(String[] args) {
    var solution = new ReverseString();

    char[] s = {'h', 'e', 'l', 'l', 'o'};

    solution.reverseString(s);
    System.out.println(Arrays.toString(s));
  }
}
