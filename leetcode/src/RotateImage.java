import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/1/11 11:31
 * @description 48. 旋转图像 中等
 * @update
 */
public class  RotateImage {
    /*
        给定一个 n × n 的二维矩阵表示一个图像。
    将图像顺时针旋转 90 度。
    说明：
    你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
    示例 1:
    给定 matrix =
    [
      [1,2,3],
      [4,5,6],
      [7,8,9]
    ],
    原地旋转输入矩阵，使其变为:
    [
      [7,4,1],
      [8,5,2],
      [9,6,3]
    ]
    示例 2:
    给定 matrix =
    [
      [ 5, 1, 9,11],
      [ 2, 4, 8,10],
      [13, 3, 6, 7],
      [15,14,12,16]
    ],
    原地旋转输入矩阵，使其变为:
    [
      [15,13, 2, 5],
      [14, 3, 4, 1],
      [12, 6, 8, 9],
      [16, 7,10,11]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/rotate-image
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        new RotateImage().rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    //分析规律，n[i][j] --> n[j][n-i-1]
    //每次一圈
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int k = 0; k < n / 2; k++) {
            for (int l = k; l < n - k - 1; l++) {
                int i = k;
                int j = l;
                int current =  matrix[i][j];;
                int next ;
                for (int m = 0; m < 4 ; m++) {
                    next = matrix[j][n - i - 1];
                    matrix[j][n - i - 1] = current;
                    int p = j;
                    j = n - i - 1;
                    i = p;
                    current = next;
                }
            }
        }
    }
    //双重翻转
    public void rotate2(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < (n >> 1); i++) {
            int[] current = matrix[i];
            matrix[i] = matrix[n - i - 1];
            matrix[n - i - 1] = current;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
               matrix[i][j] ^= matrix[j][i];
               matrix[j][i] ^= matrix[i][j];
               matrix[i][j] ^= matrix[j][i];
            }
        }
    }
}
