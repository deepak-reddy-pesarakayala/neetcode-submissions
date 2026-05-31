class Solution {
    public int openLock(String[] deadends, String target) {
    Set<String> dead = new HashSet<>(Arrays.asList(deadends));

        if (dead.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer("0000");
        visited.add("0000");

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(target)) {
                    return steps;
                }

                for (String next : getNextStates(current)) {
                    if (!dead.contains(next) && !visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private List<String> getNextStates(String state) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            char[] arr = state.toCharArray();

            char ch = arr[i];

            arr[i] = ch == '9' ? '0' : (char)(ch + 1);
            list.add(new String(arr));

            arr[i] = ch == '0' ? '9' : (char)(ch - 1);
            list.add(new String(arr));
        }

        return list;
    }
}