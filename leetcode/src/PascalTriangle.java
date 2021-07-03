import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/2/27 14:01
 * @description 118. 杨辉三角 简单
 */
public class PascalTriangle {
    /*
        给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
    在杨辉三角中，每个数是它左上方和右上方的数的和。
    示例:
    输入: 5
    输出:
    [
         [1],
        [1,1],
       [1,2,1],
      [1,3,3,1],
     [1,4,6,4,1]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/pascals-triangle
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        ans.add(list);
        for (int i = 0; i < numRows - 1; i++) {
            list = ans.get(i);
            List<Integer> curList = new ArrayList<>();
            curList.add(1);
            for (int j = 1; j <= i ; j++) {
                curList.add(list.get(j) + list.get(j - 1));
            }
            curList.add(1);
            ans.add(curList);
        }
        return ans;
     }
}
