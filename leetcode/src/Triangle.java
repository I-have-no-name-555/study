import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/2/28 16:44
 * @description 120. 三角形最小路径和 中等
 */
public class Triangle {
    /*
        给定一个三角形 triangle ，找出自顶向下的最小路径和。
    每一步只能移动到下一行中相邻的结点上。
    相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
    也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
    示例 1：
    输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    输出：11
    解释：如下面简图所示：
       2
      3 4
     6 5 7
    4 1 8 3
    自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
    示例 2：
    输入：triangle = [[-10]]
    输出：-10
    提示：
    1 <= triangle.length <= 200
    triangle[0].length == 1
    triangle[i].length == triangle[i - 1].length + 1
    -104 <= triangle[i][j] <= 104
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/triangle
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        triangle.add(list);
        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        triangle.add(list);
        list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(7);
        triangle.add(list);
        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(8);
        list.add(3);
        triangle.add(list);
        System.out.println(new Triangle().minimumTotal(triangle));
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] sums = new int[triangle.size()];
        sums[0] = triangle.get(0).get(0);
        int row = sums.length;
        for (int i = 1; i < row; i++) {
            List<Integer> list = triangle.get(i);
            int num = sums[0];
            sums[0] += list.get(0);
            for (int j = 1; j < i; j++) {
                int sum = sums[j];
                sums[j] = list.get(j) + Math.min(num,sums[j]);
                num = sum;
            }
            sums[i] = num + list.get(i);
        }
        int num = sums[0];
        for (int sum : sums) {
            num = Math.min(num, sum);
        }
        return num;
    }
}
