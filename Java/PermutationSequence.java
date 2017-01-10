public class PermutationSequence {
    
    //O(n^2) time and O(1) additional space
    public String getPermutation(int n, int k) {
        if (n < 1 || n > 9) {
            return "";
        }
        int factorial = 1;
        for (int i = 1; i <= n; ++i) {
            factorial *= i;
        }
        if (k <= 0 || k > factorial) {
            return "";
        }
        char[] chars = new char[n];
        for (int i = 0; i < n; ++i) {
            chars[i] = (char)('1' + i);
        }
        k--;
        for (int i = 0; i < n; ++i) {
            factorial /= n - i;
            int remainder = k % factorial;
            int nextId = i + k / factorial;
            char nextChar = chars[nextId];
            for (int j = nextId - 1; j >= i; j--) {
                chars[j+1] = chars[j];
            }
            chars[i] = nextChar;
            k = remainder;
        }
        return new String(chars);
    }
}