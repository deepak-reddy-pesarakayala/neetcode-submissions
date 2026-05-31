class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;

        int[][] arr = new int[m][4];

        for (int i = 0; i < m; i++) {
            arr[i][0] = edges[i][0];
            arr[i][1] = edges[i][1];
            arr[i][2] = edges[i][2];
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> a[2] - b[2]);

        int mstWeight = kruskal(n, arr, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int edgeIndex = arr[i][3];

            int without = kruskal(n, arr, i, -1);

            if (without > mstWeight) {
                critical.add(edgeIndex);
            } else {
                int with = kruskal(n, arr, -1, i);

                if (with == mstWeight) {
                    pseudo.add(edgeIndex);
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudo);

        return result;
    }

    private int kruskal(int n, int[][] edges, int skip, int pick) {
        DSU dsu = new DSU(n);
        int weight = 0;
        int count = 0;

        if (pick != -1) {
            dsu.union(edges[pick][0], edges[pick][1]);
            weight += edges[pick][2];
            count++;
        }

        for (int i = 0; i < edges.length; i++) {
            if (i == skip) continue;

            if (dsu.union(edges[i][0], edges[i][1])) {
                weight += edges[i][2];
                count++;
            }
        }

        return count == n - 1 ? weight : Integer.MAX_VALUE;
    }
}

class DSU {
    int[] parent;
    int[] rank;

    DSU(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return false;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }

        return true;
    }
}