public class Solution {
    static class Trie {
        static class TrieNode {
            //Can use array too
            final Map<Character, TrieNode> next;
            String str;
            
            public TrieNode() {
                str = null;
                next = new HashMap<>();
            }
        }
        TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode curNode = root;
            for(int i = 0; i < word.length(); ++i) {
                Character c = word.charAt(i);
                TrieNode nextNode = curNode.next.get(c);
                if (nextNode == null) {
                    nextNode = new TrieNode();
                    curNode.next.put(c, nextNode);
                }
                curNode = nextNode;
            }
            if (curNode.str == null) {
                curNode.str = word;
            }
        }
    }
    
    private void dfs(char[][] board, int i, int j, Trie.TrieNode trieNode, List<String> foundStrings) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] == '#') {
            return;
        }
        char original = board[i][j];
        Trie.TrieNode nextNode = trieNode.next.get(original);
        if (nextNode == null) {
            return;
        }
        if (nextNode.str != null) {
            foundStrings.add(nextNode.str);
            nextNode.str = null;
        }
        board[i][j] = '#';
        dfs(board, i, j-1, nextNode, foundStrings);
        dfs(board, i, j+1, nextNode, foundStrings);
        dfs(board, i-1, j, nextNode, foundStrings);
        dfs(board, i+1, j, nextNode, foundStrings);
        board[i][j] = original;
    }
    
    //Good Reference: https://discuss.leetcode.com/topic/33246/java-15ms-easiest-solution-100-00/30
    public List<String> findWords(char[][] board, String[] words) {
        List<String> foundStrings = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return foundStrings;
        }
        Trie trie = new Trie();
        for (String str : words) {
            trie.insert(str);
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                dfs(board, i, j, trie.root, foundStrings);
            }
        }
        return foundStrings;
    }
}