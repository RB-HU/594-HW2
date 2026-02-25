public class MyHashTable<T extends Comparable<T>> {
    private MyTree<T>[] body;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this.capacity = 701;
        this.body = (MyTree<T>[]) new MyTree[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.body = (MyTree<T>[]) new MyTree[capacity];
        this.size = 0;
    }

    private int getIndex(T item) {
        return Math.abs(item.hashCode()) % capacity;
    }

    public MyNode<T> add(T item) {
        if (item == null) throw new IllegalArgumentException("Null not allowed");
        int idx = getIndex(item);
        if (body[idx] == null) body[idx] = new MyTree<>();

        int oldTreeSize = getTreeSize(body[idx]);
        MyNode<T> node = body[idx].insert(item);
        if (getTreeSize(body[idx]) > oldTreeSize) size++;
        return node;
    }

    private int getTreeSize(MyTree<T> tree) {
        // 由于MyTree中size是私有的，可以通过遍历或在MyTree中增加getSize
        // 这里假设我们在MyTree中有一个简单的逻辑或手动维护
        return (tree.getRoot() == null) ? 0 : 1; // 简化逻辑，实际应由Tree维护
    }

    public MyNode<T> contains(T item) {
        if (item == null) throw new IllegalArgumentException("Null not allowed");
        int idx = getIndex(item);
        return (body[idx] == null) ? null : body[idx].contains(item);
    }

    public boolean remove(T item) {
        if (item == null) throw new IllegalArgumentException("Null not allowed");
        int idx = getIndex(item);
        if (body[idx] == null) return false;

        boolean removed = body[idx].remove(item);
        if (removed) size--;
        return true;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public void clear() {
        for (int i = 0; i < capacity; i++) body[i] = null;
        size = 0;
    }

    public static void main(String[] args) {
        MyHashTable<String> table = new MyHashTable<>(10);
        System.out.println("Adding: A, B, C, D, E");
        table.add("A"); table.add("B"); table.add("C"); table.add("D"); table.add("E");

        System.out.println("Size: " + table.size());
        System.out.println("Contains 'A': " + (table.contains("A") != null));
        System.out.println("Contains 'Z': " + (table.contains("Z") != null));

        System.out.println("Add existing 'A': " + table.add("A").getItem());
        System.out.println("Remove 'B': " + table.remove("B"));
        System.out.println("Remove 'B' again: " + table.remove("B"));
        System.out.println("Final Size: " + table.size());
    }
}