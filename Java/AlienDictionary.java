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
}