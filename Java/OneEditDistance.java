public class OneEditDistance {
    
    //O(ls + lt) time and O(1) space
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        int is = 0;
        int it = 0;
        for (; is < s.length() && it < t.length() && s.charAt(is) == t.charAt(it); is++, it++);
        if (is == s.length() || it == t.length()) {
            return (s.length() != t.length());
        }
        if (s.length() == t.length()) {
            return s.substring(is+1).equals(t.substring(it+1));
        } else if (s.length() > t.length()) {
            return s.substring(is+1).equals(t.substring(it));
        } else {
            return s.substring(is).equals(t.substring(it+1));
        }
    }
}