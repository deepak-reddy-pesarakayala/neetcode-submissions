class CountSquares {
    Map<String, Integer> map;
    public CountSquares() {
        map = new HashMap<>();
    }
    public void add(int[] point) {
        String key = point[0] + "," + point[1];
        map.put(key, map.getOrDefault(key, 0) + 1);
    }
    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        int ans = 0;
        for (String key : map.keySet()) {
            String[] parts = key.split(",");
            int px = Integer.parseInt(parts[0]);
            int py = Integer.parseInt(parts[1]);
            if (Math.abs(px - x) != Math.abs(py - y) || px == x || py == y) {
                continue;
            }
            String p1 = x + "," + py;
            String p2 = px + "," + y;
            ans += map.get(key) *
                   map.getOrDefault(p1, 0) *
                   map.getOrDefault(p2, 0);
        }
        return ans;
    }
}