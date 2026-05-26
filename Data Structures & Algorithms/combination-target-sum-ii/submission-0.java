class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int start, int[] candidates, int target,
                           List<Integer> current,
                           List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > target) {
                break;
            }
            current.add(candidates[i]);
            backtrack(i + 1, candidates, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }
}