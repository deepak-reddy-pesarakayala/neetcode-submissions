class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        insertToFront(node);
        return node.value;
    }
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node oldNode = map.get(key);
            remove(oldNode);
        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        insertToFront(newNode);
        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void insertToFront(Node node) {
        Node first = head.next;
        head.next = node;
        node.prev = head;
        node.next = first;
        first.prev = node;
    }
}
