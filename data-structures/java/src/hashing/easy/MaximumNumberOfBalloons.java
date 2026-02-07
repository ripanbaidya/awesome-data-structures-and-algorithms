package hashing.easy;

import static java.lang.Math.min;

/**
 * 1189. Maximum Number of Balloons
 * <a href="https://leetcode.com/problems/maximum-number-of-balloons/description/">Click here.</a>
 */
public class MaximumNumberOfBalloons {

  /* ---------------- Optimal  ---------------- */

  /**
   * Count the frequency of each character, then divide the frequency by the number of times
   * each character is needed, compute the minimum among all the possible characters, return
   * the result.
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public int maxNumberOfBalloons(String text) {
    int maxBalloons = Integer.MAX_VALUE;
    int[] charFreq = new int[26];

    // Count frequency of each character in the text
    for (char ch : text.toCharArray()) {
      charFreq[ch - 'a']++;
    }

    // Characters required to form "balloon"
    char[] requiredChars = {'a', 'b', 'l', 'o', 'n'};
    // Number of times each character is needed
    int[] need = {1, 1, 2, 2, 1};

    for (int i = 0; i < requiredChars.length; i++) {
      int possibleBalloons =
          charFreq[requiredChars[i] - 'a'] / need[i];
      maxBalloons = Math.min(maxBalloons, possibleBalloons);
    }

    return maxBalloons;
  }


  // Main
  public static void main(String[] args) {
    var solution = new MaximumNumberOfBalloons();

    String text = "loonbalxballpoon";

    int result = solution.maxNumberOfBalloons(text);
    System.out.println("Maximum number of balloon possible: " + result);
  }
}
