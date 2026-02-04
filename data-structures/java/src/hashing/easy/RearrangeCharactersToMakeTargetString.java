package hashing.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 2287. Rearrange Characters to Make Target String
 * <a href="https://leetcode.com/problems/rearrange-characters-to-make-target-string/description/">Click here.</a>
 */
public class RearrangeCharactersToMakeTargetString {

    /* ---------------- Better ---------------- */

    public int rearrangeCharactersBetter(String s, String target) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        Map<Character, Integer> freqTarget = new HashMap<>();
        for (char c : target.toCharArray()) {
            freqTarget.put(c, freqTarget.getOrDefault(c, 0) + 1);
        }

        int result = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : freqTarget.entrySet()) {
            char ch = entry.getKey();
            int needed = entry.getValue();
            result = Math.min(result, freq[ch - 'a'] / needed);
        }

        return result;
    }

    /* ---------------- Optimal ---------------- */

    public int rearrangeCharacters(String s, String target) {
        int[] freq = new int[26];
        int[] freq2 = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : target.toCharArray()) {
            freq2[c - 'a']++;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (freq2[i] > 0) {
                result = Math.min(result, freq[97 + i - 'a'] / freq2[i]);
            }
        }

        return result;
    }

    // Main
    public static void main(String[] args) {
        var solution = new RearrangeCharactersToMakeTargetString();

        String s = "ilovecodingonleetcode", target = "code";
        int result = solution.rearrangeCharacters(s, target);
        System.out.println(result);
    }
}
