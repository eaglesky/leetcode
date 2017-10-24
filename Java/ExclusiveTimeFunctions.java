class ExclusiveTimeFunctions {
    
    //O(n) time and O(n) space
    public int[] exclusiveTime(int n, List<String> logs) {
        if (n <= 0 || logs == null || logs.isEmpty()) {
            return new int[0];
        }
        int[] result = new int[n];
        Deque<int[]> stack = new ArrayDeque<>();
        for (String log : logs) {
            String[] secs = log.split(":");
            int funcId = Integer.parseInt(secs[0]);
            int timestamp = Integer.parseInt(secs[2]);
            if (secs[1].equals("start")) {
                stack.push(new int[]{funcId, timestamp, 0});
            } else {
                int[] prev = stack.pop();
                int totalTime = timestamp - prev[1] + 1;
                int execTime = totalTime - prev[2];
                if (!stack.isEmpty()) {
                    (stack.peek())[2] += totalTime;
                }
                result[funcId] += execTime;
            }
        }
        return result;
    }
}