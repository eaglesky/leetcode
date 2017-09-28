public class Candy {
    
    //Initialize and fix. Three passes.
    //O(n) time and O(n) space
    public int candy0(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = Math.max(candies[i], candies[i-1] + 1);
            }
        }
        for (int i = ratings.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
            }
        }
        int sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }
    
    //Improved solution, O(n) time and O(1) space
    public int candy(int[] ratings) {
        int prev = 1;
        int total = prev;
        int prevId = 0;
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i-1]) {
                int cur = prev + 1;
                total += cur;
                prev = cur;
                prevId = i;
            } else if (ratings[i] == ratings[i-1]) {
                total += 1;
                prev = 1;
                prevId = i;
            } else if (i == ratings.length - 1 || ratings[i] <= ratings[i + 1]) {
                total += (i - prevId + 1) * (i - prevId) / 2;
                int prevUpdated = Math.max(prev, 1 + i - prevId);
                total += prevUpdated - prev;
                prev = 1;
                prevId = i;
            }
        }
        return total;
    }

    //Second try, O(n) time and O(1) space
    //Iterate each element, compare with the one after to decide what
    //candies to give to the current one.
    public int candy(int[] ratings) {
        int result = 0;
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int cur = 1;
        for (int i = 0; i < ratings.length; ++i) {
            //At the beginning of each iteration, cur is the possible candies satisfying 
            //left side constraint
            if (i == ratings.length - 1 || ratings[i] <= ratings[i + 1]) {
                result += cur;
                if (i < ratings.length - 1 && ratings[i] < ratings[i + 1]) {
                    cur++;
                } else {
                    cur = 1;
                }
            } else {
                int count = 0;
                for (; i < ratings.length - 1 && ratings[i] > ratings[i + 1]; ++i, ++count);
                int newCur = 1 + count;
                result += Math.max(cur, newCur);
                result += (1 + count) * count / 2;
                if (i < ratings.length - 1 && ratings[i] == ratings[i + 1]) {
                    cur = 1;
                } else {
                    cur = 2;
                }
            }
        }
        return result;
    }
}