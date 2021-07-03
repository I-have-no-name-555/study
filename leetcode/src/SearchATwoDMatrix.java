import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/1/26 22:04
 * @description 74. 搜索二维矩阵 中等
 * @update
 */
public class SearchATwoDMatrix {
    /*
        编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
    每行中的整数从左到右按升序排列。
    每行的第一个整数大于前一行的最后一个整数。
    示例 1：
    输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    输出：true
    示例 2：
    输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    输出：false
    提示：
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -104 <= matrix[i][j], target <= 104
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/search-a-2d-matrix
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] matrix = {{1,1}};
        int target = 3;
        System.out.println(new SearchATwoDMatrix().searchMatrix(matrix, target));
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left <= right){
            int mid = (left + right) >> 1;
            int i = mid / n;
            int j = mid % n;
            if (target == matrix[i][j])
                return true;
            if (target < matrix[i][j])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }
}
