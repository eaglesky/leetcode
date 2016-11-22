import java.util.*;

public class PalindromePairs {

    public class Trie {
        private class TrieNode {
            private int val;
            public final Map<Character, TrieNode> next;

            //Indices of palindrome substrings represented by links after this node
            public final List<Integer> palinIds;
            
            public TrieNode() {
                val = -1;
                next = new HashMap<>();
                palinIds = new ArrayList<>();
            }
            
            public int getVal() {
                return val;
            }
            
            public void setVal(int val) {
                this.val = val;
            }
        }
    
        private TrieNode root;
        private int num;
        
        public Trie() {
            root = new TrieNode();
            num = 0;
        }
    
        //Find the TrieNode immediately after the word
        private TrieNode findNode(String word) {
            TrieNode curNode = root;
            for(int i = 0; i < word.length(); ++i) {
                Character c = word.charAt(i);
                TrieNode nextNode = curNode.next.get(c);
                if (nextNode == null) {
                    return null;
                }
                curNode = nextNode;
            }
            return curNode;
        }
        
        // Inserts a word into the trie.
        // For each node, check if the rest of substring is palindrome,
        // then store the id of the word in the node if true
        public void insert(String word) {
            TrieNode curNode = root;
            for(int i = 0; i < word.length(); ++i) {
                if (isPalindrome(word.substring(i))) {
                    curNode.palinIds.add(num);
                }
                Character c = word.charAt(i);
                TrieNode nextNode = curNode.next.get(c);
                if (nextNode == null) {
                    nextNode = new TrieNode();
                    curNode.next.put(c, nextNode);
                }
                curNode = nextNode;
            }
            curNode.palinIds.add(num);
            if (curNode.getVal() == -1) {
                curNode.setVal(num++);
            }
            
        }
        
        // When the searching word is longer than the matched word
        // (could be multiple during the process), record the id if the rest of the word is palindrome
        // When not, return the indices of palindrome substrings in the last node.
        public List<Integer> search(String word) {
            TrieNode curNode = root;
            List<Integer> result = new ArrayList<>();
            int i = 0;
            for(; i < word.length(); ++i) {
                if (curNode.getVal() >= 0 && isPalindrome(word.substring(i)))  {
                    result.add(curNode.getVal());
                }
                Character c = word.charAt(i);
                TrieNode nextNode = curNode.next.get(c);
                if (nextNode == null) {
                    break;
                }
                curNode = nextNode;
            }
            if (i == word.length()) {
                for(int j : curNode.palinIds) {
                    result.add(j);
                }             
            }

            return result;
        }
    }
    
    //O(k) time
    private boolean isPalindrome(String str) {
        for(int i = 0, j = str.length() - 1; i < j; ++i, --j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    
    // Trie approach.
    // Time: let n be the number of words, k be the average length of the words.
    // Building the trie: O(n*k^2)
    // Searching the trie for each word: 
    //      O(k^2), if search word is longer than matched word in the tree;
    //      O(k^2) + O(number of palindromes in the last node), if otherwise.
    // So the total time is O(n*k^2) + O(number of solutions)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        for(int i = 0; i < words.length; ++i) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            List<Integer> others = trie.search(reversed);
            for(int j : others) {
                if (j != i) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(j);
                    pair.add(i);
                    result.add(pair);             
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"abcd","dcba","lls","s","sssll"};
        PalindromePairs ppairs = new PalindromePairs();
        List<List<Integer>> result = ppairs.palindromePairs(strs);
        System.out.println(result);
    }
}