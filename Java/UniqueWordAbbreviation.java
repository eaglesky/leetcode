/*
An abbreviation of a word follows the form <first letter><number><last letter>. 'number' refers to the
number of letters between the first and last letter. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the 
dictionary. A word's abbreviation is unique if no other word from the dictionary has the same
abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> 
false

isUnique("cart") -> 
true

isUnique("cane") -> 
false

isUnique("make") -> 
true

More explaination:
https://discuss.leetcode.com/topic/37254/let-me-explain-the-question-with-better-examples
"other word" here means the different word. For the same abbreviation, if there are more than one
word in the dictionary plus the given word that can be abbrevated to it, we should return false.
so the isUnique really means if the abbreviation is unique.
1) [“dog”]; isUnique(“dig”) = false;
2) [“dog”, “dog"]; isUnique(“dog”) = true;
3) [“dog”, “dig”]; isUnique(“dog”) = false;

*/

public class ValidWordAbbr {

    //This can be optimized to Map<String, String>.
    //If one abbreviated word corresponds to multiple words, then the 
    //value is "". This will still work if there is "" in the dictionary 
    //or the given word. Since the word that is abbrivated to "" can only
    //be "".
    //https://discuss.leetcode.com/topic/30533/java-solution-with-one-hashmap-string-string-beats-90-of-submissions
    private final Map<String, Set<String>> abbrevs = new HashMap<>();
    
    private String abbreviate(String word) {
        if (word.length() <= 2) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0));
        sb.append(word.length() - 2);
        sb.append(word.charAt(word.length() - 1));
        return sb.toString();
    }
    
    //O(n) time
    public ValidWordAbbr(String[] dictionary) {
        for (String word : dictionary) {
            String abbreviated = abbreviate(word);
            abbrevs.computeIfAbsent(abbreviated, k -> new HashSet<>())
                   .add(word);
        }
    }
    
    //O(1) time
    public boolean isUnique(String word) {
        Set<String> words = abbrevs.get(abbreviate(word));
        if (words == null || (words.size() == 1 && words.contains(word))) {
            return true;
        }
        return false;
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */