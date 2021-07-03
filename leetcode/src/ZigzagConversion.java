/**
 * @author :Xuan
 * @date :Create in 2020/12/17 19:42
 * @description 第六题 z字形变换 中等
 * @update
 */
public class ZigzagConversion {
    /*
        将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
    比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
    L   C   I   R
    E T O E S I I G
    E   D   H   N
    之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
    请你实现这个将字符串进行指定行数变换的函数：
    string convert(string s, int numRows);
    示例 1:
    输入: s = "LEETCODEISHIRING", numRows = 3
    输出: "LCIRETOESIIGEDHN"
    示例 2:
    输入: s = "LEETCODEISHIRING", numRows = 4
    输出: "LDREOEIIECIHNTSG"
    解释:
    L     D     R
    E   O E   I I
    E C   I H   N
    T     S     G
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/zigzag-conversion
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        ZigzagConversion test = new ZigzagConversion();
        String s = "ABC";
        System.out.println(test.convert(s,1));
    }
    //直接分析规律 第一次自己想出来这么简单的
    public String convert(String s, int numRows){
        if (s == null || s.length() <= numRows || numRows == 1)
            return s;
        int len = s.length();
        int current = 0;
        StringBuilder sb = new StringBuilder(len);
        while (current < len){//把第一行拿出来单独处理
            sb.append(s.charAt(current));
            current += 2 * (numRows - 1);
        }
        for (int i = 1; i < numRows - 1; i++) {
            current = i;
            sb.append(s.charAt(current));
            while (current < len){
                current += 2 * (numRows - i - 1);//斜着的下一列上是否存在字符
                if (current < len)
                    sb.append(s.charAt(current));
                current += 2 * i;//竖着的下一列上是否存在字符
                if (current < len)
                    sb.append(s.charAt(current));
            }
        }
        //把最后一行拿出来单独处理
        current = numRows - 1;
        while (current < len){
            sb.append(s.charAt(current));
            current += 2 * (numRows - 1);
        }
        return sb.toString();
    }
}
