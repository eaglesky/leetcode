public class CourseSchedule {
    
    //BFS solution(Kahn's algorithm)
    //Instead of implementing reqGraph as a map, we can speed up the run-time
    //by using ArrayList[], which however is not a good design since raw type
    //is just used for backwards compatibility. List<ArrayList<>> requires
    //initializing each element first and takes much space when the graph is sparse,
    //which I think is not good either.
    //Let n == numCourse, m == prerequisites.length,
    //this solution uses O(n + m) time and O(n + m) space
    //Here is the BFS solution that runs faster on OJ
    //https://discuss.leetcode.com/topic/15762/java-dfs-and-bfs-solution
    public boolean canFinish0(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> reqGraph = new HashMap<>();
        int[] inBounds = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            List<Integer> adjs = reqGraph.get(prerequisite[0]);
            if (adjs == null) {
                adjs = new ArrayList<Integer>();
                reqGraph.put(prerequisite[0], adjs);
            }
            adjs.add(prerequisite[1]);
            inBounds[prerequisite[1]]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < inBounds.length; ++i) {
            if (inBounds[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int courseId = q.poll();
            List<Integer> adjCourses = reqGraph.get(courseId);
            reqGraph.remove(courseId);
            if (adjCourses != null) { //Pay attention here!
                for (int adjCourse : adjCourses) {
                    inBounds[adjCourse]--;
                    if (inBounds[adjCourse] == 0) {
                        q.offer(adjCourse);
                    }
                }                
            }
        }
        return reqGraph.isEmpty();
    }
    
    //visited[start] = 2 if traversing from start doesn't detect a cycle.
    private static boolean dfs(Map<Integer, List<Integer>> reqGraph, int start, int[] visited) {
        if (visited[start] == 2) {
            return true;
        } else if (visited[start] == 1) {
            return false;
        }
        List<Integer> adjs = reqGraph.get(start);
        visited[start] = 1;
        if (adjs != null) {
            for (Integer adj : adjs) {
                if (!dfs(reqGraph, adj, visited)) {
                    return false;
                }
            }            
        }
        visited[start] = 2;
        return true;
    }
    
    //DFS solution, O(n + m) time and O(n + m) space
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> reqGraph = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> adjs = reqGraph.get(prerequisite[0]);
            if (adjs == null) {
                adjs = new ArrayList<Integer>();
                reqGraph.put(prerequisite[0], adjs);
            }
            adjs.add(prerequisite[1]);
        }
        int[] visited = new int[numCourses];
        for (Integer i : reqGraph.keySet()) {
            if (!dfs(reqGraph, i, visited)) {
                return false;
            }
        }
        return true;
    }
    

}