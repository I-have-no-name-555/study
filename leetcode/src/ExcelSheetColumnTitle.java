/**
 * @author :Xuan
 * @date :Create in 2021/5/12 11:41
 * @description 168. Excel表列名称 简单
 */
public class ExcelSheetColumnTitle {
    /*
        给定一个正整数，返回它在 Excel 表中相对应的列名称。
    例如，
        1 -> A
        2 -> B
        3 -> C
        ...
        26 -> Z
        27 -> AA
        28 -> AB
        ...
    示例 1:
    输入: 1
    输出: "A"
    示例 2:
    输入: 28
    输出: "AB"
    示例 3:
    输入: 701
    输出: "ZY"
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/excel-sheet-column-title
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String convertToTitle(int columnNumber) {
        if (columnNumber <= 0)
            return "";
        int remainder;
        int quotient = columnNumber;
        StringBuilder sb = new StringBuilder();
        do {
            remainder = quotient % 26;
            quotient = quotient / 26;
            if (remainder == 0){
                sb.append('Z');
                quotient--;
            }else sb.append((char) ('A' + remainder - 1));
        }while (quotient != 0);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(701));
    }
}
