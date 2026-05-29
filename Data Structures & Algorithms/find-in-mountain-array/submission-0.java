class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int peak = findPeak(mountainArr, n);
        int leftSearch = binarySearch(mountainArr, target, 0, peak, true);
        if (leftSearch != -1) {
            return leftSearch;
        }
        return binarySearch(mountainArr, target, peak + 1, n - 1, false);
    }
    private int findPeak(MountainArray mountainArr, int n) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    private int binarySearch(MountainArray mountainArr, int target,
                             int left, int right, boolean increasing) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = mountainArr.get(mid);
            if (value == target) {
                return mid;
            }
            if (increasing) {
                if (value < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (value < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}