class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n][n + 1];

        return dfs(0, 1, piles, suffix, dp);
    }

    private int dfs(int index, int m, int[] piles, int[] suffix, int[][] dp) {
        int n = piles.length;

        if (index >= n) {
            return 0;
        }

        if (index + 2 * m >= n) {
            return suffix[index];
        }

        if (dp[index][m] != 0) {
            return dp[index][m];
        }

        int maxStones = 0;

        for (int x = 1; x <= 2 * m; x++) {
            int opponent = dfs(index + x, Math.max(m, x), piles, suffix, dp);
            maxStones = Math.max(maxStones, suffix[index] - opponent);
        }

        dp[index][m] = maxStones;
        return maxStones;
    }
}