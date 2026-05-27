class Solution {
    public void islandsAndTreasure(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    queue.add(new int[]{r, c});
                }
            }
        }
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (newRow < 0 || newCol < 0 ||
                    newRow >= rows || newCol >= cols ||
                    grid[newRow][newCol] != 2147483647) {
                    continue;
                }
                grid[newRow][newCol] = grid[row][col] + 1;
                queue.add(new int[]{newRow, newCol});
            }
        }
    }
}