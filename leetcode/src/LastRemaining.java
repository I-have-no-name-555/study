import java.util.ArrayList;

/**
 * @author :Xuan
 * @date :Create in 2021/5/2 11:26
 * @description 剑指 Offer 62. 圆圈中最后剩下的数字 简单
 */
public class LastRemaining {
    /*
        0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
    求出这个圆圈里剩下的最后一个数字。
    例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，
    因此最后剩下的数字是3。
    示例 1：
    输入: n = 5, m = 3
    输出: 3
    示例 2：
    输入: n = 10, m = 17
    输出: 2
    限制：
    1 <= n <= 10^5
    1 <= m <= 10^6
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    public int lastRemaining2(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }

}
