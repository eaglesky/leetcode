class FrogJump {
    
    //O(n^2) time, O(n^2) space
    //Maintain a map of current position to set of last steps jumping to it.
    //For each stone, find out all the possible stones that can be reached from it.
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        Map<Integer, Set<Integer>> posToSteps = new HashMap<>();
        for (int i = 0; i < stones.length; ++i) {
            Set<Integer> steps = new LinkedHashSet<>();
            if (i == 0) {
                steps.add(stones[i]);
            }
            posToSteps.put(stones[i], steps);
        }
        for (int j = 0; j < stones.length - 1; ++j) {
            int pos = stones[j];
            Set<Integer> steps = posToSteps.get(pos);
            for (int step : steps) {
                for (int i = -1; i <= 1; ++i) {
                    int nextPos = pos + step + i;
                    Set<Integer> nextSteps = posToSteps.get(nextPos);
                    if (nextSteps != null) {
                        nextSteps.add(step + i);
                    }
                }
            }
        }
        return !posToSteps.get(stones[stones.length - 1]).isEmpty();
    }
    
    //Another solution is DP. From right to left, same time complexity,
    //but use much more space.
}