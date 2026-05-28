class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);
        for (int num : nums) {
            Map<Integer, Integer> next = new HashMap<>();
            for (int sum : dp.keySet()) {
                int count = dp.get(sum);
                next.put(sum + num,
                next.getOrDefault(sum + num, 0) + count);
                next.put(sum - num,
                next.getOrDefault(sum - num, 0) + count);
            }
            dp = next;
        }
        return dp.getOrDefault(target, 0);
    }
}