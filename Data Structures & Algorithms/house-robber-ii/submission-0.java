class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(
            robHouse(nums, 0, n - 2),
            robHouse(nums, 1, n - 1)
        );
    }
    private int robHouse(int[] nums, int start, int end) {
        int first = 0;
        int second = 0;
        for (int i = start; i <= end; i++) {
            int current = Math.max(second, first + nums[i]);
            first = second;
            second = current;
        }
        return second;
    }
}