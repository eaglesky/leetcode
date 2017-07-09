public class Solution {
    
    /* Rules:
	' ': Leading and trailing spaces are valid, however spaces in between
	     characters are not.
	'e': No more than 1. If exists, its left must be a valid number and right
  		 must be a valid integer(without dot). Neigher side can be empty.
	'.': Can only be on left side of 'e', no more than 1. Either its left side 
	     or right side must be a digit.
	'+'/'-': On either side of 'e', no more than 1. Must be located before digits
			 or dot.
	'0'--'9': Digits can be anywhere.
	*/
    private boolean isValidNumber(String s, boolean isInteger) {
        if (s.isEmpty()) {
            return false;
        }
        int startId = 0;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            startId = 1;
            if (startId >= s.length()) {
                return false;
            }
        }
        boolean foundDot = false;
        int numDigit = 0;
        for (int i = startId; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                numDigit++;
            } else if (c == '.') {
                if (foundDot || isInteger) {
                    return false;
                }
                foundDot = true;
            } else {
                return false;
            }
        }
        if (foundDot && numDigit == 0) {
            return false;
        }
        return true;
    }
    
    public boolean isNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        String trimmedStr = s.trim();
        int eid = -1;
        for (int i = 0; i < trimmedStr.length(); ++i) {
            char c = trimmedStr.charAt(i);
            if (c == 'e') {
                if (eid >= 0) {
                    return false;
                }
                eid = i;
            } else if (!Character.isDigit(c) && c != '.' && c != '+' && c != '-') {
                return false;
            }
        }
        if (eid < 0) {
            return isValidNumber(trimmedStr, false);
        } else {
            return isValidNumber(trimmedStr.substring(0, eid), false)
                && isValidNumber(trimmedStr.substring(eid+1), true);
        }
    }
}