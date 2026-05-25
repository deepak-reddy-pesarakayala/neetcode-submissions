class LinkedList {
    class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }
    private Node head;
    public LinkedList() {
        head = null;
    }
    public int get(int i) {
        Node curr = head;
        int index = 0;
        while (curr != null) {
            if (index == i) {
                return curr.val;
            }
            curr = curr.next;
            index++;
        }
        return -1;
    }
    public void insertHead(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
    }
    public void insertTail(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }
    public boolean remove(int i) {
        if (head == null) {
            return false;
        }
        if (i == 0) {
            head = head.next;
            return true;
        }
        Node curr = head;
        int index = 0;
        while (curr != null && curr.next != null) {
            if (index + 1 == i) {
                curr.next = curr.next.next;
                return true;
            }
            curr = curr.next;
            index++;
        }
        return false;
    }
    public List<Integer> getValues() {
        List<Integer> result = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            result.add(curr.val);
            curr = curr.next;
        }
        return result;
    }
}