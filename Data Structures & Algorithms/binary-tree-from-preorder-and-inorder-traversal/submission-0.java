class Solution {
    int index = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return createTree(preorder, 0, inorder.length - 1);
    }
    private TreeNode createTree(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int rootValue = preorder[index];
        index++;
        TreeNode root = new TreeNode(rootValue);
        int mid = map.get(rootValue);
        root.left = createTree(preorder, left, mid - 1);
        root.right = createTree(preorder, mid + 1, right);
        return root;
    }
}