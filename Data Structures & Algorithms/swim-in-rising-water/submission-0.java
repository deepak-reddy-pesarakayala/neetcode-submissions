class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[2] - b[2]
        );
        pq.add(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int r = cell[0];
            int c = cell[1];
            int time = cell[2];
            if (r == n - 1 && c == n - 1) {
                return time;
            }
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < n &&
                    nc >= 0 && nc < n &&
                    !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    int newTime = Math.max(time, grid[nr][nc]);
                    pq.add(new int[]{nr, nc, newTime});
                }
            }
        }
        return -1;
    }
}