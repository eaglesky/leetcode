public class Roman2Int {
    
    int map(char c)
    {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
    
    // See the c++ solution for a better solution
    public int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length();) {
            if ((i < s.length()-1) && (map(s.charAt(i)) < map(s.charAt(i+1)))) {
                result += map(s.charAt(i+1)) - map(s.charAt(i));
                i += 2;
            } else {
                result += map(s.charAt(i));
                i++;
            }
        }
        return result;
    }

    //Better solution
    public int romanToInt(String s) {
        Map<Character, Integer> dict = new HashMap<>();
        dict.put('I', 1);
        dict.put('V', 5);
        dict.put('X', 10);
        dict.put('L', 50);
        dict.put('C', 100);
        dict.put('D', 500);
        dict.put('M', 1000);
        
        int result = 0;
        for (int i = 0; i < s.length(); ++i) {
            int cur = dict.get(s.charAt(i));
            if (i == s.length() - 1 || dict.get(s.charAt(i+1)) <= cur) {
                result += cur;
            } else {
                result -= cur;
            }
        }
        return result;
    }
}