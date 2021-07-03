/**
 * @author :Xuan
 * @date :Create in 2021/1/20 17:28
 * @description 62. 不同路径 中等
 * @update
 */
public class UniquePaths {
    /*
        一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
    问总共有多少条不同的路径？
    示例 1：
    输入：m = 3, n = 7
    输出：28
    示例 2：
    输入：m = 3, n = 2
    输出：3
    解释：
    从左上角开始，总共有 3 条路径可以到达右下角。
    1. 向右 -> 向右 -> 向下
    2. 向右 -> 向下 -> 向右
    3. 向下 -> 向右 -> 向右
    示例 3：
    输入：m = 7, n = 3
    输出：28
    示例 4：
    输入：m = 3, n = 3
    输出：6
    提示：
    1 <= m, n <= 100
    题目数据保证答案小于等于 2 * 10^9
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/unique-paths
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //显然地，答案是combination(m + n - 2 , m - 1)
    public static void main(String[] args) {
        int m = 51;
        int n = 9;
        System.out.println(new UniquePaths().uniquePaths(m, n));
    }
    public int uniquePaths(int m, int n) {
        if (m > n)
            return uniquePaths(n,m);
        int a = m + n - 2;
        int b = m - 1;
        long ans = 1;
        for (int i = n; i <= a; i++)
            ans *= i;
        for (int i = 1; i <= b; i++)
            ans /= i;
        return (int) ans;
    }
    //官方题解
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y)
            ans = ans * x / y;
        return (int) ans;
    }
}
