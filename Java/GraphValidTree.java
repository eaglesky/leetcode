/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a 
pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges 
are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

public class GraphValidTree {
    
    //O(n) time and O(n) space
    private static void dfs(List<List<Integer>> adjList, int cur, Set<Integer> visited) {
        if (visited.contains(cur)) {
            return;
        }
        visited.add(cur);
        for (Integer adj : adjList.get(cur)) {
            dfs(adjList, adj, visited);
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