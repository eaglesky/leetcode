public class ValidPalindrome {
    private boolean compare(char c1, char c2) {
        if (Character.isLetter(c1) && Character.isLetter(c2)
            && (Character.toLowerCase(c1) == Character.toLowerCase(c2))) {
                return true;
        }
        if (Character.isDigit(c1) && Character.isDigit(c2)
            && (c1 == c2)) {
                return true;
            }
        return false;
    }
    
    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j;) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if (!Character.isLetterOrDigit(c1)) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(c2)) {
                j--;
                continue;
            }
            if (!compare(c1, c2)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();
        System.out.println("Input : " + args[0]);
        System.out.println(solution.isPalindrome(args[0]));
    }
}