package uy.edu.um.prog2.adt.BinarySearchTree;

public class TreeNode<K extends Comparable<K>, T> {
    private K key; //comparo siempre las keys ( key: cedula, data: Presona)
    private T data;
    private TreeNode<K, T> leftChild;
    private TreeNode<K, T> rightChild;

    public TreeNode(T data, K key) {
        this.data = data;
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    public String toString() {
        return key.toString();
    }
}
