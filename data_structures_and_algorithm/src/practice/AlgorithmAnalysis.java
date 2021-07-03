package practice;

import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2020/12/7 20:14
 * @description 第二章习题
 * @update
 */
public class AlgorithmAnalysis {
    /*
    2.15 在递增数列中找到Ai＝i，没有返回0，i从1开始
     */
    public int test15(int[] a){
        int i = a.length/2;
        int left = 0;
        int right = a.length;
        while (i != 0 && i != a.length){
            //a[0]是A1
            if (a[i] == i+1)
                return i + 1;
            if (a[i] < i + 1){
                left = i;
                i = (left + right) / 2;
            }
            if (a[i] > i + 1){
                right = i;
                i = (left + right) / 2;
            }
        }
        return 0;
    }
    /*
    2.17 a求最小子序列和，c求最大子序列乘积
    在a中，若全为正返回0，c中全为负返回1
     */
    public int test17A(int[] a){
        int minSum = 0;
        int thisSum = 0;
        for (int value : a) {
            if (value <= 0) {
                thisSum += value;
                minSum = Math.min(thisSum, minSum);
            }
            else {
                thisSum = 0;
            }
        }
        return minSum;
    }
    public int test17C(int[] a){
        int maxProduct = 0;
        int thisProduct = 1;
        for(int value : a){
            if(value > 0) {
                thisProduct *= value;
                maxProduct = Math.max(thisProduct,maxProduct);
            }
            else {
                thisProduct = 1;
            }
        }
        return maxProduct;
    }
    /*
    2.27 输入一个N阶方阵，从左到右递增，从上到下递增，在O（N）内判断给定数X是否在矩阵中
     */
    public boolean test27(int[][] a , int x){

        if(x < a[0][0] || x > a[a.length-1][a.length - 1])
            return false;
        if (x == a[0][0])
            return true;

        boolean result = false;
        for (int i = a.length - 1; i > 0; i--) {
            if (x < a[i][i])
                continue;
            //检查本列
            int left = 0;
            int right = i;
            int k = (left + right) / 2;
            while (k != 0 && k != i){
                if (a[i][k] == x )
                    return true;
                if(a[i][k] < x){
                    left = k;
                    k = (left + right) / 2;
                }
                if(a[i][k] > x){
                    right = k;
                    k = (left + right) / 2;
                }
            }
            //检查本行
            return Arrays.binarySearch(a[i] , x) >= 0;

        }
        //其实没用
        return false;
    }
    /*
    2.28 对于正数数组，设计有效算法确定
        a.a[i] + a[j]
        b.a[j] - a[i]
        c.a[j] * a[i]
        d.a[j] / a[i]
        的最大值，其中j >= i
     */
    public int test28A(int[] a){
        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] < max1)
                max2 = Math.max(a[i],max2);
            else
                max1 = a[i];
        }
        return max1 + max2;
    }
    public int test28B(int[] a){
        int max = 0;
        int min = 0;
        for (int i = 0; i < a.length; i++) {

            max = Math.max(a[i],max);
            min = Math.min(a[i],min);
        }
        return max - min;
    }
    public int test28C(int[] a){
        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] < max1)
                max2 = Math.max(a[i],max2);
            else
                max1 = a[i];
        }
        return max1 * max2;
    }
    public int test28D(int[] a){
        int max = 0;
        int min = 1;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(a[i],max);
            min = Math.min(a[i],min);
        }
        return max / min;
    }
}
