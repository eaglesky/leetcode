public class palindromePairs {

    public class Trie {
        private class TrieNode {
            private int val;
            
            //Can use array too
            public final Map<Character, TrieNode> next;
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
            if (curNode.getVal() == -1) {
                curNode.setVal(num++);
            }
        }
    }
    
    private boolean isPalindrome(String str) {
        for(int i = 0, j = str.length() - 1; i < j; ++i, --j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        for(int i = 0; i < words.length; ++i) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            
        }
    }
}