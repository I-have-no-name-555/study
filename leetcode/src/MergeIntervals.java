import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/1/17 9:14
 * @description 56. 合并区间 中等
 * @update
 */
public class MergeIntervals {
    /*
        给出一个区间的集合，请合并所有重叠的区间。
    示例 1:
    输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
    输出: [[1,6],[8,10],[15,18]]
    解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    示例 2:
    输入: intervals = [[1,4],[4,5]]
    输出: [[1,5]]
    解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
    注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
    提示：
    intervals[i][0] <= intervals[i][1]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/merge-intervals
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {2,6},{1,3},{8,10},{15,18}
        };
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(intervals)));
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int len = intervals.length;
        int[][] ans = new int[len][];
        int left = intervals[0][0];
        int right = intervals[0][1];
        int size = 0;
        for (int i = 1; i < len; i++) {
            if (intervals[i][0] > right){
                ans[size++] = new int[]{left,right};
                left = intervals[i][0];
            }
            right = Math.max(intervals[i][1],right);
        }
        ans[size++] = new int[]{left,right};

        return Arrays.copyOf(ans,size);
    }

    //从提交里找的最快算法  空间换时间
    public int[][] merge2(int[][] intervals) {
        int mergeCount = 0;
        int len = intervals.length;
        for(int i = 0;i < len - 1;i ++){
            for(int j = i + 1;j < len;j ++){
                if(merge(intervals,i,j)){
                    mergeCount ++; //统计需要合并的次数
                    break; //如果进行了合并,需要停止此时循环，因为前一行变成了[1,-1]
                }
            }
        }
        int index = 0;
        int[][] ans = new int[len - mergeCount][];
        for(int[] interval : intervals){
            if(interval[0] != 1 || interval[1] != -1){
                ans[index++] = interval;
            }
        }
        return ans;
    }

    private boolean merge(int[][] intervals,int i,int j){
        int[] row1 = intervals[i];
        int[] row2 = intervals[j];
        if(row1[0] > row2[0]){ //比如[2,6]和[1,3],交换顺序
            int[] temp = row1;
            row1 = row2;
            row2 = temp;
        }
        if(row1[1] < row2[0]){
            return false;
        }
        intervals[j][0] = row1[0];
        intervals[j][1] = Math.max(row1[1],row2[1]);
        intervals[i][0] = 1;
        intervals[i][1] = -1;  //此时由[1,3],[2,6] =>[1,-1],[1,6]
        return true;
    }
}
