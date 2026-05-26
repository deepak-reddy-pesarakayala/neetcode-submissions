class Solution {
    public int goodNodes(TreeNode root) {
        return countGood(root, root.val);
    }
    private int countGood(TreeNode node, int maxValue) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.val >= maxValue) {
            count = 1;
        }
        maxValue = Math.max(maxValue, node.val);
        count += countGood(node.left, maxValue);
        count += countGood(node.right, maxValue);
        return count;
    }
}