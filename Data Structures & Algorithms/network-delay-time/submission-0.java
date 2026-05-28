class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            int source = time[0];
            int target = time[1];
            int weight = time[2];
            graph.get(source).add(new int[]{target, weight});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] - b[1]
        );
        pq.add(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int time = current[1];
            if (time > dist[node]) {
                continue;
            }
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int newTime = time + neighbor[1];
                if (newTime < dist[nextNode]) {
                    dist[nextNode] = newTime;
                    pq.add(new int[]{nextNode, newTime});
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            answer = Math.max(answer, dist[i]);
        }
        return answer;
    }
}