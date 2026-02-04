package hashing.easy;

/**
 * 383. Ransom Note
 * <a href="https://leetcode.com/problems/ransom-note/description/">Click here.</a>
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];

        // Count frequency of character of 'ransomNote'
        for (char c : ransomNote.toCharArray()) {
            freq[c - 'a']++;
        }
        // Decrease frequency of character of 'magazine'
        for (char c : magazine.toCharArray()) {
            freq[c - 'a']--;
        }

        // If any character frequency is positive, Means, there are still some
        // characters are left in 'ransomNote' which are not present in 'magazine'
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                return false;
            }
        }

        return true;
    }

    // Main
    public static void main(String[] args) {
        var solution = new RansomNote();

        String ransomNote = "a";
        String magazine = "b";

        boolean result = solution.canConstruct(ransomNote, magazine);
        System.out.println(result);
    }
}
