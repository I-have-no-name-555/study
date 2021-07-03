package study;

import textbook.UnderflowException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/17 19:54
 * @description 第四章 树
 */
public class Trees {
    private static class BinaryTreeNode<T extends Comparable<? super T>> {
        T val;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        public BinaryTreeNode(T val) {
            this.val = val;
        }
    }

    //二叉查找树的实现
    static class BinarySearchTree<T extends Comparable<? super T>> {
        private BinaryTreeNode<T> root;

        public void makeEmpty() {
            root = null;
        }

        public boolean isEmpty() {
            return root == null;
        }

        public boolean contains(T t) {
            return contains(t, root);
        }

        public T findMin() {
            if (isEmpty())
                throw new UnderflowException();
            return findMin(root).val;
        }

        public T findMax() {
            if (isEmpty())
                throw new UnderflowException();
            return findMax(root).val;
        }

        public void insert(T t) {
            root = insert(t, root);
        }

        public void remove(T t) {
            root = remove(t, root);
        }

        public void printTree() {

        }

        private boolean contains(T t, BinaryTreeNode<T> root) {
            if (root == null)
                return false;
            int compare = t.compareTo(root.val);
            if (compare == 0)
                return true;
            else if (compare > 0)
                return contains(t, root.right);
            else return contains(t, root.left);
        }

        private BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
            if (root == null)
                return null;
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }

        private BinaryTreeNode<T> findMax(BinaryTreeNode<T> root) {
            if (root != null)
                while (root.right != null)
                    root = root.right;
            return root;
        }

        private BinaryTreeNode<T> insert(T t, BinaryTreeNode<T> root) {
            if (root == null)
                return new BinaryTreeNode<>(t);
            int compare = t.compareTo(root.val);
            if (compare < 0)
                root.left = insert(t, root.left);
            else if (compare > 0)
                root.right = insert(t, root.right);
            return root;
        }

        private BinaryTreeNode<T> remove(T t, BinaryTreeNode<T> root) {
            if (root == null)
                return null;
            int compare = t.compareTo(root.val);
            if (compare < 0)
                root.left = remove(t, root.left);
            else if (compare > 0)
                root.right = remove(t, root.right);
            else if (root.left != null && root.right != null) {
                root.val = findMin(root.right).val;
                root.right = remove(root.val, root.right);
            } else root = root.left != null ? root.left : root.right;
            return root;
        }

        private void printTree(BinaryTreeNode<T> root) {

        }
    }

    //AVL树的实现
    static class AvlTree<T extends Comparable<? super T>> {
        T element;
        AvlTree<T> left;
        AvlTree<T> right;
        int height;

        public AvlTree(T element, AvlTree<T> left, AvlTree<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public AvlTree(T element) {
            this.element = element;
        }

        private int height(AvlTree<T> root) {
            return root == null ? -1 : root.height;
        }

        private static final int ALLOWED_IMBALANCE = 1;

        private AvlTree<T> findMin(AvlTree<T> root) {
            if (root == null)
                return null;
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }

        private AvlTree<T> findMax(AvlTree<T> root) {
            if (root != null)
                while (root.right != null)
                    root = root.right;
            return root;
        }

        public AvlTree<T> insert(T t, AvlTree<T> root) {
            if (root == null)
                return new AvlTree<>(t);
            int compare = t.compareTo(root.element);
            if (compare < 0)
                root.left = insert(t, root.left);
            else if (compare > 0)
                root.right = insert(t, root.right);
            return balance(root);
        }

        private AvlTree<T> balance(AvlTree<T> root) {
            if (root == null)
                return null;
            if (height(root.left) - height(root.right) > ALLOWED_IMBALANCE)
                if (height(root.left.left) >= height(root.left.right))
                    root = rotateWithLeftChild(root);
                else root = doubleWithLeftChild(root);
            else if (height(root.right) - height(root.left) > ALLOWED_IMBALANCE)
                if (height(root.right.right) >= height(root.right.left))
                    root = rotateWithRightChild(root);
                else root = doubleWithRightChild(root);

            root.height = newHeight(root);
            return root;
        }

        private int newHeight(AvlTree<T> tree) {
            return Math.max(height(tree.left), height(tree.right)) + 1;
        }

        private AvlTree<T> rotateWithLeftChild(AvlTree<T> root) {
            AvlTree<T> newRoot = root.left;
            root.left = newRoot.right;
            newRoot.right = root;
            newRoot.height = newHeight(newRoot);
            root.height = newHeight(root);
            return newRoot;
        }

        private AvlTree<T> rotateWithRightChild(AvlTree<T> root) {
            AvlTree<T> newRoot = root.right;
            root.right = newRoot.left;
            newRoot.left = root;
            root.height = newHeight(root);
            newRoot.height = newHeight(newRoot);
            return newRoot;
        }

        private AvlTree<T> doubleWithLeftChild(AvlTree<T> root) {
            root.left = rotateWithRightChild(root.left);
            return rotateWithLeftChild(root);
        }

        private AvlTree<T> doubleWithRightChild(AvlTree<T> root) {
            root.left = rotateWithLeftChild(root.left);
            return rotateWithRightChild(root);
        }

        public AvlTree<T> remove(T t, AvlTree<T> root){
            if (root == null)
                return null;
            int compare = t.compareTo(root.element);
            if (compare < 0)
                root.left = remove(t,root.left);
            else if (compare > 0)
                root.right = remove(t,root.right);
            else if (root.left != null && root.right != null){
                root.element = findMin(root.right).element;
                root.right = remove(root.element,root.right);
            }else root = root.left != null ? root.left : root.right;
            return balance(root);
        }
    }

    //
}
