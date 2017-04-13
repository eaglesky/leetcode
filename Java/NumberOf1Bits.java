public class NumberOf1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < Integer.SIZE; ++i) {
            count += (n & 1);
            n = n >> 1;
        }
        return count;
    }
}