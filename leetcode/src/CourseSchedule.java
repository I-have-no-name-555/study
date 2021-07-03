import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/5/25 10:31
 * @description 207. 课程表 中等
 */
public class CourseSchedule {
    /*
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
    其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
    示例 1：
    输入：numCourses = 2, prerequisites = [[1,0]]
    输出：true
    解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
    示例 2：
    输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
    输出：false
    解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；
    并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
    提示：
    1 <= numCourses <= 10^5
    0 <= prerequisites.length <= 5000
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    prerequisites[i] 中的所有课程对 互不相同
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/course-schedule
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        for (int[] cp : prerequisites) {
            inDegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        for (int i = 0; i < numCourses; i++)
            if (inDegrees[i] == 0) queue.add(i);
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for (int cur : adjacency.get(pre))
                if (--inDegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);
        for(int i = 0; i < numCourses; i++)
            if(!dfs(adjacency, flags, i)) return false;
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;
        if(flags[i] == -1) return true;
        flags[i] = 1;
        for(Integer j : adjacency.get(i))
            if(!dfs(adjacency, flags, j)) return false;
        flags[i] = -1;
        return true;
    }

}
