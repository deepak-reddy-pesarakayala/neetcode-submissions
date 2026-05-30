class LFUCache {
    private int capacity;
    private int minFreq;
    private Map<Integer, Integer> keyToValue;
    private Map<Integer, Integer> keyToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToKeys;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
    }
    public int get(int key) {
        if (!keyToValue.containsKey(key)) {
            return -1;
        }
        updateFrequency(key);
        return keyToValue.get(key);
    }
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (keyToValue.containsKey(key)) {
            keyToValue.put(key, value);
            updateFrequency(key);
            return;
        }
        if (keyToValue.size() == capacity) {
            LinkedHashSet<Integer> keys = freqToKeys.get(minFreq);
            int removeKey = keys.iterator().next();
            keys.remove(removeKey);
            if (keys.isEmpty()) {
                freqToKeys.remove(minFreq);
            }
            keyToValue.remove(removeKey);
            keyToFreq.remove(removeKey);
        }
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        minFreq = 1;
    }
    private void updateFrequency(int key) {
        int freq = keyToFreq.get(key);
        LinkedHashSet<Integer> keys = freqToKeys.get(freq);
        keys.remove(key);
        if (keys.isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }
        keyToFreq.put(key, freq + 1);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
    }
}