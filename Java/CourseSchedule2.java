public class CourseSchedule2 {
    
    //BFS solution
    public int[] findOrder0(int numCourses, int[][] prerequisites) {
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
        int[] result = new int[numCourses];
        int count = 0;
        while (!q.isEmpty()) {
            int courseId = q.poll();
            result[numCourses - count - 1] = courseId;
            count++;
            List<Integer> adjCourses = reqGraph.get(courseId);
            if (adjCourses != null) { //Pay attention here!
                for (int adjCourse : adjCourses) {
                    inBounds[adjCourse]--;
                    if (inBounds[adjCourse] == 0) {
                        q.offer(adjCourse);
                    }
                }                
            }
        }
        return count == numCourses ? result : new int[0];
    }
    
    //visited[start] = 2 if traversing from start doesn't detect a cycle.
    private static boolean dfs(List<List<Integer>> reqGraph, int start, int[] visited, List<Integer> stack) {
        if (visited[start] == 2) {
            return true;
        } else if (visited[start] == 1) {
            return false;
        }
        List<Integer> adjs = reqGraph.get(start);
        visited[start] = 1;
        if (adjs != null) {
            for (Integer adj : adjs) {
                if (!dfs(reqGraph, adj, visited, stack)) {
                    return false;
                }
            }            
        }
        visited[start] = 2;
        stack.add(start);
        return true;
    }
    
    //DFS solution
    //Using List<List<Integer>> instead of hashmap because all the nodes need to 
    //be traversed and added to the stack.
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> reqGraph = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            reqGraph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            List<Integer> adjs = reqGraph.get(prerequisite[0]);
            adjs.add(prerequisite[1]);
        }
        int[] visited = new int[numCourses];
        List<Integer> stack = new ArrayList<>();
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(reqGraph, i, visited, stack)) {
                return new int[0];
            }
        }
        for (int i = 0; i < result.length; ++i) {
            result[i] = stack.get(i);
        }
        return result;
    }
}