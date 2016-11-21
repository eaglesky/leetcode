public class WordDictionary {
    
    private class TrieNode {
        static final int R = 26;
        boolean isWord;
        final TrieNode[] next;
        TrieNode() {
            isWord = false;
            next = new TrieNode[R];
        }
    }
    
    private final TrieNode root = new TrieNode();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode curNode = root;
        for(int i = 0; i < word.length(); ++i) {
            char c = Character.toLowerCase(word.charAt(i));
            if (c < 'a' || c > 'z') {
                return;
            }
            TrieNode nextNode = curNode.next[c - 'a'];
            if (nextNode == null) {
                nextNode = new TrieNode();
                curNode.next[c - 'a'] = nextNode;
            }
            curNode = nextNode;
        }
        curNode.isWord = true;
    }

    private boolean findNode(TrieNode node, String word, int d) {
        if (node == null) {
            return false;
        }
        if (d == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(d);
        if (c != '.') {
            return findNode(node.next[c - 'a'], word, d+1);
        } else {
            for(int i = 0; i < TrieNode.R; ++i) {
                if (findNode(node.next[i], word, d+1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.

    // Can also be implemented iteratively using a stack, not hard
    public boolean search(String word) {
        return findNode(root, word, 0);
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");