package array.medium;

import java.util.*;

/*
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * Example:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Explanation:
 * - There is no string in strs that can be rearranged to form "bat".
 * - The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * - The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 *
 * @author Ripan Baidya
 * @date 04/01/26
 */
public class GroupAnagrams {
    /*
     * Brute Force
     * Compare each string with every other string to check if they are anagrams. Group strings that
     * are anagrams together and mark them as used to avoid duplicate processing.
     *
     * Time Complexity: O(n^2 * m), where n is the number of strings and 'm' is the maximum length of
     * a string.
     * Space Complexity: O(n), where n is the number of strings.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // Result list to store group of anagrams
        List<List<String>> res = new ArrayList<>();
        // Number of strings
        int n = strs.length;

        // Edge case
        if (strs == null || n == 0) {
            return res;
        }

        // Array to mark strings that are already grouped
        int[] taken = new int[n];

        // Pick each string one by one
        for (int i = 0; i < n; i++) {
            // Skip if this string is already grouped
            if (taken[i] == 1) continue;

            // Create a new group for current string
            List<String> list = new ArrayList<>();
            list.add(strs[i]);

            // Compare with remaining strings
            for (int j = i + 1; j < n; j++) {
                // If strings are anagrams and not already grouped
                if (taken[j] == 0 && isAnagram(strs[i], strs[j])) {
                    list.add(strs[j]);
                    taken[j] = 1; // Mark as grouped
                }
            }
            // Add the current group to result
            res.add(list);
        }
        // Return the result
        return res;
    }

    // Helper function to check if two strings are anagrams
    private boolean isAnagram(String s1, String s2) {
        // If lengths differ, they cannot be anagrams
        if (s1.length() != s2.length()) {
            return false;
        }

        // Frequency array for 26 lowercase letters
        int[] freq = new int[26];

        // Count characters of first string
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }

        // Decrease count using second string
        for (char c : s2.toCharArray()) {
            freq[c - 'a']--;
            if (freq[c - 'a'] < 0) {
                return false;
            }
        }

        // If all counts are zero, they are anagrams
        return true;
    }

    /*
     * Optimal Solution
     * Anagrams become identical when their characters are sorted.
     * For example, "eat", "tea", and "ate" all become "aet" after sorting. By using the sorted
     * version of each string as a key, we can group all anagrams together.
     * Strings that share the same sorted form must be anagrams, so placing them in the same group
     * is both natural and efficient.
     *
     * Time Complexity: O(n * mlogm), where n is the number of strings and 'm' is the maximum length
     * of a string.
     * Space Complexity: O(n), where n is the number of strings.
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        // key (sorted string) -> value (list of anagrams)
        Map<String, List<String>> map = new HashMap<>();

        // Iterate over each string
        for (String s : strs) {
            // Sort each word
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // Put the sorted word as a key and the original word as a value
            map.computeIfAbsent(key, fun -> new ArrayList<>()).add(s);
        }

        // Return the result
        return new ArrayList<>(map.values());
    }

    // Main
    public static void main(String[] args) {
        var ref = new GroupAnagrams();

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(ref.groupAnagrams(strs));
        System.out.println(ref.groupAnagrams2(strs));
    }
}
