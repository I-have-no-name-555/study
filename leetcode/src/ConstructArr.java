import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/5/6 16:19
 * @description 剑指 Offer 66. 构建乘积数组 中等
 */
public class ConstructArr {
    /*
        给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
    其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
    即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
    示例:
    输入: [1,2,3,4,5]
    输出: [120,60,40,30,24]
    提示：
    所有元素乘积之和不会溢出 32 位整数
    a.length <= 100000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] a = {7, 2, 2, 4, 2, 1, 8, 8, 9, 6, 8, 9, 6, 3, 2, 1};
        System.out.println(Arrays.toString(new ConstructArr().constructArr(a)));
    }
    public int[] constructArr(int[] a) {
        if (a == null || a.length < 2){
            return a;
        }
        //除0之外全部乘积
        int product = 1;
        //如果有零，需要特殊处理
        int zeroIndex = -1;
        //获取除0之外全部数之积
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0){
                //如果不止一个0那么结果是全0
                if (zeroIndex != -1){
                    return new int[a.length];
                }
                zeroIndex = i;
                continue;
            }
            product *= a[i];
        }
        //如果有且只有一个0，结果中除这一位外全是0，这一位上是全部非0数之积
        if (zeroIndex != -1){
            int[] res = new int[a.length];
            res[zeroIndex] = product;
            return res;
        }
        //计算每个数
        for (int i = 0; i < a.length; i++) {
            a[i] = divide(product,a[i]);
        }
        return a;
    }
    private int divide(int dividend, int divisor){
        int res = 0;
        //除数与被除数是否一正一负
        boolean flag = (dividend ^ divisor) < 0;
        //置为正数
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        long k = getNearestPowerOfTwo(dividend);

        //除法器实现 也是性能瓶颈
        while (dividend != 0 && k != 0){
            if (k * divisor <= dividend){
                res += k;
                dividend -= k * divisor;
            }
            k >>= 1;
        }

        return flag ? -res : res;
    }
    //计算离n最近且大于等于n的2的幂
    private long getNearestPowerOfTwo(long n){
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public int[] constructArr2(int[] a) {
        if(a.length == 0)
            return new int[0];
        int[] b = new int[a.length];
        b[0] = 1;
        int tmp = 1;
        for(int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        for(int i = a.length - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }
        return b;
    }

}
