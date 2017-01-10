public class WordLadder2 {
    
    private void getPaths(String endWord, Map<String, List<String>> prevs, List<String> path, List<List<String>> result) {
        List<String> prevStrings = prevs.get(endWord);
        if (prevStrings == null) {
            return;
        }
        path.add(endWord); //Can also use add(0, endWord) to add to the front,
                           //so that there is no need to reverse the path later.
                           //This is only better if the list if linked list
        for (String prevString : prevStrings) {
            getPaths(prevString, prevs, path, result);
        }
        if (prevStrings.isEmpty()) {
            List<String> pathReversed = new ArrayList<String>(path);
            Collections.reverse(pathReversed);
            result.add(pathReversed);
        }
        path.remove(path.size()-1);
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Deque<String> q = new ArrayDeque<>();
        Map<String, Integer> str2Dis = new HashMap<>();
        Map<String, List<String>> prevs = new HashMap<>();
        q.offer(beginWord);
        str2Dis.put(beginWord, 1);
        prevs.put(beginWord, new ArrayList());
        int shortestDis = -1;
        while (!q.isEmpty()) {
            String curWord = q.poll();
            int dis = str2Dis.get(curWord);
            if (shortestDis > -1 && dis >= shortestDis) {
                break;
            }
            for (int i = 0; i < curWord.length(); ++i) {
                char[] chars = curWord.toCharArray();
                for (char c = 'a'; c <= 'z'; ++c) {
                    if (c == curWord.charAt(i)) {
                        continue;
                    }
                    chars[i] = c;
                    String adjWord = String.valueOf(chars);
                    if (adjWord.equals(endWord)) {
                        shortestDis = dis + 1;
                    }
                    if (wordList.contains(adjWord) || (adjWord.equals(endWord))) {
                        if (!str2Dis.containsKey(adjWord)) {
                            str2Dis.put(adjWord, dis+1);
                            q.offer(adjWord);
                            List<String> prevList = prevs.get(adjWord);
                            if (prevList == null) {
                                prevList = new ArrayList<>();
                                prevs.put(adjWord, prevList);
                            }
                            prevList.add(curWord);
                        } else if (str2Dis.get(adjWord) == dis + 1) {
                            prevs.get(adjWord).add(curWord);
                        }
                    } 
                }
            }
        }
        getPaths(endWord, prevs, new ArrayList<String>(), result);
        return result;
    }
}