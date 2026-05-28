class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int length = 1; length <= n; length++) {
            for (int left = 1; left <= n - length + 1; left++) {
                int right = left + length - 1;
                for (int last = left; last <= right; last++) {
                    int coins = arr[left - 1] * arr[last] * arr[right + 1]
                              + dp[left][last - 1]
                              + dp[last + 1][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }
        return dp[1][n];
    }
}