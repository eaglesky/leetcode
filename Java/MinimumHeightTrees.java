public class MinimumHeightTrees {
    
    //O(n) time and O(n+) space.
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