import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/4/5 9:51
 * @description 剑指 Offer 29. 顺时针打印矩阵 简单
 */
public class SpiralOrder {
    /*
        输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
    示例 1：
    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
    输出：[1,2,3,6,9,8,7,4,5]
    示例 2：
    输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
    输出：[1,2,3,4,8,12,11,10,9,5,6,7]
    限制：
    0 <= matrix.length <= 100
    0 <= matrix[i].length <= 100
    注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2},
                {4,5},
                {7,8}
        };
        System.out.println(Arrays.toString(new SpiralOrder().spiralOrder(matrix)));
    }
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return new int[0];

        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int index = 0;
        int curRow = 0;
        int curCol = 0;
        int cycleNum = Math.min(m,n) >> 1;

        for (int i = 0; i < cycleNum; i++) {
            while (curCol < n - i)
                res[index++] = matrix[curRow][curCol++];
            curCol--;
            while (curRow < m - i - 1)
                res[index++] = matrix[++curRow][curCol];
            while (curCol > i)
                res[index++] = matrix[curRow][--curCol];
            while (curRow > i + 1)
                res[index++] = matrix[--curRow][curCol];
            curCol++;
        }
        if (m < n){
            while (index < m * n)
                res[index++] = matrix[curRow][curCol++];
        }else {
            while (index < m * n)
                res[index++] = matrix[curRow++][curCol];
        }

        return res;
    }
}
