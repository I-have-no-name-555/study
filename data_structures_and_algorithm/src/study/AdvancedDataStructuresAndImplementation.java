package study;


import textbook.RedBlackTree;

/**
 * @author :Xuan
 * @date :Create in 2021/5/22 11:34
 * @description 第十二章 高级数据结构及其实现
 */
public class AdvancedDataStructuresAndImplementation {
    //红黑树
    static class RBTree<T extends Comparable<? super T>> extends Trees.BinarySearchTree<T> {
        private static class RBTreeNode<T> {
            T value;
            RBTreeNode<T> left;
            RBTreeNode<T> right;
            boolean color;

            public RBTreeNode(T value) {
                this(value, null, null);
            }


            public RBTreeNode(T value, RBTreeNode<T> left, RBTreeNode<T> right) {
                this.value = value;
                this.left = left;
                this.right = right;
            }

        }

        private RBTreeNode<T> header;
        private RBTreeNode<T> nullNode;

        private static final boolean BLACK = true;
        private static final boolean RED = false;

        public RBTree() {
            header = new RBTreeNode<>(null);
            nullNode = new RBTreeNode<>(null);
            nullNode.left = nullNode.right = nullNode;
            header.left = header.right = nullNode;
        }

        //辅助操作的四个变量  这就很不优雅
        private RBTreeNode<T> current, parent, grand, great;

        public void insert(T item) {
            current = parent = grand = header;
            nullNode.value = item;

            while (compare(item, current) != 0) {
                great = grand;
                grand = parent;
                parent = current;
                current = compare(item, current) < 0 ? current.left : current.right;

                if (current.left.color == RED && current.right.color == RED)
                    handleReorient(item);
            }

            if (current != nullNode)
                return;

            current = new RBTreeNode<>(item, nullNode, nullNode);
        }

        //当节点有两个红色子节点时，进行翻转和旋转
        private void handleReorient(T item) {
            current.color = RED;
            current.left.color = BLACK;
            current.right.color = BLACK;

            if (parent.color == RED) {
                grand.color = RED;
                if ((compare(item, grand) < 0) != (compare(item, parent) < 0))
                    parent = rotate(item, grand);
                current = rotate(item, great);
                current.color = BLACK;
            }

            header.right.color = BLACK;
        }

        private boolean isRed(RBTreeNode<T> node) {
            if (node == null)
                return false;
            return node.color == RED;
        }

        private int compare(T item, RBTreeNode<T> t) {
            if (t == header)
                return 1;
            return item.compareTo(t.value);
        }

        private RBTreeNode<T> rotate(T item, RBTreeNode<T> parent) {
            return compare(item, parent) < 0 ?
                    (parent.left = compare(item, parent.left) < 0 ?
                            rotateWithLeftChild(parent.left) :
                            rotateWithRightChild(parent.right)) :
                    (parent.right = compare(item, parent.right) < 0 ?
                            rotateWithLeftChild(parent.right) :
                            rotateWithRightChild(parent.right));
        }

        private RBTreeNode<T> rotateWithLeftChild(RBTreeNode<T> k2) {
            RBTreeNode<T> k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            return k1;
        }

        private RBTreeNode<T> rotateWithRightChild(RBTreeNode<T> k1) {
            RBTreeNode<T> k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            return k2;
        }

    }
}
