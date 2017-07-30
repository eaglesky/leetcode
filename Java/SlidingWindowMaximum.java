import java.util.*;

public class SlidingWindowMaximum {

	//Naive solution takes O(nk) time.
	//BST solution takes O(nlogk) time
	//Heap solution takes O(nk) time since remove takes O(k) time

	//Monotonic queue solution
	//https://abitofcs.blogspot.com/2014/11/data-structure-sliding-window-minimum.html
    //Maintaining a two-directional queue that stores decreasing subsequence of 
    //current sequence in the sliding window such that the first element in the 
    //queue is the largest one in the sliding window.
    //Though there is a inner loop, the running time is still O(n) time,
    //since the inner loop only removes the added elements and each element is
    //added exactly once and removed at most once.
    //The space usage is O(k).
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        int id = 0;
        for (int i = 0; i < nums.length; ++i) {
            //It is natural to add the element to the window first and then
            //remove the first one from the window.
            for (; !q.isEmpty() && q.peekLast() < nums[i]; q.pollLast());
            q.offer(nums[i]);
            if (i >= k && !q.isEmpty() && q.peek() == nums[i-k]) {
                q.poll();
            }
            if (i >= k - 1) {
                result[id++] = q.peek();
            }
        }
        return result;
    }


    public static void main(String[] args) {
    	SlidingWindowMaximum solution = new SlidingWindowMaximum();
    	int[] test = new int[] {
    		5183,2271,3067,539,8939,2999,9264,737,3974,5846,-210,9278,5800,2675,6608,1133,
    		-1,6018,9672,5179,9842,7424,-209,2988,2757,5984,1107,2644,-499,7234,7539,6525,
    		347,5718,-742,1797,5292,976,8752,8297,1312,3385,5924,2882,6091,-282,2595,96,
    		1906,8014,7667,5895,7283,7974,-167,7068,3946,6223,189,1589,2058,9277,-302,
    		8157,8256,5261,8067,1071,9470,2682,8197,5632,753,3179,8187,9042,8167,4657,
    		7080,7801,5627,7917,8085,928,-892,-427,3685,4676,2431,8064,8537,343,505,4352,
    		2108,4399,66,2086,1922,9126,9460,393,443,5689,7595,850,8493,2866,732,3738,
    		7933,3666,2370,5804,4045,7903,8009,5387,5542,7593,6862,1547,6934,-160,9693,
    		4560,7429,9989,7232,-594,587,6476,9277,4471,5979,6268,2419,6706,-727,1927,
    		7361,9684,5519,2703,1723,5181,3545,4290,9421,4288,1656,1541,9632,1448,-490,
    		4747,5416,4139,-845,3834,3349,8594,7882,2279,7777,9369,9917,8167,6799,-612,
    		5604,5787,2615,7033,5986,-322,8631,1793,-612,3528,206,419,1413,8585,5658,
    		-981,1391,8088,7035,6259,-651,3118,9105,4531,2569,7576,7981,838,5715,1387,
    		8506,331,7844,9187,6812,1221,6916,2361,5869,1002,5944,344,310,-981,3541,960,
    		7667,8478,6610,9678,6511,3891,468,1347,115,3683,-982,5993,1875,69,4723,9949,
    		3097,6822,1809,4672,3064,4587,2228,-580,6866,8977,9224,-261,4311,5304,1169,
    		-511,7881,4252,3520,517,1714,6316,9399,8902,-376,4452,-414,1282,8399,1582,
    		4933,7642,6671,1530,6175,2321,7191,9479,7211,6559,4040,6830,7416,602,6970,
    		7978,4941,2225,7949,7398,6964,5912,1328,9818,8268,-999,4800,2510,6984,918,
    		2181,9142,6036,5447,4337,9459,9070,-171,5017,7625,2807,6172,7139,-966,5374,
    		4320,1266,6637,7043,-636,4346,7651,2102,3936,6906,4677,2505,1357,6219,2778,
    		5193,5994,4155,1350,9806,2404,9970,8132,1054,5197,1421,4908,1185,6817,7034,
    		239,8012,1740,7582,8098,8786,3703,2030,8422,3912,3300,8238,4293,898,7025,4871,
    		1781,3688,9833,2108,6812,4171,-539,7759,3088,9106,2839,9216,3165,451,2475,8717,
    		410,5226,6835,423,7611,426,6514,2729,-715,4223,9212,6197,1684,7505,5464,5505,
    		2320,2156,5838,3702,1641,6709,-930,5108,2480,3753,2035,142,530,3975,4683,885,
    		482,7599,2955,2265,-883,6708,8365,6133,1966,1460,7730,9852,3032,4275,1292,5735,
    		1491,6617,6958,9245,2946,-624,1845,7854,-392,3744,-978,9238,-805,3657,1313,5916
    	};
    	int k = 40;
    	int[] result = solution.maxSlidingWindow(test, k);
    	System.out.println(Arrays.toString(result));
    }
}