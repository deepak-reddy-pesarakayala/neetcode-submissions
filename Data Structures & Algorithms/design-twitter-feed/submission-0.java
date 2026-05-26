class Twitter {
    private int time;
    private HashMap<Integer, HashSet<Integer>> followMap;
    private HashMap<Integer, List<int[]>> tweetMap;
    public Twitter() {
        time = 0;
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }
    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<>());
        tweetMap.get(userId).add(new int[]{time, tweetId});
        time++;
    }
    public List<Integer> getNewsFeed(int userId) {
        List<int[]> tweets = new ArrayList<>();
        if (tweetMap.containsKey(userId)) {
            tweets.addAll(tweetMap.get(userId));
        }
        if (followMap.containsKey(userId)) {
            for (int followeeId : followMap.get(userId)) {
                if (tweetMap.containsKey(followeeId)) {
                    tweets.addAll(tweetMap.get(followeeId));
                }
            }
        }
        tweets.sort((a, b) -> b[0] - a[0]);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tweets.size() && i < 10; i++) {
            result.add(tweets.get(i)[1]);
        }
        return result;
    }
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}