package practice;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2020/12/8 18:03
 * @description 第三章 表、栈和队列
 * @update
 */
public class ListsStacksAndQueues {
    /*
    3.4 给定两个已排序的表L1、L2，只使用基本的表操作给出其交集
    3.5 给出其并集
     */
    public <T extends Comparable> List<T> test4(List<T> l1, List<T> l2) {
        List<T> l = new ArrayList<>(l1.size());
        Iterator<T> iterator1 = l1.iterator();
        Iterator<T> iterator2 = l2.iterator();
        T item1 = iterator1.next();
        T item2 = iterator2.next();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (item1.compareTo(item2) == 0) {
                l.add(item1);
                item1 = iterator1.next();
                item2 = iterator2.next();
            } else if (item1.compareTo(item2) < 0) {
                item1 = iterator1.next();
            } else {
                item2 = iterator2.next();
            }
        }
        if (item1.compareTo(item2) == 0)
            l.add(item1);
        return l;
    }

    public <T extends Comparable> List<T> test5(List<T> l1, List<T> l2) {
        List<T> l = new ArrayList<>(l1.size());
        Iterator<T> iterator1 = l1.iterator();
        Iterator<T> iterator2 = l2.iterator();
        T item1 = iterator1.next();
        T item2 = iterator2.next();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (item1.compareTo(item2) < 0) {
                l.add(item1);
                item1 = iterator1.next();
            } else if (item1.compareTo(item2) > 0) {
                l.add(item2);
                item2 = iterator2.next();
            } else {
                l.add(item1);
                item1 = iterator1.next();
                item2 = iterator2.next();
            }
        }
        if (!iterator1.hasNext())
            l.add(item1);
        if (!iterator2.hasNext())
            l.add(item2);
        while (iterator1.hasNext())
            l.add(iterator1.next());
        while (iterator2.hasNext())
            l.add(iterator2.next());
        return l;
    }

    /*
    3.6 Josephus问题：n个人编号从1到n依次序围成一圈，从一号开始传递一个热土豆，经过m次后持热土豆者出局，
    土豆交给下一个人继续游戏直至只剩一人,返回胜者编号
     */
    public int test6(int m, int n) {
        MyQueue<Integer> queue = new MyQueue<>(n);
        for (int i = 0; i < n; i++) {
            queue.enqueue(i + 1);
        }
        int answer = 0;
        while (!queue.isEmpty()) {
            for (int i = 0; i < m; i++) {
                queue.enqueue(queue.dequeue());
            }
            answer = queue.dequeue();
        }
        return answer;
    }


    /*
    3.9 提供对MyArrayList的addAll实现，声明为public void addAll（Iterator iter）;
     */
    /*
    3.13 提供ListIterator对MyArrayList的支持
     */
    /*
    3.22 编写一个程序计算后缀表达式的值
     */
    //（为了方便不考虑优先级）
    public void test22() {
        Scanner in = new Scanner(System.in);
        Stack<Double> numStack = new Stack<>();
        String str;
        double num1 = in.nextDouble();
        numStack.push(num1);
        double num2;
        while (in.hasNext()) {
            str = in.next();
            if (checkOperator(str)) {
                num2 = numStack.pop();
                num1 = numStack.pop();
                switch (str) {
                    case "+":
                        num1 += num2;
                        break;
                    case "-":
                        num1 -= num2;
                        break;
                    case "*":
                        num1 *= num2;
                        break;
                    case "/":
                        num1 /= num2;
                        break;
                    case "%":
                        num1 %= num2;
                        break;
                }
                numStack.push(num1);
            } else
                numStack.push(Double.parseDouble(str));
        }
    }

    private boolean checkOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%");
    }

    //3.23太长了不想做

    /*
    3.24 用一个数组实现两个栈，除非数组中每个元素都被使用，否则不声明溢出
     */
    static class BidirectionalStack<T>{
        private Object[] items;
        private int top1;
        private int top2;
        private static final int DEFAULT_CAPACITY = 50;

        public BidirectionalStack() {
            init(DEFAULT_CAPACITY);
        }

        public BidirectionalStack(int capacity) {
            init(capacity);
        }
        private void init(int capacity){
            top1 = -1;
            top2 = capacity;
            items = new Object[capacity];
        }
        public boolean isFrontEmpty(){
            return top1 == -1;
        }
        public boolean isBackEmpty(){
            return top2 == items.length;
        }
        public T pushFront(T t){
            if (top1++ == top2)
                throw new StackOverflowError();
            items[top1] = t;
            return t;
        }
        public T pushBack(T t){
            if (top2-- == top1)
                throw new StackOverflowError();
            items[top2] = t;
            return t;
        }
        public T popFront(){
            return (T) items[top1--];
        }
        public T popBack(){
            return (T)items[top2++];
        }
        public T topFront(){
            return (T) items[top1];
        }
        public T topBack(){
            return (T)items[top2];
        }
    }

    /*
    3.25 提出一种数据结构支持栈push和pop操作，且具有findMin操作，所有操作均以O（1）最坏情形时间运行

    答：在表示栈的类中额外维护一个最小值栈，新入主栈的元素大于当前最小值时，不作任何附加操作；
    当新入主栈的元素小于当前最小值时，拷贝一份压入辅助栈。
    同理弹出时如弹出的是当前最小值，则辅助栈同时弹出；否则仅主栈弹出。
    当进行findMin操作时，将辅助栈栈顶元素取得且不弹出即可。
     */

    /*
    3.28 双向队列
     */
    static class BidirectionalQueue<T>{
        private Object[] items;
        private int front1;
        private int back1;
        private int front2;
        private int back2;
        private static final int DEFAULT_CAPACITY = 50;

        public BidirectionalQueue() {
            init(DEFAULT_CAPACITY);
        }

        public BidirectionalQueue(int capacity) {
            init(capacity);
        }
        private void init(int capacity){
            front1 = 0;
            back1 = -1;
            front2 = capacity - 1;
            back2 = capacity;
            items = new Object[capacity];
        }
        public T push(T t){
            if (back1++ == back2)
                throw new ArrayIndexOutOfBoundsException();
            items[back1] = t;
            return t;
        }
        public T pop(){
            if (front1 > back1)
                throw new ArrayIndexOutOfBoundsException();
            return (T)items[front1++];
        }
        public T inject(T t){
            if (back1 == back2--)
                throw new ArrayIndexOutOfBoundsException();
            items[back2] = t;
            return t;
        }
        public T eject(){
            if (front2 < back2)
                throw new ArrayIndexOutOfBoundsException();
            return (T)items[front2--];
        }
    }

    /*
    3.30 a.用数组实现自调整表
         b.用链表实现自调整表
     */
    static class ArraySelfAdjustingTable<T>{
        private Object[] items;
        private static final int DEFAULT_CAPACITY = 10;
        private int size;
        public ArraySelfAdjustingTable() {
            items = new Object[DEFAULT_CAPACITY];
        }
        public ArraySelfAdjustingTable(int capacity) {
            items = new Object[capacity];
        }

        public T get(int i){
            return (T) items[i];
        }
        public void set(int i , T t){
            if (i < size)
                items[i] = t;
            else throw new ArrayIndexOutOfBoundsException();
        }
        public void add(T t){
            items[size++] = t;
        }
        public int size(){
            return size;
        }
        public void find(int i){
            Object obj = items[i];
            while (i > 0)
                items[i] = items[i--];
            items[i] = obj;
        }
    }
    static class LinkedSelfAdjustingTable<T>{
        private class Node<T>{
            T value;
            Node<T> next;
            Node<T> last;
        }
        private Node<T> head;
        private Node<T> tail;

        public LinkedSelfAdjustingTable() {
            init();
        }
        private void init(){
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.last = head;
        }

        private int size;
        public  int size(){
            return size;
        }

        public T get(int i){
            return getNode(i).value;
        }
        private Node<T> getNode(int i){
            if (i >= size)
                throw new ArrayIndexOutOfBoundsException();
            Node<T> node = head;
            while (i >= 0){
                node = node.next;
                i--;
            }
            return node;
        }
        public void set(int i , T t){
            getNode(i).value = t;
        }

        public void find(int i){
            Node<T> node = getNode(i);
            node.last.next = node.next;
            node.next.last = node.last;
            head.next = node;
            node.last = head;
        }
    }
    
    /*
    3.31 使用单链表高效实现栈，不使用头结点和尾结点
     */
    static class LinkedStack<T>{
        Node<T> head;
        public void push(T t){
            head = new Node<>(t,head);
        }
        public T pop(){
            Node<T> node = head;
            head = head.next;
            return node.t;
        }
        static class Node<T>{
            T t;
            Node<T> next;

            public Node(T t, Node<T> next) {
                this.t = t;
                this.next = next;
            }
        }
    }
    /*
    3.32 使用单链表高效实现队列，不使用头结点和尾结点
     */

    static class LinkedQueue<T>{
        Node<T> head;
        Node<T> tail;

        public void enQueue(T t){
            if (tail == null)
                head = tail = new Node<>(t,null);
            else
                tail = tail.next = new Node<>(t,null);
        }
        public T deQueue(){
            Node<T> node = head;
            head = head.next;
            return node.t;
        }
        static class Node<T>{
            T t;
            Node<T> next;
            public Node(T t, Node<T> next) {
                this.t = t;
                this.next = next;
            }
        }
    }
    /*
    3.33 使用循环数组高效实现队列类
     */

    static class MyQueue<T> {
        private Object[] items;
        private int capacity;
        private int front;
        private int back;
        private int currentSize;
        private static final int DEFAULT_INIT_CAPACITY = 50;

        public MyQueue() {
            this(DEFAULT_INIT_CAPACITY);
        }

        public MyQueue(int initCapacity) {
            capacity = initCapacity;
            items = new Object[capacity];
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public void enqueue(T t) {
            if (currentSize == capacity)
                throw new ArrayIndexOutOfBoundsException();
            items[back++ % capacity] = t;
            currentSize++;
        }

        public T dequeue() {
            if (currentSize == 0)
                throw new ArrayIndexOutOfBoundsException();
            currentSize--;
            return (T) items[front++ % capacity];
        }

        public void clear() {
            currentSize = 0;
            front = 0;
            back = 0;
        }
    }
    /*
    3.34 如果从某个节点p开始，接着跟有足够数目的next链将把我们带回到节点p，那么这个链表包含一个循环。
    p不必是链表的头结点。
    a.设计一个O(N)的算法确定一条链表中是否有循环，空间复杂度可以是O(N)
    b.将空间复杂度优化至O(1)
     */
    //leetcode 141 环形链表

}
