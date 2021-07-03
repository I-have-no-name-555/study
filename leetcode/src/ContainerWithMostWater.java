/**
 * @author :Xuan
 * @date :Create in 2020/12/19 11:16
 * @description 第11题 盛最多水的容器 中等
 * @update
 */
public class ContainerWithMostWater {
    /*
        给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
        在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，
        使得它们与 x 轴共同构成的容器可以容纳最多的水。
    说明：你不能倾斜容器。
    输入：[1,8,6,2,5,4,8,3,7]
    输出：49
    解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
    示例 2：
    输入：height = [1,1]
    输出：1
    示例 3：
    输入：height = [4,3,2,1,4]
    输出：16
    示例 4：
    输入：height = [1,2,1]
    输出：2
    提示：
    n = height.length
    2 <= n <= 3 * 104
    0 <= height[i] <= 3 * 104
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/container-with-most-water
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        ContainerWithMostWater test = new ContainerWithMostWater();
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(test.maxArea1(height));
        System.out.println(test.maxArea2(height));
    }
    //暴力遍历
    public int maxArea1(int[] height) {
        int len = height.length;
        int maxArea = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                maxArea = Math.max(maxArea,Math.min(height[i],height[j]) * (j - i));
            }
        }
        return maxArea;
    }
    //双指针
    public int maxArea2(int[] height){
        int len = height.length;
        int maxArea = 0;
        int left = 0;
        int right = len - 1;
        while (left < right){
            maxArea = Math.max(maxArea,(len-- - 1) * (height[left] > height[right] ?
                    height[right--] : height[left++]));
        }
        return maxArea;
    }
    //优化 不需要指针每次移动后都进行一次比较
    public int maxArea3(int[] height){
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        while(left < right){
            if(height[left] <= height[right]){
                int minHeight = height[left];
                ans = Math.max(ans, minHeight * (right - left));
                while(left < right && height[left] <= minHeight)
                    left++;
            }else{
                int minHeight = height[right];
                ans = Math.max(ans, minHeight * (right - left));
                while(left < right && height[right] <= minHeight)
                    right--;
            }
        }
        return ans;
    }
}
