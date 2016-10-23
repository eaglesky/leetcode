import java.util.*;

public class SimplifyPath {

    // Can also try pre-processing the path using split method.
    // Requires more memory
    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < path.length();) {
            int j = i;
            for (; (j < path.length()) && (path.charAt(j) == '/'); ++j);
            int nextSlash = path.indexOf('/', j);
            if (nextSlash == -1) {
                nextSlash = path.length();
            }
            String curStr = path.substring(j, nextSlash);
            i = nextSlash + 1;
            if (curStr.equals(".")) {
                continue;
            } else if (curStr.equals("..")) {
                if (stack.peek() != null) {
                    stack.pop();
                }
            } else if ((!curStr.isEmpty())){
                stack.push(curStr);
            }
        }
        StringBuilder result = new StringBuilder();
        for(Iterator descItr = stack.descendingIterator(); descItr.hasNext();){
            result.append("/");
            result.append(descItr.next());
        }
        return (result.length() == 0) ? "/" : result.toString();
    }

    public static void main(String[] args) {
        String result = simplifyPath("/a/./b/../../c/");
        System.out.println(result);
    }
}