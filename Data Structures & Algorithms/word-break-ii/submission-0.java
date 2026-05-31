class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();

        return dfs(0, s, set, memo);
    }

    private List<String> dfs(int index, String s,
                             Set<String> set,
                             Map<Integer, List<String>> memo) {

        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        List<String> result = new ArrayList<>();

        if (index == s.length()) {
            result.add("");
            return result;
        }

        for (int end = index + 1; end <= s.length(); end++) {
            String word = s.substring(index, end);

            if (set.contains(word)) {
                List<String> nextWords = dfs(end, s, set, memo);

                for (String next : nextWords) {
                    if (next.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + next);
                    }
                }
            }
        }

        memo.put(index, result);
        return result;
    }
}