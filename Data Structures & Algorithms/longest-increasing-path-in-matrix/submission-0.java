class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int answer = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                answer = Math.max(answer, dfs(matrix, dp, r, c));
            }
        }
        return answer;
    }
    private int dfs(int[][] matrix, int[][] dp, int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxPath = 1;
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < rows &&
                nc >= 0 && nc < cols &&
                matrix[nr][nc] > matrix[r][c]) {
                maxPath = Math.max(maxPath, 1 + dfs(matrix, dp, nr, nc));
            }
        }
        dp[r][c] = maxPath;
        return maxPath;
    }
}