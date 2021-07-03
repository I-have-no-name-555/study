package practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author :Xuan
 * @date :Create in 2020/12/5 17:28
 * @description 第一章 引论 课后习题
 * @update
 */
public class Introduction {
    //1.2编写一个程序解决解字谜游戏,words为单词表,chars为方阵
    public void test2(char[][] chars , String[] words){
        //将单词表内容添加到一个集合中方便匹配
        HashSet<String> dictionary = new HashSet<>(words.length);
        Collections.addAll(dictionary, words);
        HashSet<String> answer = new HashSet<>();
        //用于存储单词
        StringBuilder stringBuilder = new StringBuilder(chars.length);

        for (int i = 0; i < chars.length; i++) {

            //检查行
            for (int j = 0; j < chars.length; j++) {
                stringBuilder.append(chars[i][j]);
                checkWord(stringBuilder,answer,dictionary);
            }
            stringBuilder = new StringBuilder(chars.length);
            for (int j = chars.length - 1; j >= 0; j--) {
                stringBuilder.append(chars[i][j]);
                checkWord(stringBuilder,answer,dictionary);
            }
            stringBuilder = new StringBuilder(chars.length);
            //检查列
            for (int j = 0; j < chars.length; j++) {
                stringBuilder.append(chars[j][i]);
                checkWord(stringBuilder,answer,dictionary);
            }
            stringBuilder = new StringBuilder(chars.length);
            for (int j = chars.length - 1; j >= 0; j--) {
                stringBuilder.append(chars[j][i]);
                checkWord(stringBuilder,answer,dictionary);
            }
            stringBuilder = new StringBuilder(chars.length);
        }
        //检查对角线
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i][i]);
            checkWord(stringBuilder,answer,dictionary);
        }
        stringBuilder = new StringBuilder(chars.length);
        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuilder.append(chars[i][i]);
            checkWord(stringBuilder,answer,dictionary);
        }
        stringBuilder = new StringBuilder(chars.length);
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i][chars.length - i - 1]);
            checkWord(stringBuilder,answer,dictionary);
        }
        stringBuilder = new StringBuilder(chars.length);
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[chars.length - i - 1][i]);
            checkWord(stringBuilder,answer,dictionary);
        }
        System.out.println(answer);
    }
    private void checkWord(StringBuilder stringBuilder , HashSet<String> answer , HashSet<String> dictionary){
        String word = stringBuilder.toString();
        if (dictionary.contains(word))
            answer.add(word);
    }

    //1.3使用printDigit方法输出任意double,其中printDigit是一次输出一个0~9数字的函数
    public void test3(double value){
        if (value < 0){
            System.out.print('-');
            value = -value;
        }
        int valueInt = (int)value;
        //输出整数部分
        printOut(valueInt);
        //输出小数部分
        value = value - valueInt;
        if (value == 0.0)
            return;
        System.out.print('.');
        while (value != 0){
            value *= 10;
            valueInt = (int)value;
            printDigit(valueInt);
            value = value - valueInt;
        }

    }
    private void printDigit(int n){
        System.out.print(n);
    }
    private void printOut(int n){
        if(n > 10)
            printOut(n/10);
        printDigit(n%10);
    }
    //1.5返回N的二进制表示中1的个数,利用一个事实:当N是奇数时，其1的个数为N/2中1个数+1
    public int test5(int n){
        int answer = 0;
        if (n == 0)
            return 0;
        if (n % 2 != 0)
            return test5(n/2) + 1;
        else
            return test5(n/2);

        /*答案
        if(n<2)
            return n;
        return n % 2 + test5(n/2);
        */
    }
    //1.6编写两个函数，public void permute（String str);
    // private void permute(char[] str ,int low,int high);
    //第一个函数调用第二个函数输出str中字符的全部排列，第二个函数递归实现
    public void permute(String str){
        permute(str.toCharArray() , 0 , str.length() - 1);
    }
    private void permute(char[] str, int low ,int high){
        if (high <= 1)
            System.out.println(str);
        if (low == high)
            System.out.println(str);
        else{
            for (int i = low; i <= high ; i++) {
                swap(str , i , low);
                permute(str,low + 1, high);
                swap(str , i , low);
            }
        }

    }
    private void swap(char[] array , int a , int b){
        char c = array[a];
        array[a] = array[b];
        array[b] = c;
    }
    /*1.13设计一个泛型类Collection，它存储Object对象的集合在数组中，提供集合的当前大小，
    * 并提供public方法isEmpty、makeEmpty、insert、remove和isPresent
    */
    class Collection{
        //因为没有要求所以不提供扩容机制
        //由题意通过Object实现泛型
        private Object[] objects;
        private int size = 0;
        public Collection(int initSize){
            objects = new Object[initSize];
        }
        public int size(){
            return size;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public void makeEmpty(){
            size = 0;
        }
        public void insert(Object obj){
            objects[size++] = obj;
        }
        public void remove(int index){
            for (int i = index; i < size - 1; i++) {
                objects[i] = objects[i + 1];
            }
            size--;
        }
        public boolean isPresent(Object obj){
            return Arrays.binarySearch(objects,obj) >= 0;
        }
    }
    /*1.14设计一个泛型类OrderedCollection，它存储Comparable对象的集合在数组中，提供集合的当前大小，
     * 并提供public方法isEmpty、makeEmpty、insert、remove、findMin、findMax
     */
    class OrderedCollection{
        //因为没有要求所以不提供扩容机制
        //由题意通过Object实现泛型
        private Comparable[] objects;
        private int size = 0;
        public OrderedCollection(int initSize){
            objects = new Comparable[initSize];
        }
        public int size(){
            return size;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public void makeEmpty(){
            size = 0;
        }
        public void insert(Comparable obj){
            objects[size++] = obj;
        }
        public void remove(int index){
            for (int i = index; i < size - 1; i++) {
                objects[i] = objects[i + 1];
            }
            size--;
        }
        public Comparable findMin(){
            Comparable min = objects[0];
            for (int i = 1; i < size; i++) {
                if (min.compareTo(objects[i]) >= 0){
                    min = objects[i];
                }
            }
            return min;
        }
        public Comparable findMax(){
            Comparable max = objects[0];
            for (int i = 1; i < size; i++) {
                if (max.compareTo(objects[i]) <= 0){
                    max = objects[i];
                }
            }
            return max;
        }
    }
}
