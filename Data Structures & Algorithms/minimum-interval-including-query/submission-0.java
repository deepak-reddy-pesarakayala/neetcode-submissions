class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = queries.length;
        int[][] q = new int[n][2];
        for (int i = 0; i < n; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        Arrays.sort(q, (a, b) -> a[0] - b[0]);
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );
        int i = 0;
        for (int[] query : q) {
            int value = query[0];
            int index = query[1];
            while (i < intervals.length && intervals[i][0] <= value) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                int length = right - left + 1;
                minHeap.offer(new int[]{length, right});
                i++;
            }
            while (!minHeap.isEmpty() && minHeap.peek()[1] < value) {
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) {
                answer[index] = minHeap.peek()[0];
            }
        }
        return answer;
    }
}