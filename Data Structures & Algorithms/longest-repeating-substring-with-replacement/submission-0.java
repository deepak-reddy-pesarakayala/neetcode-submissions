class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);
            int windowSize = right - left + 1;
            while (windowSize - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
             windowSize = right - left + 1;
            }
            maxLength = Math.max(maxLength, windowSize);
        }
        return maxLength;
    }
}