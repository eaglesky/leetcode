class PalindromePermutation2 {
    
    private void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
    
    private void permute(char[] str, int id, List<String> result, String oddChar) {
        if (id >= str.length) {
            String curStr = new String(str);
            String lastHalfStr = new StringBuilder(curStr).reverse().toString();
            result.add(curStr + oddChar + lastHalfStr);
            return;
        }
        Set<Character> visited = new HashSet<>();
        for (int i = id; i < str.length; ++i) {
            if (visited.contains(str[i])) {
                continue;
            }
            visited.add(str[i]);
            swap(str, id, i);
            permute(str, id + 1, result, oddChar);
            swap(str, id, i);
        }
    }
    
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        char[] halfStr = new char[chars.length / 2];
        String oddChar = "";
        int id = 0;
        for (int i = 0; i < chars.length;) {
            char c = chars[i];
            int count = 0;
            for (; i < chars.length && chars[i] == c; ++i, ++count);
            for (int j = 0; j < count / 2; ++j) {
                halfStr[id++] = c;
            }
            if ((count & 1) != 0) {
                if (!oddChar.isEmpty()) {
                    return result;
                }
                oddChar = String.valueOf(c);
            }
        }
        permute(halfStr, 0, result, oddChar);
        return result;
    }
}