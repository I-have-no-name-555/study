import java.util.Stack;

/**
 * @author :Xuan
 * @date :Create in 2021/4/5 11:13
 * @description 剑指 Offer 31. 栈的压入、弹出序列 中等
 */
public class ValidateStackSequences {
    /*
        输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
    假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
    序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
    示例 1：
    输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
    输出：true
    解释：我们可以按以下顺序执行：
    push(1), push(2), push(3), push(4), pop() -> 4,
    push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
    示例 2：
    输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
    输出：false
    解释：1 不能在 2 之前弹出。
    提示：
    0 <= pushed.length == popped.length <= 1000
    0 <= pushed[i], popped[i] < 1000
    pushed 是 popped 的排列。
    注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,3,5,1,2};
        System.out.println(new ValidateStackSequences().validateStackSequences(pushed, popped));
    }
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0)
            return popped.length == 0;
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int value : pushed) {
            stack.push(value);
            while (!stack.isEmpty() && popped[popIndex] == stack.peek()) {
                popIndex++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    //用pushed本身已扫描的部分来代替栈
    public boolean validateStackSequences2(int[] pushed, int[] popped){
        int i = 0;
        int j = 0;
        for(int num : pushed){
            pushed[i] = num;
            while(i >= 0 && pushed[i] == popped[j]){
                j++;
                i--;
            }
            i++;
        }
        return i == 0;
    }
}
