public class AlienDictionary {
    
    private static boolean dfsTopoSort(Map<Character, List<Character>> adjGraph, char c, Set<Character> charSet, 
            Set<Character> pathSet, char[] sortedArray, int[] id) {
        if (pathSet.contains(c)) {
            return false;
        }
        if (!charSet.contains(c)) {
            return true;
        }
        pathSet.add(c);
        charSet.remove(c);
        List<Character> adjs = adjGraph.get(c);
        if (adjs != null) {
            for (Character adj : adjs) {
                if (!dfsTopoSort(adjGraph, adj, charSet, pathSet, sortedArray, id)) {
                    return false;
                }
            }
        }
        sortedArray[id[0]--] = c;
        pathSet.remove(c);
        return true;
    }
            
    private static String topoSort(Map<Character, List<Character>> adjGraph, Set<Character> charSet) {
        char[] sortedArray = new char[charSet.size()];
        int[] id = new int[]{charSet.size()-1};
        for (Map.Entry<Character, List<Character>> entry : adjGraph.entrySet()) {
            if (!dfsTopoSort(adjGraph, entry.getKey(), charSet, new HashSet<>(), sortedArray, id)) {
                return "";
            }
        }
        for (Character c : charSet) {
            sortedArray[id[0]--] = c;
        }
        return new String(sortedArray);
    }
    
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adjGraph = new LinkedHashMap<>();
        Set<Character> charSet = new LinkedHashSet<>();
        String prevWord = "";
        for (String word : words) {
            int iPre = 0, i = 0;
            for(; iPre < prevWord.length() && i < word.length() && prevWord.charAt(iPre) == word.charAt(i);
                    ++iPre, ++i) {
                charSet.add(prevWord.charAt(iPre));     
            }
            if (iPre != prevWord.length() && i != word.length()) {
                List<Character> adjList = adjGraph.get(prevWord.charAt(iPre));
                if (adjList == null) {
                    adjList = new ArrayList<>();
                    adjGraph.put(prevWord.charAt(iPre), adjList);
                }
                adjList.add(word.charAt(i));
            }
            for (; iPre < prevWord.length(); ++iPre) {
                charSet.add(prevWord.charAt(iPre));
            }
            for (; i < word.length(); ++i) {
                charSet.add(word.charAt(i));
            }
            prevWord = word;
        }
        return topoSort(adjGraph, charSet);
    }

    //BFS solution. Slow
    private String getTopoSort(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> inBounds = new LinkedHashMap<>();
        for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
            inBounds.putIfAbsent(entry.getKey(), 0);
            for (Character c : entry.getValue()) {
                int curNum = inBounds.getOrDefault(c, 0);
                inBounds.put(c, curNum + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : inBounds.entrySet()) {
            if (entry.getValue() == 0) {
                sb.append(entry.getKey());
            }
        }
        for (int i = 0; i < sb.length(); ++i) {
            char c = sb.charAt(i);
            for (char adjC : graph.get(c)) {
                inBounds.put(adjC, inBounds.get(adjC) - 1);
                if (inBounds.get(adjC) == 0) {
                    sb.append(adjC);
                }
            }
        }
        return (sb.length() < graph.size()) ? "" : sb.toString();
    }
    
    public String alienOrder(String[] words) {
        if (words == null) {
            return "";
        }
        Map<Character, Set<Character>> graph = new LinkedHashMap<>();
        String prevWord = "";
        for (String curWord : words) {
            int j = 0;
            for (int i = 0; i < prevWord.length() && j < curWord.length(); ++i, ++j) {
                char prevC = prevWord.charAt(i);
                char curC = curWord.charAt(j);
                graph.computeIfAbsent(curC, k -> new LinkedHashSet<>());
                if (prevC != curC) {
                    graph.get(prevC).add(curC);
                    break;
                }
            }
            for (; j < curWord.length(); ++j) {
                graph.computeIfAbsent(curWord.charAt(j), k -> new LinkedHashSet<>());
            }
            prevWord = curWord;
        }
        return getTopoSort(graph);
    }
}