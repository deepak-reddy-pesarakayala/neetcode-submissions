class Solution {
    public Node construct(int[][] grid) {
        int n = grid.length;
        return build(grid, 0, 0, n);
    }
    private Node build(int[][] grid, int row, int col, int size) {
        if (isSame(grid, row, col, size)) {
            return new Node(grid[row][col] == 1, true);
        }
        int half = size / 2;
        Node topLeft = build(grid, row, col, half);
        Node topRight = build(grid, row, col + half, half);
        Node bottomLeft = build(grid, row + half, col, half);
        Node bottomRight = build(grid, row + half, col + half, half);
        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }
    private boolean isSame(int[][] grid, int row, int col, int size) {
        int value = grid[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (grid[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}