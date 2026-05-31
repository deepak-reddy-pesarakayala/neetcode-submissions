class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();

        DSU dsu = new DSU(n);
        Map<String, Integer> emailToIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);

            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);

                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, i);
                } else {
                    dsu.union(i, emailToIndex.get(email));
                }
            }
        }

        Map<Integer, TreeSet<String>> map = new HashMap<>();

        for (String email : emailToIndex.keySet()) {
            int index = emailToIndex.get(email);
            int parent = dsu.find(index);

            map.putIfAbsent(parent, new TreeSet<>());
            map.get(parent).add(email);
        }

        List<List<String>> result = new ArrayList<>();

        for (int parent : map.keySet()) {
            List<String> merged = new ArrayList<>();

            merged.add(accounts.get(parent).get(0));
            merged.addAll(map.get(parent));

            result.add(merged);
        }

        return result;
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

        if (pa == pb) {
            return;
        }

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