class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }
        int rows = board.length;
        int cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }
        return result;
    }
    private void dfs(char[][] board, int r, int c,
                     TrieNode node, List<String> result) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return;
        }
        char ch = board[r][c];
        if (ch == '#') {
            return;
        }
        int index = ch - 'a';
        if (node.children[index] == null) {
            return;
        }
        node = node.children[index];
        if (node.word != null) {
            result.add(node.word);
            node.word = null; 
        }
        board[r][c] = '#';
        dfs(board, r + 1, c, node, result);
        dfs(board, r - 1, c, node, result);
        dfs(board, r, c + 1, node, result);
        dfs(board, r, c - 1, node, result);
        board[r][c] = ch;
    }
}