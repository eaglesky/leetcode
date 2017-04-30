public class Integer2EnglishWords {
    
    //The speed of creating the dict can be improved by
    //storing the words in different string arrays.
    //Storing the trailing space is a good way of handling the spaces between words
    private static Map<Integer, String> createDict() {
        Map<Integer, String> dict = new HashMap<>();
        dict.put(0, "");
        dict.put(1, "One ");
        dict.put(2, "Two ");
        dict.put(3, "Three ");
        dict.put(4, "Four ");
        dict.put(5, "Five ");
        dict.put(6, "Six ");
        dict.put(7, "Seven ");
        dict.put(8, "Eight ");
        dict.put(9, "Nine ");
        dict.put(10, "Ten ");
        dict.put(11, "Eleven ");
        dict.put(12, "Twelve ");
        dict.put(13, "Thirteen ");
        dict.put(14, "Fourteen ");
        dict.put(15, "Fifteen ");
        dict.put(16, "Sixteen ");
        dict.put(17, "Seventeen ");
        dict.put(18, "Eighteen ");
        dict.put(19, "Nineteen ");
        dict.put(20, "Twenty ");
        dict.put(30, "Thirty ");
        dict.put(40, "Forty ");
        dict.put(50, "Fifty ");
        dict.put(60, "Sixty ");
        dict.put(70, "Seventy ");
        dict.put(80, "Eighty ");
        dict.put(90, "Ninety ");
        dict.put(100, "Hundred ");
        dict.put(1000, "Thousand ");
        dict.put(1000000, "Million ");
        dict.put(1000000000, "Billion ");
        return dict;
    }
    
    // 1 <= num <= 999
    private static StringBuilder smallNumber2Words(int num, StringBuilder sb, Map<Integer, String> dict) {
        if (num >= 100) {
            sb.append(dict.get(num / 100));
            sb.append(dict.get(100));
            num = num % 100;
        }
        String word = dict.get(num);
        if (word != null) {
            sb.append(word);
            return sb;
        }
        int remainder = num % 10;
        return sb.append(dict.get(num - remainder)).append(dict.get(remainder));
    }
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> dict = createDict();
        int divisor = 1000000000;
        for (; divisor > 0; divisor = divisor / 1000) {
            int curSegNum = num / divisor;
            if (curSegNum == 0) {
                continue;
            }
            smallNumber2Words(curSegNum, sb, dict);
            if (divisor > 1) {
                sb.append(dict.get(divisor));
            }
            num %= divisor;
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}