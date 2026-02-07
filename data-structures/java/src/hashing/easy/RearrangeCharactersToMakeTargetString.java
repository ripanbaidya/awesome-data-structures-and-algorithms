package hashing.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 2287. Rearrange Characters to Make Target String
 * <a href="https://leetcode.com/problems/rearrange-characters-to-make-target-string/description/">Click here.</a>
 */
public class RearrangeCharactersToMakeTargetString {

  /* ---------------- Better ---------------- */

  /**
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   */
  public int rearrangeCharactersBetter(String s, String target) {
    int[] characterFreq = new int[26];
    // Count the frequency of each character of String 's'
    for (char c : s.toCharArray()) {
      characterFreq[c - 'a']++;
    }

    // Count the frequency of each character of String 'target'
    Map<Character, Integer> targetFreq = new HashMap<>();
    for (char c : target.toCharArray()) {
      targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
    }

    int result = Integer.MAX_VALUE;
    for (Map.Entry<Character, Integer> entry : targetFreq.entrySet()) {
      char ch = entry.getKey();
      int needed = entry.getValue();
      result = Math.min(result, characterFreq[ch - 'a'] / needed);
    }

    return result;
  }

  /* ---------------- Optimal ---------------- */

  /**
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public int rearrangeCharacters(String s, String target) {
    int[] characterFreq = new int[26];
    int[] targetFreq = new int[26];

    // Count the frequency of each character of String 's'
    for (char c : s.toCharArray()) {
      characterFreq[c - 'a']++;
    }
    // Count the frequency of each character of String 'target'
    for (char c : target.toCharArray()) {
      targetFreq[c - 'a']++;
    }

    // Initialize with 0, as we are calculating the maximum
    int result = 0;

    // Since, there are 26 possible characters, we are interested only in those characters
    // which are present in the target string, so we will only consider those characters and
    // calculate the maximum number of target string that can be formed
    for (int i = 0; i < 26; i++) {
      if (targetFreq[i] > 0) {
        int possibleTarget = characterFreq[i] / targetFreq[i];
        result = Math.min(result, possibleTarget);
      }
    }

    return result;
  }

  // Main
  public static void main(String[] args) {
    var solution = new RearrangeCharactersToMakeTargetString();

    String s = "ilovecodingonleetcode";
    String target = "code";

    int result = solution.rearrangeCharacters(s, target);
    System.out.println(result);
  }
}
