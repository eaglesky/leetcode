public class CountingBits {

	//O(n) time and O(1) extra space
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int cur = 1; cur <= num; ++cur) {
            result[cur] = result[cur >> 1] + (cur & 1);
        }
        return result;
    }
}