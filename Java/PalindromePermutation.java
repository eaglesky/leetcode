class PalindromePermutation {
    
    //O(n) time and O(n) space
    public boolean canPermutePalindrome(String s) {
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!chars.contains(c)) {
                chars.add(c);
            } else {
                chars.remove(c);
            }
        }
        return chars.size() <= 1;
    }
}