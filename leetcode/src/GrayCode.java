import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/2/4 18:21
 * @description 89. 格雷编码 中等
 * @update
 */
public class GrayCode {
    /*
        格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
    给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
    格雷编码序列必须以 0 开头。
    示例 1:
    输入: 2
    输出: [0,1,3,2]
    解释:
    00 - 0
    01 - 1
    11 - 3
    10 - 2
    对于给定的 n，其格雷编码序列并不唯一。
    例如，[0,2,3,1] 也是一个有效的格雷编码序列。
    00 - 0
    10 - 2
    11 - 3
    01 - 1
    示例 2:
    输入: 0
    输出: [0]
    解释: 我们定义格雷编码序列必须以 0 开头。
         给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
         因此，当 n = 0 时，其格雷编码序列为 [0]。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/gray-code
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static final List<List<Integer>> LISTS = new ArrayList<>(32);
    static {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        LISTS.add(list);
    }
    public List<Integer> grayCode(int n) {
        if (n <= LISTS.size() - 1)
            return LISTS.get(n);
        int halfLen = 1 << (n - 1);
        List<Integer> list = new ArrayList<>(halfLen << 1);
        List<Integer> lastList = grayCode(n - 1);
        list.addAll(lastList);
        for (int i = lastList.size() - 1; i >= 0; i--) {
            list.add(halfLen | lastList.get(i));
        }
        LISTS.add(list);
        return list;
    }
    //以二进制为 0 值的格雷码为第零项，第一项改变最右边的位元，第二项改变右起第一个为1的位元的左边位元，
    // 第三、四项方法同第一、二项，如此反复，即可排列出n个位元的格雷码。
    public List<Integer> grayCode2(int n) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0); //初始化第零项
        for (int i = 1; i < 1 << n; i++) {
            int previous = list.get(i - 1);
            if ((i & 1) == 1) {
                previous ^= 1;
                list.add(previous);
            } else {
                int temp = previous;
                for (int j = 0; j < n; j++) {
                    if ((temp & 1) == 1) {
                        previous = previous ^ (1 << (j + 1));
                        list.add(previous);
                        break;
                    }
                    temp = temp >> 1;
                }
            }
        }
        return list;
    }
    //公式
    public List<Integer> grayCode3(int n) {
        List<Integer> gray = new ArrayList<>(1 << n);
        for(int binary = 0;binary < 1 << n; binary++){
            gray.add(binary ^ binary >> 1);
        }
        return gray;
    }
//    作者：windliang
//    链接：https://leetcode-cn.com/problems/gray-code/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--12/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static void main(String[] args) {
        int n = 2;
        System.out.println(new GrayCode().grayCode(n));
    }
}
