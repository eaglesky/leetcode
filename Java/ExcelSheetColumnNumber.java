public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); ++i) {
            int cur = s.charAt(i) - 'A' + 1;
            num = num * 26 + cur;
        }
        return num;
    }
}