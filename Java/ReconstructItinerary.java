public class ReconstructIterary {
    
    public static class Airport{
        String name;
        boolean visited;
        Airport(String name) {
            this.name = name;
            visited = false;
        }
    }
    
    private static boolean dfs(Map<String, List<Airport>> graph, String start, List<String> iter, int numEdges) {
        if (iter.size() - 1 == numEdges) {
            return true;
        }
        List<Airport> dests = graph.get(start);
        if (dests != null) {
            for (Airport dest : dests) {
                if (!dest.visited) {
                    dest.visited = true;
                    iter.add(dest.name);
                    if (dfs(graph, dest.name, iter, numEdges)) {
                        return true;
                    }
                    iter.remove(iter.size()-1);
                    dest.visited = false;
                }
            }
        }
        return false;
    }
    
    //Naive DFS solution, O(p^E), p is the average of degrees,
    //and E is the number of edges.
    public List<String> findItinerary(String[][] tickets) {
        int numEdges = tickets.length;
        List<String> iter = new ArrayList<>();
        iter.add("JFK");
        if (numEdges == 0) {
            return iter;
        }
        Map<String, List<Airport>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            List<Airport> dests = graph.get(ticket[0]);
            if (dests == null) {
                dests = new ArrayList<Airport>();
                graph.put(ticket[0], dests);
            }
            dests.add(new Airport(ticket[1]));
        }
        for (List<Airport> dests : graph.values()) {
            dests.sort(new Comparator<Airport>() {
                @Override
                public int compare(Airport a1, Airport a2) {
                    return a1.name.compareTo(a2.name);
                }
            });
        }
        dfs(graph, "JFK", iter, numEdges);
        return iter;
    }
    
    //Another solution using Hierholzer's algorithm, which I don't understand why it works yet.
    //It runs in O(E) time, using a priority queue to store the edges, and LinkedList to store the result.
    //https://discuss.leetcode.com/topic/36383/share-my-solution
    //Explaination: https://discuss.leetcode.com/topic/36370/short-ruby-python-java-c
    //Iterative solution:https://discuss.leetcode.com/topic/36385/share-solution-java-greedy-stack-15ms-with-explanation
    
}