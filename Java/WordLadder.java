public class WordLadder {
    
    // Naive BFS solution
    public int ladderLength0(String beginWord, String endWord, Set<String> wordList) {
        if (wordList.isEmpty()) {
            return 0;
        }
        Deque<String> q = new ArrayDeque<>();
        Map<String, Integer> str2Dis = new HashMap<>();
        q.offer(beginWord);
        str2Dis.put(beginWord, 1);
        while (!q.isEmpty()) {
            String curWord = q.poll();
            int dis = str2Dis.get(curWord);
            if (curWord.equals(endWord)) {
                return dis;
            }
            for (int i = 0; i < curWord.length(); ++i) {
                char[] chars = curWord.toCharArray();
                for (char c = 'a'; c <= 'z'; ++c) {
                    if (c == curWord.charAt(i)) {
                        continue;
                    }
                    chars[i] = c;
                    String adjWord = String.valueOf(chars);
                    if (wordList.contains(adjWord) && !str2Dis.containsKey(adjWord)) {
                        str2Dis.put(adjWord, dis+1);
                        q.offer(adjWord);
                    }
                }
            }
        }
        return 0;
    }
    
    //Remove the distance from the visited map, thus reduce the memory usage a bit
    public int ladderLength1(String beginWord, String endWord, Set<String> wordList) {
        if (wordList.isEmpty()) {
            return 0;
        }
        Deque<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.offer(beginWord);
        visited.add(beginWord);
        for (int dis = 1; !q.isEmpty(); ++dis) {
            int curSize = q.size();
            for (int i = 0; i < curSize; ++i) {
                String curWord = q.poll();
                
                //Deals with the case when the words are empty.
                //This edge case is not included in OJ
                if (curWord.equals(endWord)) {
                    return dis;
                }
                for (int j = 0; j < curWord.length(); ++j) {
                    char[] chars = curWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == curWord.charAt(j)) {
                            continue;
                        }
                        chars[j] = c;
                        String adjWord = String.valueOf(chars);
                        if (adjWord.equals(endWord)) {
                            return dis + 1;
                        }
                        if (wordList.contains(adjWord) && !visited.contains(adjWord)) {
                            visited.add(adjWord);
                            q.offer(adjWord);
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    //Updated version, the wordList has changed to List instead of Set
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() != endWord.length() || wordList.isEmpty()) {
            return 0;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        Deque<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        wordSet.remove(beginWord);
        int level = 1;
        for (;!q.isEmpty(); ++level) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                String curWord = q.poll();
                char[] curWordChars = curWord.toCharArray();
                for (int j = 0; j < curWordChars.length; ++j) {
                    char c = curWordChars[j];
                    for (char newC = 'a'; newC <= 'z'; ++newC) {
                        if (newC != c) {
                            curWordChars[j] = newC;
                            String adjWord = new String(curWordChars);
                            if (wordSet.contains(adjWord)) {
                                if (adjWord.equals(endWord)) {
                                    return level + 1;
                                }
                                q.offer(adjWord);
                                wordSet.remove(adjWord);
                            }
                        }
                    }
                    curWordChars[j] = c;
                }                
            }
        }
        return 0;
    }
    
    //Bidirectional BFS
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (wordList.isEmpty()) {
            return 0;
        }
        Deque<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.offer(beginWord);
        visited.add(beginWord);
        Deque<String> q2 = new ArrayDeque<>();
        Set<String> visited2 = new HashSet<>();
        q2.offer(endWord);
        visited2.add(endWord);
        for (int dis = 1; !q.isEmpty() && !q2.isEmpty(); ++dis) {
            Deque<String> temp = q;
            q = q2;
            q2 = temp;
            Set<String> tempSet = visited;
            visited = visited2;
            visited2 = tempSet;
            int curSize = q.size();
            for (int i = 0; i < curSize; ++i) {
                String curWord = q.poll();
                //Deals with the case when the words are empty.
                //This edge case is not included in OJ
                if (visited2.contains(curWord)) {
                    return dis;
                }
                for (int j = 0; j < curWord.length(); ++j) {
                    char[] chars = curWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == curWord.charAt(j)) {
                            continue;
                        }
                        chars[j] = c;
                        String adjWord = String.valueOf(chars);
                        if (visited2.contains(adjWord)) {
                            return dis + 1;
                        }
                        if (wordList.contains(adjWord) && !visited.contains(adjWord)) {
                            visited.add(adjWord);
                            q.offer(adjWord);
                        }
                    }
                }
            }
        }
        return 0;
    }
}