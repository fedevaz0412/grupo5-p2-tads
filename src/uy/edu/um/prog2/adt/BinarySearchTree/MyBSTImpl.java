package uy.edu.um.prog2.adt.BinarySearchTree;

public class MyBSTImpl <K extends Comparable<K>, T> implements MyBinarySearchTree<K,T>{
    private TreeNode<K,T> root;

    @Override
    public T find(K key) {
        //tirar error si key no existe
        return findRecursivo(key,root);
    }
    private T findRecursivo(K key, TreeNode<K,T> subTree){
        T dataToReturn = null;
        if(subTree != null){
            if(key.compareTo(subTree.getKey()) == 0){
                dataToReturn = subTree.getData();
            }else if (key.compareTo(subTree.getKey()) < 0){
                dataToReturn = findRecursivo(key, subTree.getLeftChild());
            }else if (key.compareTo(subTree.getKey()) > 0) {
                dataToReturn = findRecursivo(key, subTree.getRightChild());
            }
        }
        return dataToReturn;
    }

    @Override
    public void insert(K key, T data) {
        root = insertRecursivo(key, data, root);
    }
    private TreeNode<K,T> insertRecursivo(K key, T data, TreeNode<K,T> subTree){
        TreeNode<K,T> elementToAdd = new TreeNode<>(data, key);

        if(subTree == null){
            return elementToAdd;
        }else{
            if(key.compareTo(subTree.getKey()) > 0){ // key > root.getKey()
                TreeNode<K,T> newRight = insertRecursivo(key, data, subTree.getRightChild());
                subTree.setRightChild(newRight);
                return subTree;
            }else if(key.compareTo(subTree.getKey()) < 0 ){
                TreeNode<K,T> newLeft = insertRecursivo(key,data, subTree.getLeftChild());
                subTree.setLeftChild(newLeft);
                return subTree;
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        root = deleteRecursivo(key, root);
    }
    private TreeNode<K,T> deleteRecursivo(K key, TreeNode<K,T> subtree) {
        if (subtree == null) {
            return subtree;
        }
        if (key.compareTo(subtree.getKey()) < 0){//key< subtree.getKey()
            subtree.setLeftChild(deleteRecursivo(key, subtree.getLeftChild()));
        } else if (key.compareTo(subtree.getKey()) > 0){
            subtree.setRightChild(deleteRecursivo(key, subtree.getRightChild()));
        }else if (key.compareTo(subtree.getKey()) == 0){//este es el nodo a borrar
            if (subtree.getLeftChild() == null){
                return subtree.getRightChild();
            }else if (subtree.getRightChild() == null){
                return subtree.getLeftChild();
            }
            subtree.setKey(minValue(subtree.getRightChild()));
            subtree.setRightChild(deleteRecursivo(subtree.getKey(), subtree.getRightChild()));
        }
        return subtree;
    }
    private K minValue(TreeNode<K,T> subtree)
    {
        K minv = subtree.getKey();
        while (subtree.getLeftChild() != null)
        {
            minv = subtree.getLeftChild().getKey();
            subtree = subtree.getLeftChild();
        }
        return minv;
    }

    @Override
    public boolean exists(K key) {
        return existsRecursivo(key, root);
    }
    private boolean existsRecursivo(K key, TreeNode<K,T> subTree) {
        boolean exists = false;
        if (subTree != null) {
            if (key.compareTo(subTree.getKey()) == 0) {
                exists = true;
            } else if (key.compareTo(subTree.getKey()) < 0) {
                exists = existsRecursivo(key, subTree.getLeftChild());
            } else if (key.compareTo(subTree.getKey()) > 0) {
                exists = existsRecursivo(key, subTree.getRightChild());
            }
        }
        return exists;
    }

    @Override
    public int size() {
        return sizeRecursivo(root);
    }
    private int sizeRecursivo(TreeNode<K,T> subTree) {
        if (subTree == null) {
            return 0;
        }
        return 1 + sizeRecursivo(subTree.getLeftChild()) + sizeRecursivo(subTree.getRightChild());
    }

    public String toString() {
        return toStringRecursivo(root, 0);
    }

    private String toStringRecursivo(TreeNode<K,T> node, int currentLevel){//inOrder
        if(node == null){
            return " ";
        }
        String leftString = toStringRecursivo(node.getLeftChild(), currentLevel+1);
        String rightString = toStringRecursivo(node.getRightChild(), currentLevel+1);

        return leftString + ", "+ node.getKey().toString() +", "+ rightString;

    }
}
