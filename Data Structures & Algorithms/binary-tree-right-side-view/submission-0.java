class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        solve(root, 0, result);
        return result;
    }
    private void solve(TreeNode node, int level, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (level == result.size()) {
            result.add(node.val);
        }
        solve(node.right, level + 1, result);
        solve(node.left, level + 1, result);
    }
}