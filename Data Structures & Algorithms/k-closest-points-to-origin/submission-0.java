class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> distance(b) - distance(a)
        );
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        int[][] answer = new int[k][2];
        for (int i = 0; i < k; i++) {
            answer[i] = maxHeap.poll();
        }
        return answer;
    }
    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}