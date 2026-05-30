class Solution {
    public int rob(TreeNode root) {
        int[] result = solve(root);
        return Math.max(result[0], result[1]);
    }
    private int[] solve(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = solve(node.left);
        int[] right = solve(node.right);
        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{rob, notRob};
    }
}