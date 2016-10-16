/**
 * Given a string S, find the length of the longest substring T that 
 * contains at most two distinct characters.
 * For example, Given S = “eceba”, T is "ece" which its length is 3.
 */
import java.util.*;

public class LongestSubstringKDistinctChars {

    public static int lengthOfLongestSubstring(String s, int k) {
        int maxLen = 0;
        Map<Character, Integer> charToPos = new HashMap<>();
        int start = 0;
        int i = 0;
        for (; i < s.length(); ++i) {
            Integer prevIdInteger = charToPos.get(s.charAt(i));
            if ((prevIdInteger == null) && (charToPos.size() == k)) {
                maxLen = Math.max(maxLen, i - start);
                for (; start < i; ++start) {
                    if (charToPos.get(s.charAt(start)) == start) {
                        charToPos.remove(s.charAt(start++));
                        break;
                    }
                }
            }
            charToPos.put(s.charAt(i), i);
        }
        return (maxLen > i - start) ? maxLen : i - start;
    }

// Another solution is simply use charToCounts instead of charToPos.
// Both solutions run in O(n) time and O(1) space
    
    private static void test(String s, int k, int truth) {
        System.out.println("String: " + s);
        System.out.println("k = " + k);
        int result = lengthOfLongestSubstring(s, k);
        if (result == truth) {
            System.out.println("Result = " + result + ", correct");
        } else {
            System.out.println("Result = " + result + ", incorrect, should be " + truth);
        }
    }

    public static void main(String[] args) {

        if (args.length > 0) {
            String str = args[0];
            int k = Integer.parseInt(args[1]);
            System.out.println("Input string: ");
            System.out.println(str);
            System.out.println("k = " + k);
            int result = lengthOfLongestSubstring(str, k);
            System.out.println("Result = " + result);         
        }

        test("eceba", 2, 3);
        test("abcbbbbcccbdddadacb", 2, 10);
        test("abbaaaaeeeeeeeeee", 2, 14);
        test("abcadcacacaca", 3, 11);
        test("aaabbbccc", 3, 9);
        test("abaaa", 1, 3);
        test("a", 4, 1);
        test("", 6, 0);
    }
}
