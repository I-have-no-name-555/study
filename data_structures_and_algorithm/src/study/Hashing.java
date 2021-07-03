package study;

import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/3/25 19:02
 * @description 第五章 散列
 */
public class Hashing {

    //实现HashMap
    static class MyHashMap<K, V> implements Map<K, V> {
        class Node<K, V> implements Entry<K, V> {
            final K k;
            V v;
            Node<K, V> next;
            final int hash;

            public Node(K k, V v, Node<K, V> next, int hash) {
                this.k = k;
                this.v = v;
                this.next = next;
                this.hash = hash;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node<?, ?> node = (Node<?, ?>) o;
                return hash == node.hash &&
                        Objects.equals(k, node.k) &&
                        Objects.equals(v, node.v) &&
                        Objects.equals(next, node.next);
            }

            @Override
            public int hashCode() {
                return k.hashCode() ^ v.hashCode();
            }

            @Override
            public K getKey() {
                return k;
            }

            @Override
            public V getValue() {
                return v;
            }

            @Override
            public V setValue(V value) {
                V oldValue = v;
                v = value;
                return oldValue;
            }
        }

        public static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private float loadFactor;

        public static final int DEFAULT_CAPACITY = 1 << 4;

        private int threshold;

        private int size;

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        private Node<K, V>[] table;
        private Set<Map.Entry<K, V>> entrySet;
        private Set<K> keySet;
        private Collection<V> values;

        public MyHashMap(float loadFactor, int capacity) {
            this.loadFactor = loadFactor;
            this.threshold = tableSizeFor(capacity);
        }

        public MyHashMap(int capacity) {
            new MyHashMap<>(DEFAULT_LOAD_FACTOR, capacity);
        }

        public MyHashMap() {
            new MyHashMap<>(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
        }

        public MyHashMap(Map<K, V> map) {
            putAll(map);
        }

        private static int hash(Object key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }

        private static int tableSizeFor(int cap) {
            int n = cap - 1;
            n |= n >>> 1;
            n |= n >>> 2;
            n |= n >>> 4;
            n |= n >>> 8;
            n |= n >>> 16;
            return (n < 0) ? 1 : n + 1;
        }

        @Override
        public boolean containsKey(Object key) {
            return getNode(hash(key), key) != null;
        }

        @Override
        public boolean containsValue(Object value) {
            return values().contains(value);
        }

        @Override
        public V get(Object key) {
            Node<K, V> node = getNode(hash(key), key);
            return node == null ? null : node.v;
        }

        private Node<K, V> getNode(int hash, Object key) {
            if (key == null) {
                Node<K, V> node = table[0];
                while (node != null) {
                    if (node.k == null)
                        return node;
                    node = node.next;
                }
                return null;
            }
            Node<K, V> node = table[(table.length - 1) & hash];
            if (node == null)
                return null;
            do {
                if (hash == node.hash && key.equals(node.k))
                    return node;
            } while ((node = node.next) != null);
            return null;
        }

        @Override
        public V put(K key, V value) {
            if (table == null || table.length == 0)
                resize();
            int hash = hash(key);
            int index = hash & (table.length - 1);
            Node<K, V> node = table[index];
            if (node == null) {
                table[index] = new Node<>(key, value, null, hash);
                size++;
            } else {
                while (node.next != null) {
                    if (hash == node.hash && node.k.equals(key))
                        return node.v = value;
                    node = node.next;
                }
            }
            if (++size > threshold)
                resize();
            return null;
        }

        final void resize() {
            Node<K, V>[] oldTable = table;
            int oldCap = oldTable == null ? 0 : oldTable.length;
            int oldThr = threshold;
            int newCap = 0;
            int newThr = 0;
            if (oldCap > 0) {
                if (oldCap >= DEFAULT_CAPACITY) {
                    newCap = oldCap << 1;
                    newThr = oldThr << 1;
                }
            } else if (oldThr > 0)
                newCap = oldThr;
            else {
                newCap = DEFAULT_CAPACITY;
                newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_CAPACITY);
            }
            if (newThr == 0) {
                newThr = (int) (newCap * loadFactor);
            }
            threshold = newThr;

            table = (Node<K, V>[]) new Node[newCap];

            if (oldTable != null) {
                for (Node<K, V> node : oldTable) {
                    while (node != null) {
                        Node<K, V> next = node.next;
                        Node<K, V> e = table[(newCap - 1) & node.hash];
                        if (e == null) {
                            table[(newCap - 1) & node.hash] = node;
                        } else {
                            while (e.next != null) {
                                e = e.next;
                            }
                            e.next = node;
                        }
                        node = next;
                    }
                }
            }

        }

        @Override
        public V remove(Object key) {
            Node<K, V> e;
            return (e = removeNode(hash(key), key, null, false)) == null ? null : e.v;
        }

        private Node<K, V> removeNode(int hash, Object key, Object v, boolean nullValueFlag) {
            int index = hash & (table.length - 1);
            Node<K, V> node = new Node<>(null, null, table[index], 0);
            if (node.next == null)
                return null;
            while (node.next != null) {
                if (node.next.hash == hash && ((key != null && key.equals(node.next.k))
                        || (key == null && node.next.k == null))) {
                    if (v != null && !v.equals(node.v) || (v == null && nullValueFlag && node.v != null))
                        return null;
                    Node<K, V> ret = node.next;
                    node.next = ret.next;
                    size--;
                    return ret;
                }
                node = node.next;
            }
            return null;
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            for (Entry<? extends K, ? extends V> entry : m.entrySet())
                put(entry.getKey(), entry.getValue());
        }

