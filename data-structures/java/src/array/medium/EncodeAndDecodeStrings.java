package array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array of strings s[], you are required to create an algorithm in the encode() function
 * that can convert the given strings into a single encoded string, which can be transmitted over
 * the network and then decoded back into the original array of strings. The decoding will happen
 * in the decode() function.
 *
 * You need to implement two functions:
 * 1. encode(): This takes an array of strings s[] and encodes it into a single string.
 * 2. decode(): This takes the encoded string as input and returns an array of strings containing
 * the original array as given in the encode method.
 *
 * Note: You are not allowed to use any inbuilt serialize method.
 *
 * Example:
 * Input: s = ["Hello","World"]
 * Output: ["Hello","World"]
 * Explanation: The encode() function will have the str as input, it will be encoded by one machine.
 * Then another machine will receive the encoded string as the input parameter and then will decode
 * it to its original form.
 *
 * @author Ripan Baidya
 * @date 05/01/26
 */
public class EncodeAndDecodeStrings {

    /**
     * As the input can contain any character's, we use the length of the string and a delimiter (here
     * '#') to delimit the string. We keep the length and delimiter together in front of the actual
     * string so that while decoding, we can easily get the length of the string and the actual string.
     *
     * <p>While decoding, we first compute the length of the string, then skip the delimiter, and then
     * extract the string. We move to the next string.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String encode(String strs[]) {
        StringBuilder sb = new StringBuilder();

        // Append length + '#' + string
        // ["Hello", "World"] -> "5#Hello5#World"
        for (String str : strs) {
            sb.append(str.length() + "#");
            sb.append(str);
        }

        return  sb.toString();
    }

    public String[] decode(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            // Calcuate the length
            int len = 0;
            while (s.charAt(i) != '#') {
                len = len * 10 + (s.charAt(i) - '0');
                i ++;
            }

            // Skip the '#' character
            i ++;

            // 5#Hello5#World
            // Extract the string
            String word = s.substring(i, i + len);
            list.add(word);

            // Move to the next string
            i += len;
        }

        return list.toArray(new String[0]);
    }

    // Main
    public static void main(String[] args) {
        var ref = new EncodeAndDecodeStrings();

        String[] strs = {"Hello", "World"};

        String encoded = ref.encode(strs);
        String[] decoded = ref.decode(encoded);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + Arrays.toString(decoded));
    }
}
