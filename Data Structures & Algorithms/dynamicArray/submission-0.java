class DynamicArray {
    private int[] arr;
    private int size;
    private int capacity;
    public DynamicArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
    }
    public int get(int i) {
        return arr[i];
    }
    public void set(int i, int n) {
        arr[i] = n;
    }
    public void pushback(int n) {
        if (size == capacity) {
            resize();
        }
        arr[size] = n;
        size++;
    }
    public int popback() {
        int value = arr[size - 1];
        size--;
        return value;
    }
    private void resize() {
        capacity = capacity * 2;
        int[] newArr = new int[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }

        arr = newArr;
    }

    public int getSize() {
        return size;
    }
    public int getCapacity() {
        return capacity;
    }
}