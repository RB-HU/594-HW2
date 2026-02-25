public class MyTree<T extends Comparable<T>> {
    private MyNode<T> root = null;
    private int size = 0;

    public MyTree() {}

    public MyNode<T> getRoot() { return root; }

    public MyNode<T> insert(T item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (root == null) {
            root = new MyNode<>(item);
            size++;
            return root;
        }
        return insertHelper(root, item);
    }

    private MyNode<T> insertHelper(MyNode<T> current, T item) {
        int cmp = item.compareTo(current.getItem());
        if (cmp == 0) return current; // 已存在

        if (cmp < 0) {
            if (current.getLeft() == null) {
                MyNode<T> newNode = new MyNode<>(item);
                current.setLeft(newNode);
                newNode.setParent(current);
                size++;
                return newNode;
            }
            return insertHelper(current.getLeft(), item);
        } else {
            if (current.getRight() == null) {
                MyNode<T> newNode = new MyNode<>(item);
                current.setRight(newNode);
                newNode.setParent(current);
                size++;
                return newNode;
            }
            return insertHelper(current.getRight(), item);
        }
    }

    public MyNode<T> contains(T item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        MyNode<T> curr = root;
        while (curr != null) {
            int cmp = item.compareTo(curr.getItem());
            if (cmp == 0) return curr;
            curr = (cmp < 0) ? curr.getLeft() : curr.getRight();
        }
        return null;
    }

    public boolean remove(T item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        MyNode<T> node = contains(item);
        if (node == null) return false;

        deleteNode(node);
        size--;
        return true;
    }

    private void deleteNode(MyNode<T> z) {
        if (z.getLeft() == null) {
            transplant(z, z.getRight());
        } else if (z.getRight() == null) {
            transplant(z, z.getLeft());
        } else {
            MyNode<T> y = minimum(z.getRight());
            if (y.getParent() != z) {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
        }
    }

    private void transplant(MyNode<T> u, MyNode<T> v) {
        if (u.getParent() == null) root = v;
        else if (u == u.getParent().getLeft()) u.getParent().setLeft(v);
        else u.getParent().setRight(v);
        if (v != null) v.setParent(u.getParent());
    }

    private MyNode<T> minimum(MyNode<T> node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        String res = sb.toString();
        return res.isEmpty() ? "" : res.substring(0, res.length() - 2);
    }

    private void inorder(MyNode<T> node, StringBuilder sb) {
        if (node == null) return;
        inorder(node.getLeft(), sb);
        sb.append(node.getItem()).append(", ");
        inorder(node.getRight(), sb);
    }
}
