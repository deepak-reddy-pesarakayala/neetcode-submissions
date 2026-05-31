class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>(Arrays.asList(dictionary));
        int n = s.length();

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1 + dp[i + 1];

            for (int j = i + 1; j <= n; j++) {
                String word = s.substring(i, j);

                if (set.contains(word)) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }

        return dp[0];
    }
}