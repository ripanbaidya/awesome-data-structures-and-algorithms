package hashing.easy;

/**
 * 205. Isomorphic Strings
 * <a href="https://leetcode.com/problems/isomorphic-strings/description/">Click here.</a>
 */
public class IsomorphicStrings {

    /* ---------------- Better: Using Two Hashmaps ---------------- */
    public boolean isIsomorphic(String s, String t) {
        // If strings are of different length, they cannot be isomorphic
        if (s.length() != t.length()) {
            return false;
        }
        // Map from `S to T` and `T to S`
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check if previous mapped characters are same
            if (mapS[charS] != mapT[charT]) {
                return false;
            }

            // Map characters to index+1 to avoid default 0 confusion
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }

        return true;
    }

    // Main
    public static void main(String[] args) {
        var solution = new IsomorphicStrings();

        String s = "egg";
        String t = "add";

        boolean result = solution.isIsomorphic(s, t);
        System.out.println(result);
    }
}
