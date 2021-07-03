/**
 * @author :Xuan
 * @date :Create in 2021/5/13 15:08
 * @description 171. Excel表列序号 简单
 */
public class ExcelSheetColumnNumber {
    /*
        给定一个Excel表格中的列名称，返回其相应的列序号。
    例如，
        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
        ...
    示例 1:
    输入: "A"
    输出: 1
    示例 2:
    输入: "AB"
    输出: 28
    示例 3:
    输入: "ZY"
    输出: 701
    致谢：
    特别感谢 @ts 添加此问题并创建所有测试用例。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/excel-sheet-column-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.length() == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < columnTitle.length(); i++)
            res = res * 26 + columnTitle.charAt(i) - 'A' + 1;
        return res;
    }
}
