import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/1/5 11:15
 * @description 36. 有效的数独 中等
 * @update
 */
public class ValidSudoku {
    /*
        判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
    数独部分空格内已填入了数字，空白格用 '.' 表示。
    示例 1:
    输入:
    [
      ["5","3",".",".","7",".",".",".","."],
      ["6",".",".","1","9","5",".",".","."],
      [".","9","8",".",".",".",".","6","."],
      ["8",".",".",".","6",".",".",".","3"],
      ["4",".",".","8",".","3",".",".","1"],
      ["7",".",".",".","2",".",".",".","6"],
      [".","6",".",".",".",".","2","8","."],
      [".",".",".","4","1","9",".",".","5"],
      [".",".",".",".","8",".",".","7","9"]
    ]
    输出: true
    示例 2:
    输入:
    [
      ["8","3",".",".","7",".",".",".","."],
      ["6",".",".","1","9","5",".",".","."],
      [".","9","8",".",".",".",".","6","."],
      ["8",".",".",".","6",".",".",".","3"],
      ["4",".",".","8",".","3",".",".","1"],
      ["7",".",".",".","2",".",".",".","6"],
      [".","6",".",".",".",".","2","8","."],
      [".",".",".","4","1","9",".",".","5"],
      [".",".",".",".","8",".",".","7","9"]
    ]
    输出: false
    解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
         但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
    说明:
    一个有效的数独（部分已被填充）不一定是可解的。
    只需要根据以上规则，验证已经填入的数字是否有效即可。
    给定数独序列只包含数字 1-9 和字符 '.' 。
    给定数独永远是 9x9 形式的。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/valid-sudoku
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //直接遍历
    public boolean isValidSudoku(char[][] board) {
        boolean[] checkCol = new boolean[9];
        boolean[] checkRow = new boolean[9];
        boolean[] check = new boolean[9];
        char c;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((c = board[i][j]) != '.') {
                    if (checkRow[getNum(c)])
                        return false;
                    else checkRow[getNum(c)] = true;
                }
                if ((c = board[j][i]) != '.') {
                    if (checkCol[getNum(c)])
                        return false;
                    else checkCol[getNum(c)] = true;
                }
            }
            Arrays.fill(checkCol, false);
            Arrays.fill(checkRow, false);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if ((c = board[3 * i + k][3 * j + l]) != '.') {
                            if (check[getNum(c)])
                                return false;
                            else check[getNum(c)] = true;
                        }
                    }
                }
                Arrays.fill(check, false);
            }
        }
        return true;
    }

    private int getNum(char c) {
        return c - '0' - 1;
    }

    //优化
    public boolean isValidSudoku2(char[][] board) {
        boolean[] checkCol = new boolean[9];
        boolean[] checkRow = new boolean[9];
        boolean[] check = new boolean[9];
        char c;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((c = board[i][j]) != '.') {
                    if (checkRow[getNum(c)])
                        return false;
                    else checkRow[getNum(c)] = true;
                }
                if ((c = board[j][i]) != '.') {
                    if (checkCol[getNum(c)])
                        return false;
                    else checkCol[getNum(c)] = true;
                }
                if ((c = board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3]) != '.') {
                    if (check[getNum(c)])
                        return false;
                    else check[getNum(c)] = true;
                }
                Arrays.fill(checkCol, false);
                Arrays.fill(checkRow, false);
                Arrays.fill(check, false);
            }
        }
        return true;
    }

    //位运算
    public boolean isValidSudoku3(char[][] board){
        int[] map = new int[9];
        for(int y=0; y<9; y++){
            for(int x=0; x<9; x++){
                int key = board[y][x] - '1';
                if(key >= 0 && key <= 8) {
                    int index = (1<<x)     //最低9位存放列号
                            | (1<<(y+9))    //中间9位存放行号
                            | (1<<(x/3 + y/3*3 + 18));  // z为9宫格区域序号
                    int old = map[key];
                    if((old & index) == 0) //无重复
                        map[key] = old | index;
                    else
                        return false;
                }
            }
        }
        return true;
    }
}
