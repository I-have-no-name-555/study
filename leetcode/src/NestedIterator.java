import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/7/17 18:29
 * @description 341. 扁平化嵌套列表迭代器 中等
 */
public class NestedIterator implements Iterator<Integer> {
    private final List<NestedInteger> nestedList;
    private final int size;
    private int i = 0;
    private NestedIterator iterator = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        size = nestedList.size();
    }

    @Override
    public Integer next() {
        NestedInteger curr = nestedList.get(i);
        if (curr.isInteger()) {
            i++;
            return curr.getInteger();
        } else {
            return iterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        while (i < size) {
            NestedInteger curr = nestedList.get(i);
            if (curr.isInteger()) {
                return true;
            } else {
                if (iterator == null) {
                    iterator = new NestedIterator(curr.getList());
                }

                if (iterator.hasNext()) {
                    return true;
                } else {
                    iterator = null;
                    i++;
                }
            }
        }

        return false;
    }
}

interface NestedInteger {

    boolean isInteger();

    Integer getInteger();

    List<NestedInteger> getList();
}