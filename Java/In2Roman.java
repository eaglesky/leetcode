public class Int2Roman {

    public String intToRoman(int num) {
        int[] radix = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < radix.length;) {
            if (num >= radix[i]) {
                num -= radix[i];
                sb.append(romans[i]);
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    //Another implementation is using TreeMap to store the mapping of integer to Roman strings.
    //But it is harder to initialize.
}