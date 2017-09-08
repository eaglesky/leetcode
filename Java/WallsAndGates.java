/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF
as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a 
gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/

public class WallsAndGates {
    
    private static void addPosToQueue(int[][] rooms, int r, int c, Deque<int[]> q, boolean[][] visited) {
        if (r < 0 || r >= rooms.length || c < 0 || c >= rooms[0].length
            || rooms[r][c] <= 0 || visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        q.offer(new int[]{r, c});
    }
    
    private static void updateGrid(int[][] rooms, int r, int c) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        for(int d = 0; !q.isEmpty(); ++d) {
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                int[] pos = q.poll();
                rooms[pos[0]][pos[1]] = Math.min(rooms[pos[0]][pos[1]], d);
                addPosToQueue(rooms, pos[0]-1, pos[1], q, visited);
                addPosToQueue(rooms, pos[0]+1, pos[1], q, visited);
                addPosToQueue(rooms, pos[0], pos[1]-1, q, visited);
                addPosToQueue(rooms, pos[0], pos[1]+1, q, visited);
            }
        }
    }
    
    //Naive BFS solution. O(mn * mn) time and O(mn) space
    public void wallsAndGates0(int[][] rooms) {
        if (rooms.length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[i].length; ++j) {
                if (rooms[i][j] == 0) {
                    updateGrid(rooms, i, j);
                }
            }
        }
    }
    
    //Improved BFS solution. O(mn) time and O(mn) space
    //https://discuss.leetcode.com/topic/25265/java-bfs-solution-o-mn-time
    private static void addPosToQueueImproved(int[][] rooms, int r, int c, Deque<int[]> q, int d) {
        if (r < 0 || r >= rooms.length || c < 0 || c >= rooms[0].length
            || rooms[r][c] != Integer.MAX_VALUE) {
            return;
        }
        rooms[r][c] = d;
        q.offer(new int[]{r, c});
    }
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0) {
            return;
        }
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[i].length; ++j) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        for (int d = 0; !q.isEmpty(); ++d) {
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                int[] pos = q.poll();
                addPosToQueueImproved(rooms, pos[0]-1, pos[1], q, d + 1);
                addPosToQueueImproved(rooms, pos[0]+1, pos[1], q, d + 1);
                addPosToQueueImproved(rooms, pos[0], pos[1]-1, q, d + 1);
                addPosToQueueImproved(rooms, pos[0], pos[1]+1, q, d + 1);
            }
        }
    }
    
    //Comparison of all solutions: 
    //https://discuss.leetcode.com/topic/35242/benchmarks-of-dfs-and-bfs
    //And DFS solution alone: 
    //https://discuss.leetcode.com/topic/29106/java-easiest-dfs-quicker-than-bfs
}