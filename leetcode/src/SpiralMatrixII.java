import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/1/19 18:14
 * @description 59. 螺旋矩阵 II 中等
 * @update
 */
public class SpiralMatrixII {
    /*
        给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
    示例:
    输入: 3
    输出:
    [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/spiral-matrix-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int n = 2;
        System.out.println(Arrays.deepToString(new SpiralMatrixII().generateMatrix(n)));
    }
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = (n + 1) / 2;
        int numCount = 0;
        for (int k = 0; k < count; k++) {
            int i = k;
            int j = k;
            while (j < n - k)
                matrix[i][j++] = ++numCount;
            j--;
            i++;
            while (i < n - k )
                matrix[i++][j] = ++numCount;
            i--;
            j--;
            while (j >= k){
                if (numCount < n * n)
                    matrix[i][j--] = ++numCount;
                else return matrix;
            }
            j++;
            i--;
            while (i > k){
                if (numCount < n * n)
                    matrix[i--][j] = ++numCount;
                else return matrix;
            }
        }
        return matrix;
    }

}
