class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;

        if (n == 1) return true;

        for (int num : nums) {
            if (num == 1) return false;
        }

        DSU dsu = new DSU(n);
        Map<Integer, Integer> factorIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            for (int factor = 2; factor * factor <= num; factor++) {
                if (num % factor == 0) {
                    if (factorIndex.containsKey(factor)) {
                        dsu.union(i, factorIndex.get(factor));
                    } else {
                        factorIndex.put(factor, i);
                    }

                    while (num % factor == 0) {
                        num /= factor;
                    }
                }
            }

            if (num > 1) {
                if (factorIndex.containsKey(num)) {
                    dsu.union(i, factorIndex.get(num));
                } else {
                    factorIndex.put(num, i);
                }
            }
        }

        int parent = dsu.find(0);

        for (int i = 1; i < n; i++) {
            if (dsu.find(i) != parent) {
                return false;
            }
        }

        return true;
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

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }
}