package hashing.easy;

import static java.lang.Math.min;

/**
 * 1189. Maximum Number of Balloons
 * <a href="https://leetcode.com/problems/maximum-number-of-balloons/description/">Click here.</a>
 */
public class MaximumNumberOfBalloons {

    /* ---------------- Optimal  ---------------- */
    public int maxNumberOfBalloons(String text) {
        int result = Integer.MAX_VALUE;
        int[] freq = new int[26];

        // Count the frequency of each character
        for (char c : text.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check the contribution of each character
        result = min(result, freq['b' - 'a']);
        result = min(result, freq['a' - 'a']);
        result = min(result, freq['n' - 'a']);
        result = min(result, freq['l' - 'a'] / 2);
        result = min(result, freq['o' - 'a'] / 2);

        return result;
    }

    // Main
    public static void main(String[] args) {
        var solution = new MaximumNumberOfBalloons();

        String text = "loonbalxballpoon";
        int result = solution.maxNumberOfBalloons(text);
        System.out.println("Maximum number of balloon possible: " + result);
    }
}
