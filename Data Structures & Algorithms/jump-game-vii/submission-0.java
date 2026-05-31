class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
    int n = s.length();

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        boolean[] visited = new boolean[n];
        visited[0] = true;

        int farthest = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == n - 1) {
                return true;
            }

            int start = Math.max(current + minJump, farthest);
            int end = Math.min(current + maxJump, n - 1);

            for (int next = start; next <= end; next++) {
                if (s.charAt(next) == '0' && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }

            farthest = end + 1;
        }

        return false;
    }
}