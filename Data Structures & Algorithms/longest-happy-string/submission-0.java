class Solution {
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        if (a > 0) pq.offer(new int[]{'a', a});
        if (b > 0) pq.offer(new int[]{'b', b});
        if (c > 0) pq.offer(new int[]{'c', c});
        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            int n = result.length();

            if (n >= 2 &&
                result.charAt(n - 1) == first[0] &&
                result.charAt(n - 2) == first[0]) {

                if (pq.isEmpty()) {
                    break;
                }

                int[] second = pq.poll();
                result.append((char) second[0]);
                second[1]--;

                if (second[1] > 0) {
                    pq.offer(second);
                }

                pq.offer(first);
            } else {
                result.append((char) first[0]);
                first[1]--;

                if (first[1] > 0) {
                    pq.offer(first);
                }
            }
        }

        return result.toString();
    }
}
        