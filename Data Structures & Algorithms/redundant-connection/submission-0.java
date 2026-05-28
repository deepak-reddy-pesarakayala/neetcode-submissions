class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (!union(parent, a, b)) {
                return edge;
            }
        }
        return new int[0];
    }
    private int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }
    private boolean union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA == rootB) {
            return false;
        }
        parent[rootB] = rootA;
        return true;
    }
}