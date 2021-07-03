import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/1/15 10:55
 * @description 54. 螺旋矩阵 中等
 * @update
 */
public class SpiralMatrix {
    /*
            给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
    示例 1:
    输入:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    输出: [1,2,3,6,9,8,7,4,5]
    示例 2:
    输入:
    [
      [1, 2, 3, 4],
      [5, 6, 7, 8],
      [9,10,11,12]
    ]
    输出: [1,2,3,4,8,12,11,10,9,5,6,7]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/spiral-matrix
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[][] matrix3 = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9},
                {10,11,12}
        };
        int[][] matrix4 = new int[][]{{1},{2},{3}};
        System.out.println(new SpiralMatrix().spiralOrder(matrix1));
        System.out.println(new SpiralMatrix().spiralOrder(matrix2));
        System.out.println(new SpiralMatrix().spiralOrder(matrix3));
        System.out.println(new SpiralMatrix().spiralOrder(matrix4));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = (Math.min(m, n) + 1) / 2;
        List<Integer> list = new ArrayList<>(m * n);
        for (int k = 0; k < count; k++) {
            int i = k;
            int j = k;
            while (j < n - k)
                list.add(matrix[i][j++]);
            j--;
            i++;
            while (i < m - k )
                list.add(matrix[i++][j]);
            i--;
            j--;
            while (j >= k){
                if (list.size() < m * n)
                    list.add(matrix[i][j--]);
                else return list;
            }
            j++;
            i--;
            while (i > k){
                if (list.size() < m * n)
                    list.add(matrix[i--][j]);
                else return list;
            }
        }
        return list;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        return null;
    }

    public List<Integer> spiralOrder3(int[][] matrix) {
        return null;
    }
}