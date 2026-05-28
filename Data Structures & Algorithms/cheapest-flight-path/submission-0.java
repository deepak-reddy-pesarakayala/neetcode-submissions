class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = Integer.MAX_VALUE / 2;
        int[] prices = new int[n];
        Arrays.fill(prices, INF);
        prices[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] temp = prices.clone();
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                if (prices[from] == INF) {
                    continue;
                }
                temp[to] = Math.min(temp[to], prices[from] + price);
            }
            prices = temp;
        }
        return prices[dst] == INF ? -1 : prices[dst];
    }
}