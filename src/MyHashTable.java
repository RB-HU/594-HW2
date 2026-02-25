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
        if (item == null) throw new IllegalArgumentException("Null is not allowed");
        int idx = getIndex(item);
        if (body[idx] == null) body[idx] = new MyTree<>();

        int oldTreeSize = getTreeSize(body[idx]);
        MyNode<T> node = body[idx].insert(item);
        if (getTreeSize(body[idx]) > oldTreeSize) size++;
        return node;
    }

    private int getTreeSize(MyTree<T> tree) {
        return (tree.getRoot() == null) ? 0 : 1;
    }

    public MyNode<T> contains(T item) {
        if (item == null) throw new IllegalArgumentException("Null is not allowed");
        int idx = getIndex(item);
        return (body[idx] == null) ? null : body[idx].contains(item);
    }

    public boolean remove(T item) {
        if (item == null) throw new IllegalArgumentException("Null is not allowed");
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
        System.out.println(table.add("CIT591").getItem());
        System.out.println(table.add("CIT592").getItem());
        System.out.println(table.add("CIT593").getItem());
        System.out.println(table.add("CIT594").getItem());
        System.out.println(table.add("CIT595&596").getItem());
        System.out.println("Table Size: " + table.size());
        System.out.println("Contains 'CIT591': " + (table.contains("CIT591")  != null));
        System.out.println("Contains 'CIS520': " + (table.contains("CIS520") != null));
        System.out.println(table.add("CIT591").getItem());
        System.out.println(table.remove("CIT592"));
        //System.out.println(table.remove("CIT592"));
    }
}