        @Override
        public void clear() {
            if (table == null)
                return;
            Arrays.fill(table, null);
            size = 0;
        }

        @Override
        public Set<K> keySet() {
            if (keySet == null) {
                keySet = new KeySet();
            }
            return keySet;
        }

        @Override
        public Collection<V> values() {
            if (values == null)
                values = new Values();
            return values;
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            if (entrySet == null)
                entrySet = new EntrySet();
            return entrySet;
        }

        @Override
        public V getOrDefault(Object key, V defaultValue) {
            Node<K, V> node = getNode(hash(key), key);
            return node == null ? defaultValue : node.v;
        }

        @Override
        public boolean remove(Object key, Object value) {
            return removeNode(hash(key), key, value, true) != null;
        }

        @Override
        public boolean replace(K key, V oldValue, V newValue) {
            Node<K, V> node = getNode(hash(key), key);
            if (node != null) {
                if ((oldValue != null && oldValue.equals(node.v))
                        || (oldValue == null && node.v == null))
                    node.v = newValue;
                return true;
            }
            return false;
        }

        @Override
        public V replace(K key, V value) {
            Node<K, V> node = getNode(hash(key), key);
            if (node != null) {
                V oldValue = node.v;
                node.v = value;
                return oldValue;
            }
            return null;
        }

        final class KeySet extends AbstractSet<K> {
            @Override
            public boolean contains(Object o) {
                return MyHashMap.this.containsKey(o);
            }

            @Override
            public boolean remove(Object o) {
                return MyHashMap.this.removeNode(hash(o), o, null, false) != null;
            }

            @Override
            public void clear() {
                MyHashMap.this.clear();
            }

            @Override
            public Iterator<K> iterator() {
                return new KeyIterator();
            }

            @Override
            public int size() {
                return size;
            }
        }

        final class Values extends AbstractCollection<V> {
            @Override
            public boolean contains(Object o) {
                return MyHashMap.this.containsValue(o);
            }

            @Override
            public void clear() {
                MyHashMap.this.clear();
            }

            @Override
            public Iterator<V> iterator() {
                return new ValueIterator();
            }

            @Override
            public int size() {
                return size;
            }
        }

        final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new EntryIterator();
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean contains(Object o) {
                return super.contains(o);
            }

            @Override
            public boolean remove(Object o) {
                return super.remove(o);
            }

            @Override
            public void clear() {
                super.clear();
            }
        }

        abstract class HashIterator {
            Node<K, V> next;
            Node<K, V> current;
            int index;

            public HashIterator() {
                Node<K, V>[] t = table;
                current = next = null;
                index = 0;
                if (t != null && size > 0) {
                    while (index < t.length && (next = t[index++]) == null) ;
                }
            }

            final Node<K, V> nextNode() {
                Node<K, V> e = next;
                if (e == null)
                    throw new NoSuchElementException();
                if ((next = (current = e).next) == null && table != null) {
                    do {
                    } while (index < table.length && (next = table[index++]) == null);
                }
                return e;
            }

            public void remove() {
                Node<K,V> p = current;
                if (p == null)
                    throw new IllegalStateException();
                current = null;
                MyHashMap.this.remove(p.k);
            }

            public boolean hasNext() {
                return next != null;
            }

        }

        final class KeyIterator extends HashIterator implements Iterator<K> {
            @Override
            public K next() {
                return nextNode().k;
            }
        }

        final class ValueIterator extends HashIterator implements Iterator<V> {
            @Override
            public V next() {
                return nextNode().v;
            }
        }

        final class EntryIterator extends HashIterator implements Iterator<Map.Entry<K, V>> {
            @Override
            public Entry<K, V> next() {
                return nextNode();
            }
        }
    }

    //实现HashSet
    static class MyHashSet<T> implements Set<T>{
        private MyHashMap<T,Object> map;
        private static Object VALUE = new Object();

        public MyHashSet() {
            map = new MyHashMap<>();
        }
        public MyHashSet(int capacity) {
            map = new MyHashMap<>(capacity);
        }

        public MyHashSet(int capacity,int loadFactor) {
            map = new MyHashMap<>(loadFactor,capacity);
        }

        public MyHashSet(Collection<? extends T> c){
            map = new MyHashMap<>(Math.max((int) (c.size()/MyHashMap.DEFAULT_LOAD_FACTOR) + 1, MyHashMap.DEFAULT_CAPACITY));
            addAll(c);
        }

        @Override
        public int size() {
            return map.size();
        }

        @Override
        public boolean isEmpty() {
            return map.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return map.containsKey(o);
        }

        @Override
        public Iterator<T> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public Object[] toArray() {
            Object[] array = new Object[map.size()];
            Iterator<T> iterator = iterator();
            for (int i = 0; i < array.length; i++) {
                array[i] = iterator.next();
            }
            return array;
        }

        @Override
        public <T1> T1[] toArray(T1[] a) {
            Iterator<T> iterator = iterator();
            for (int i = 0; i < a.length; i++) {
                if (iterator.hasNext())
                    a[i] = (T1) iterator.next();
                else break;
            }
            return a;
        }

        @Override
        public boolean add(T t) {
            return map.put(t,VALUE) == null;
        }

        @Override
        public boolean remove(Object o) {
            return map.remove(o) == VALUE;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            for (Object o : c) {
                if (!contains(o))
                    return false;
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            for (T o : c) {
                if (!add(o))
                    return false;
            }
            return true;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            for(Object o : c){
                map.remove(o);
            }
            return true;
        }

        @Override
        public void clear() {
            map.clear();
        }
    }
}
