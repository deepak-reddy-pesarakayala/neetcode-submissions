class Solution {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        findMaxPath(root);
        return maxSum;
    }
    private int findMaxPath(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSum = Math.max(0, findMaxPath(node.left));
        int rightSum = Math.max(0, findMaxPath(node.right));
        int currentPath = node.val + leftSum + rightSum;
        maxSum = Math.max(maxSum, currentPath);
        return node.val + Math.max(leftSum, rightSum);
    }
}