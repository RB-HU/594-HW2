
public class MyNode<T> {
    private T item;
    private MyNode<T> left = null;
    private MyNode<T> right = null;
    private MyNode<T> parent = null;

    public MyNode(T item) {
        this.item = item;
    }

    public T getItem() { return item; }

    public MyNode<T> getLeft() { return left; }
    public void setLeft(MyNode<T> left) { this.left = left; }

    public MyNode<T> getRight() { return right; }
    public void setRight(MyNode<T> right) { this.right = right; }

    public MyNode<T> getParent() { return parent; }
    public void setParent(MyNode<T> parent) { this.parent = parent; }

}
