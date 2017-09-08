public class MinimumHeightTrees {
    
    //O(n) time and O(n+) space.
    //In each tree, the height is equal to the distance from the root
    //to the furthest leaf. The root of MHT must be located on the 
    //longest path between too leaves(say LP), and must be the middle
    //node(s) of LP. Why? For any node outside LP, it must be able to
    //reach LP since the graph is a tree, and there must be a path between
    //that node to a leaf that is longer than the dis between LP middle 
    //node and that leaf. Even if there could be multiple LPs, the root
    //of MHT has to be the middle node(s) of one of them, using simlar
    //proof like before. So the result must contain at most 2 nodes.
    //To find out them, we can iteratively remove leaves of the graph
    //until there are no more than 2 nodes left. Then they are the middle
    //nodes -- root of MHT. The reason why this algorithm works is that
    //everytime you remove the leaves, the length of LP must be reduced
    //by 2, and the length of LP left must be still the maximum length.
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        List<Set<Integer>> adjs = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            adjs.add(new HashSet<>());
        }
        for (int i = 0; i < edges.length; ++i) {
            adjs.get(edges[i][0]).add(edges[i][1]);
            adjs.get(edges[i][1]).add(edges[i][0]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < adjs.size(); ++i) {
            if (adjs.get(i).size() <= 1) {
                q.offer(i);
            }
        }
        while (n > 2) {
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                int cur = q.poll();
                int neighbor = adjs.get(cur).iterator().next();
                Set<Integer> otherNeighbors = adjs.get(neighbor);
                otherNeighbors.remove(cur);
                if (otherNeighbors.size() == 1) {
                    q.offer(neighbor);
                }
                n--;
            }
        }
        while(!q.isEmpty()) {
            result.add(q.poll());
        }
        return result;
    }
}