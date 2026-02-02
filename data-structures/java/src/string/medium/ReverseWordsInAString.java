package string.medium;

import java.util.Stack;

/**
 * 151. Reverse Words in a String
 * <a href="https://leetcode.com/problems/reverse-words-in-a-string/description/">Click here</a>
 *
 * @author Ripan Baidya
 * @date 02.02.2026
 */
public class ReverseWordsInAString {

    /* ---------------- Better: Two Pointer + Stack ---------------- */

    /**
     * The goal is to reverse the order of words in a string while ignoring extra spaces.
     * The most straightforward way to do this is to use a stack. I scan the string from
     * left to right and extract each valid word. Every time I find a word, I push it onto
     * the stack.
     * Since a stack follows Last In First Out(LIFO), popping words from the stack automatically
     * gives them in reverse order. While scanning, I skip spaces so that leading, trailing, and
     * multiple spaces between words don’t affect the result.
     * Finally, I pop all words from the stack and append them to the result string.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n), for using stack
     */
    public String reverseWordsBetter(String s) {
        // Stack to store all valid words found in the string.
        Stack<String> words = new Stack<>();

        // Traverse the string from left to right
        for (int i = 0; i < s.length(); i++) {
            // Skip spaces (handles leading, trailing, and multiple spaces)
            if (s.charAt(i) == ' ') {
                continue;
            }
            // At this point, a valid character is found.
            // This marks the start of a word.
            int startPos = i;
            // Move forward until the end of the word is reached
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }

            // Extract the word using the detected boundaries [startPos, i)
            // and push it onto the stack
            words.push(s.substring(startPos, i));
        }

        // Build the result by popping words from the stack
        StringBuilder result = new StringBuilder();
        while (!words.isEmpty()) {
            result.append(words.pop()).append(" ");
        }

        // Remove the trailing space and return the final string
        return result.toString().trim();
    }

    /* ---------------- Better: Two Pointer ---------------- */

    /**
     * Previously i used stack to store each word, then pop them back in reverse order.
     * But a stack is only needed because I’m reading the string from left to right and
     * later pop back to collect the result.
     * But, If I instead read the string from `right to left`, I naturally encounter the
     * words in reverse order.
     * That means I can build the answer directly and completely without using a stack.
     * While scanning from the end, I skip all spaces so extra spaces don’t matter.
     * When I hit a character, I know I’ve found the end of a word.
     * I then move left until I hit a space — that gives me the start of the word.
     * Using these two boundaries, I extract the word and append it to the result.
     * Repeating this for the entire string gives the words in reversed order.
     * Time Complexity: O(n)
     * Space Complexity: O(1) if never consider the space to store the result.
     */
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();

        // traverse the string from right to left, this allows us
        // to collect words from the end without using extra space (stack i used previously)
        for (int i = s.length() - 1; i >= 0; i--) {

            // skip any spaces (handles leading, trailing, and multiple spaces)
            if (s.charAt(i) == ' ') {
                continue;
            }

            // at this point, s.charAt(i) is a valid character.
            // this marks the end of a word.
            int endPos = i;

            // move the pointer left until the beginning of the word is found.
            // this implicitly acts as the second pointer to detect word boundaries.
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            // extract the word using the detected boundaries [i + 1, endPos]
            // and append it to the result followed by a space.
            result.append(s.substring(i + 1, endPos + 1))
                    .append(" ");
        }

        // remove the extra trailing space and return the result
        return result.toString().trim();
    }

    public static void main(String[] args) {
        var solution = new ReverseWordsInAString();

        String s = "the sky is blue";

        System.out.println("Original Words: " + s);
        String result = solution.reverseWords(s);
        System.out.println("Reversed Words: " + result);
    }
}
