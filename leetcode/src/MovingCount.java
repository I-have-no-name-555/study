/**
 * @author :Xuan
 * @date :Create in 2021/3/30 11:08
 * @description 剑指 Offer 13. 机器人的运动范围 中等
 */
public class MovingCount {
    /*
        地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
    它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
    例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
    但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
    示例 1：
    输入：m = 2, n = 3, k = 1
    输出：3
    示例 2：
    输入：m = 3, n = 1, k = 0
    输出：1
    提示：
    1 <= n,m <= 100
    0 <= k <= 20
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(new MovingCount().movingCount(16, 8, 4));
    }


    boolean[][] memory;
    int res = 0;
    public int movingCount(int m, int n, int k) {
        if (k == 0 || m == 1 && n == 1) return 1;
        memory = new boolean[m][n];
        dfs(0, 0, k, m, n);
        return res;
    }

    private void dfs(int x, int y, int k, int m, int n) {
        if (x == m || y == n || memory[x][y])
            return;
        if (getSum(x, y) > k)
            return;
        memory[x][y] = true;
        res++;
        dfs(x + 1, y, k, m, n);
        dfs(x, y + 1, k, m, n);
    }

    private int getSum(int m, int n) {
        int sum = 0;
        while (m != 0) {
            sum += m % 10;
            m /= 10;
        }
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
