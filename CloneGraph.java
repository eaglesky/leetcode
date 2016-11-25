/**
 * Definition for undirected graph.
 */
import java.util.*;

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};

public class CloneGraph {

    //BFS solution. O(V+E) time, O(V) space
    public UndirectedGraphNode cloneGraph0(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Deque<UndirectedGraphNode> q = new ArrayDeque<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<>();
        q.offer(node);
        UndirectedGraphNode clonedNode = new UndirectedGraphNode(node.label);
        nodeMap.put(node, clonedNode);
        while (!q.isEmpty()) {
            UndirectedGraphNode curNode = q.poll();
            UndirectedGraphNode clonedCurNode = nodeMap.get(curNode);
            for (UndirectedGraphNode neighbor : curNode.neighbors) {
                UndirectedGraphNode clonedNeighbor = nodeMap.get(neighbor);
                if (clonedNeighbor == null) {
                    clonedNeighbor = new UndirectedGraphNode(neighbor.label);
                    nodeMap.put(neighbor, clonedNeighbor);
                    q.offer(neighbor);
                }
                clonedCurNode.neighbors.add(clonedNeighbor);
            }
        }
        return clonedNode;
    }
    
    //DFS solution. O(V + E) time, O(V) space
    void dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap) {
        UndirectedGraphNode clonedNode = nodeMap.get(node);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            UndirectedGraphNode clonedNeighbor = nodeMap.get(neighbor);
            if (clonedNeighbor == null) {
                clonedNeighbor = new UndirectedGraphNode(neighbor.label);
                nodeMap.put(neighbor, clonedNeighbor);
                dfs(neighbor, nodeMap);
            }
            clonedNode.neighbors.add(clonedNeighbor);
        }
    }
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<>();
        UndirectedGraphNode clonedNode = new UndirectedGraphNode(node.label);
        nodeMap.put(node, clonedNode);
        dfs(node, nodeMap);
        return clonedNode;
    }

    public static void main(String[] args) {
        CloneGraph cg = new CloneGraph ();
        UndirectedGraphNode node = new UndirectedGraphNode(0);
        node.neighbors.add(node);
        node.neighbors.add(node);

        UndirectedGraphNode clonedNode = cg.cloneGraph(node);
    }
}