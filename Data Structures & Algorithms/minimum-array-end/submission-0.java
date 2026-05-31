class Solution {
    public long minEnd(int n, int x) {
        long result = x;
        long num = n - 1;
        int bit = 0;

        while (num > 0) {
            if ((result & (1L << bit)) == 0) {
                if ((num & 1) == 1) {
                    result |= (1L << bit);
                }
                num >>= 1;
            }
            bit++;
        }

        return result;
    }
}