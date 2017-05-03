public class GraphValidTree {
    
    //O(n) time and O(n) space
    private static void dfs(List<List<Integer>> adjList, int cur, Set<Integer> visited) {
        visited.add(cur);
        for (Integer adj : adjList.get(cur)) {
            if (!visited.contains(adj)) {
                dfs(adjList, adj, visited);
            }
        }
    }
    
    //Can also use BFS
    private static boolean isConnected(List<List<Integer>> adjList) {
        Set<Integer> visited = new HashSet<>();
        dfs(adjList, 0, visited);
        return visited.size() == adjList.size();
    }
    
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0 || edges.length != (n - 1)) {
            return false;
        }
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; ++i) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }
        return isConnected(adjList);
    }
    
    //Another solution is union-find, which has higher time complexity.
}