public class CountAndSay {
    public String countAndSay(int n) {
        String prev = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < prev.length();) {
                char c = prev.charAt(j);
                int curId = j;
                for (; j < prev.length() && prev.charAt(j) == c; ++j);
                int count = j - curId;
                sb.append((char)('0' + count));
                sb.append(c);
            }
            prev = sb.toString();
        }
        return prev;
    }
}