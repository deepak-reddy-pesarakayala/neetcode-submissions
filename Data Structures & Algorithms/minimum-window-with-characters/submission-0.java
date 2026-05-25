class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> target = new HashMap<>();
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> window = new HashMap<>();
        int have = 0;
        int need = target.size();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (target.containsKey(c) &&
                window.get(c).intValue() == target.get(c).intValue()) {
                have++;
            }
            while (have == need) {
                if ((right - left + 1) < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (target.containsKey(leftChar) &&
                    window.get(leftChar) < target.get(leftChar)) {
                    have--;
                }
                left++;
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + minLen);
    }
}