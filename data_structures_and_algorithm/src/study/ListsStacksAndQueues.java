package study;

import org.junit.Test;
import org.omg.CORBA.Object;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author :Xuan
 * @date :Create in 2020/12/8 18:05
 * @description 第三章 表、栈和队列
 * @update
 */
public class ListsStacksAndQueues {
    @Test
    public void testMyArrayList(){
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.add(1);
    }
    /*
    根据测试可以知道，下述的基于课本的ArrayList实现在jdk8下是错误的
    但书中源码可以正确运行，暂时观察不出和课本的区别（明天调查
    原因与java数组的协变有关
    查询jdk8.0源码得知，泛型相关的数据结构（我所看见的）都使用object数组实现
    在返回一个元素时将其强转为对应泛型
     */
    /*
    基于课本而非JDK的ArrayList实现
     */
    class MyArrayList<T> implements Iterable<T> {

        private static final int DEFAULT_CAPACITY = 10;
        private int size;

        private T[] items;

          public MyArrayList(){
              doClear();
          }

          public void clear(){
              doClear();
          }

          private void doClear(){
              size = 0;
              ensureCapacity(DEFAULT_CAPACITY);
          }

          public int size(){
              return size;
          }

          public boolean isEmpty(){
              return size == 0;
          }

          public void trimToSize(){
              ensureCapacity(size());
          }

          private void checkIndex(int index){
              if(index < 0 || index >= size())
                  throw new ArrayIndexOutOfBoundsException();
          }

          public T get(int index){
              checkIndex(index);
              return items[index];
          }

          public T set(int index,T newValue){
              checkIndex(index);
              T old = items[index];
              items[index] = newValue;
              return old;
          }

          public void ensureCapacity(int newCapacity){
              if (newCapacity < size)
                  return;
              T[] old = items;
              items = (T[]) new Object[newCapacity];
              if(old != null)
                  System.arraycopy(old, 0, items, 0, old.length);
          }

          public boolean add(T x){
              add(size(),x);
              return true;
          }

          public void add(int index , T x){
              if(items.length==size())
                  ensureCapacity(size() * 2 + 1);
              if (size - index >= 0)
                  System.arraycopy(items, index, items, index + 1, size - index);
              items[index] = x;
              size++;
          }

          public T remove(int index){
              checkIndex(index);
              T removeItem = items[index];
              if (size() - 1 - index >= 0)
                  System.arraycopy(items, index + 1, items, index, size() - 1 - index);
              size--;
              return removeItem;
          }

          public Iterator<T> iterator(){
              return new ArrayListIterator();
          }
        private class ArrayListIterator implements Iterator<T>{
              int current = 0;

              @Override
              public boolean hasNext() {
                  return current < size();
              }

              @Override
              public T next() {
                  if(!hasNext())
                      throw new NoSuchElementException();
                  return items[current++];
              }
              @Override
              public void remove() {
                  MyArrayList.this.remove(--current);
              }
          }

          public void addAll(Iterator<T> iterator){
              while (iterator.hasNext())
                  add(iterator.next());
          }
          public ListIterator<T> listIterator(){
              return new MyListIterator();
          }
          private class MyListIterator implements ListIterator<T>{
              private int current = 0;
              @Override
              public boolean hasNext() {
                  return current < size;
              }

              @Override
              public T next() {
                  if(!hasNext())
                      throw new NoSuchElementException();
                  return  items[current++];
              }

              @Override
              public boolean hasPrevious() {
                  return current > 0;
              }

              @Override
              public T previous() {
                  if (!hasPrevious())
                      throw new NoSuchElementException();
                  return items[current--];
              }

              @Override
              public int nextIndex() {
                  return current + 1;
              }

              @Override
              public int previousIndex() {
                  return current - 1;
              }

              @Override
              public void remove() {
                  MyArrayList.this.remove(current--);
              }

              @Override
              public void set(T t) {
                  MyArrayList.this.set(current,t);
              }

              @Override
              public void add(T t) {
                  MyArrayList.this.add(t);
              }
          }
    }

}
