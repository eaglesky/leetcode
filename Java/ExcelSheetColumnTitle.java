public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        for(; n > 0; n /= 26) {
            --n;
            sb.append((char)('A' + n % 26));
        }
        return sb.reverse().toString();
    }

    //More easy-to-understand implementation,
    //O(log_26(n)) time and O(1) extra space 
    //(a_n-1 a_n-2 ... a_0) = a_n-1 * 26^(n-1) + ... + a_0 * 1,
    //1 <= a_i <= 26. This can represent every number succesively.
    //E.g. ZZ = 26^2 + 26, next one is AAA = 26^2 + 26 + 1
    //So if a_0 == 26, n % 26 == 0, next_n = n / 26 - 1;
    //otherwise, n % 26 == a_0, next_n = n / 26.
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        for (; n > 0;) {
            int cur = n % 26;
            if (cur == 0) {
                n = n / 26 - 1;
                sb.append('Z');
            } else {
                n = n / 26;
                sb.append((char)('A' + (cur - 1)));
            }
        }
        return sb.reverse().toString();
    }
}