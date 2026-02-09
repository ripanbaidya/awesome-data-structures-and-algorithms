package twopointer.medium;

/**
 * 844. Backspace String Compare
 * <a href="https://leetcode.com/problems/backspace-string-compare/description/">Link</a>
 */
public class BackspaceStringCompare {

  /* ---------------- Better: Build String ---------------- */

  /**
   * Intuition: Let's individually build the result of each string (build(S) and build(T)),
   * then compare if they are equal.
   * Algorithm
   * To build the result of a string build(S), we'll use a StringBuilder approach, simulating
   * the result of each keystroke.
   * Time Complexity: O(m + n)
   * Space Complexity: O(m + n)
   */
  public boolean backspaceCompareBetter(String s, String t) {
    return helper(s).equals(helper(t));
  }

  /**
   * Helper function to build the result of a string by removing backspaces
   */
  private String helper(String s) {
    StringBuilder sb = new StringBuilder();

    for (char c : s.toCharArray()) {
      if (c == '#') {
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length() - 1);
        }
      } else {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  /* ---------------- Optimal: Two Pointer ---------------- */

  /**
   * Intuition: When writing a character, it may or may not be part of the final string depending on how many
   * backspace keystrokes occur in the future.
   * If instead we iterate through the string in reverse, then we will know how many backspace characters we
   * have seen, and therefore whether the result includes our character.
   * Algorithm: Iterate through the string in reverse. If we see a backspace character, the next non-backspace
   * character is skipped. If a character isn't skipped, it is part of the final answer.
   * Time Complexity: O(m + n)
   * Space Complexity: O(1)
   */
  public boolean backspaceCompare(String S, String T) {
    int i = S.length() - 1, j = T.length() - 1;
    int skipS = 0, skipT = 0;

    while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
      while (i >= 0) { // Find position of next possible char in build(S)
        if (S.charAt(i) == '#') {
          skipS++;
          i--;
        } else if (skipS > 0) {
          skipS--;
          i--;
        } else
          break;
      }
      while (j >= 0) { // Find position of next possible char in build(T)
        if (T.charAt(j) == '#') {
          skipT++;
          j--;
        } else if (skipT > 0) {
          skipT--;
          j--;
        } else
          break;
      }
      // If two actual characters are different
      if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
        return false;
      // If expecting to compare char vs nothing
      if ((i >= 0) != (j >= 0))
        return false;
      i--;
      j--;
    }
    return true;
  }

  // Main
  public static void main(String[] args) {
    var solution = new BackspaceStringCompare();

    String s = "ab#c";
    String t = "ad#c";
    boolean result = solution.backspaceCompare(s, t);
    System.out.println("Result: " + result); // true
  }
}
