import java.util.*;

class TrieNode {
    private int val;
    
    //Can use array too
    public final Map<Character, TrieNode> charToNode;

    public TrieNode() {
        val = -1;
        charToNode = new HashMap<>();
    }
    
    public int getVal() {
        return val;
    }
    
    public void setVal(int val) {
        this.val = val;
    }
}

public class Trie {
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
            TrieNode nextNode = curNode.charToNode.get(c);
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
            Character c = word.charAt(i);
            TrieNode nextNode = curNode.charToNode.get(c);
            if (nextNode == null) {
                nextNode = new TrieNode();
                curNode.charToNode.put(c, nextNode);
            }
            curNode = nextNode;
        }
        if (curNode.getVal() == -1) {
            curNode.setVal(num++);
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.getVal() >= 0;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private void collectStrings(TrieNode curNode, String prefix, List<String> result) {
        if (curNode == null) {
            return;
        }
        if (curNode.getVal() >= 0) {
            result.add(prefix);
        }
        for(Map.Entry<Character, TrieNode> entry : curNode.charToNode.entrySet()) {
            char c = entry.getKey();
            TrieNode nextNode = entry.getValue();
            collectStrings(nextNode, prefix + c, result);
        }
    }

    // Returns all the strings with a given prefix
    // If the given prefix is "", then returns all the strings in the trie
    public List<String> keysWithPrefix(String prefix) {
        TrieNode startNode = findNode(prefix);
        List<String> result = new ArrayList<>();
        collectStrings(startNode, prefix, result);
        return result;
    }

    // Returns the longest prefix of a given string in the trie
    public String getLongestPrefix(String word) {
        int prefixId = -1;
        TrieNode curNode = root;
        for(int i = 0; i < word.length(); ++i) {
            Character c = word.charAt(i);
            TrieNode nextNode = curNode.charToNode.get(c);
            if (nextNode == null) {
                break;
            }
            if (nextNode.getVal() >= 0) {
                prefixId = i;
            }
            curNode = nextNode;
        }
        return (prefixId < 0) ? "" : word.substring(0, prefixId + 1);
    }

    // Delete the key that equals to word, or do nothing if word is not found in the trie
    public void delete(String word) {
        Deque<TrieNode> stack = new ArrayDeque<>();
        TrieNode curNode = root;
        for(int i = 0; i < word.length(); ++i) {
            Character c = word.charAt(i);
            TrieNode nextNode = curNode.charToNode. get(c);
            if (nextNode == null) {
                return;
            }
            stack.push(curNode);
            curNode = nextNode;
        }
        if (curNode.getVal() >= 0) {
            curNode.setVal(-1);
            TrieNode preNode = curNode;
            for(int i = word.length() - 1; i >= 0 && !stack.isEmpty(); --i) {
                curNode = stack.pop();
                if (preNode != null && preNode.charToNode.size() > 0) {
                    return;
                }
                curNode.charToNode.remove(word.charAt(i));
                preNode = curNode;
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] strs = new String[]{
            "by",
            "sea",
            "sells",
            "she",
            "shells",
            "shore",
            "the"
        };
        for (String str : strs) {
            trie.insert(str);
        }
        List<String> allStrsInTrie = trie.keysWithPrefix("");
        System.out.println("All strings in trie:");
        System.out.println(allStrsInTrie);

        List<String> strsInTrieS = trie.keysWithPrefix("s");
        System.out.println("Strings start with s");
        System.out.println(strsInTrieS);

        List<String> strsInTrieNotExist = trie.keysWithPrefix("bye");
        System.out.println("Strings start with bye");
        System.out.println(strsInTrieNotExist);

        System.out.println("Longest prefix of shed is : " + trie.getLongestPrefix("shed"));
        System.out.println("Longest prefix of shellst is : " + trie.getLongestPrefix("shellst"));
        System.out.println("Longest prefix of th is : " + trie.getLongestPrefix("th"));
        System.out.println("Longest prefix of shore is : " + trie.getLongestPrefix("shore"));

        trie.delete("she");
        System.out.println("After she is deleted:");
        System.out.println(trie.keysWithPrefix(""));

        trie.delete("the");
        System.out.println("After the is deleted:");
        System.out.println(trie.keysWithPrefix(""));

        trie.delete("shore");
        System.out.println("After shore is deleted:");
        System.out.println(trie.keysWithPrefix(""));

        trie.delete("bye");
        System.out.println("After bye is deleted:");
        System.out.println(trie.keysWithPrefix(""));
    }
}