class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        int maxFreq = 0;
        int maxCount = 0;
        for (int count : freq) {
            if (count > maxFreq) {
                maxFreq = count;
                maxCount = 1;
            } else if (count == maxFreq) {
                maxCount++;
            }
        }
        int partCount = maxFreq - 1;
        int partLength = n + 1;
        int minimumTime = partCount * partLength + maxCount;
        return Math.max(minimumTime, tasks.length);
    }
}
