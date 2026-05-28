class Solution {
    public String foreignDictionary(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] indegree = new int[26];
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                graph.putIfAbsent(ch, new HashSet<>());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree[c2 - 'a']++;
                    }
                    break;
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (char ch : graph.keySet()) {
            if (indegree[ch - 'a'] == 0) {
                queue.add(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            result.append(ch);
            for (char next : graph.get(ch)) {
                indegree[next - 'a']--;
                if (indegree[next - 'a'] == 0) {
                    queue.add(next);
                }
            }
        }
        if (result.length() != graph.size()) {
            return "";
        }
        return result.toString();
    }
}