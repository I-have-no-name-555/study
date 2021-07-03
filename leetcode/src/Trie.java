import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/3/23 11:34
 * @description 208. 实现 Trie (前缀树) 中等
 */
public class Trie {
    /*
        实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
    示例:
    Trie trie = new Trie();
    trie.insert("apple");
    trie.search("apple");   // 返回 true
    trie.search("app");     // 返回 false
    trie.startsWith("app"); // 返回 true
    trie.insert("app");
    trie.search("app");     // 返回 true
    说明:
    你可以假设所有的输入都是由小写字母 a-z 构成的。
    保证所有输入均为非空字符串。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * Initialize your data structure here.
     */
//    TrieNode root = new TrieNode();
//
//    public Trie() {
//    }
//
//    /**
//     * Inserts a word into the trie.
//     */
//    public void insert(String word) {
//        char[] chars = word.toCharArray();
//        TrieNode node = root;
//        for (char c : chars) {
//            if (!node.children.containsKey(c))
//                node.children.put(c, new TrieNode());
//            node = node.children.get(c);
//        }
//        node.children.put(null, null);
//    }
//
//    /**
//     * Returns if the word is in the trie.
//     */
//    public boolean search(String word) {
//        char[] chars = word.toCharArray();
//        TrieNode node = root;
//        for (char c : chars) {
//            if (!node.children.containsKey(c))
//                return false;
//            node = node.children.get(c);
//        }
//
//        return node.children.containsKey(null);
//    }
//
//    /**
//     * Returns if there is any word in the trie that starts with the given prefix.
//     */
//    public boolean startsWith(String prefix) {
//        char[] chars = prefix.toCharArray();
//        TrieNode node = root;
//        for (char c : chars) {
//            if (!node.children.containsKey(c))
//                return false;
//            node = node.children.get(c);
//        }
//        return true;
//    }
//
//    static class TrieNode {
//        Map<Character, TrieNode> children = new HashMap<>();
//    }

    //由于只有a-z,将HashMap用数组代替
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (!node.containsKey(c))
                node.put(c, new TrieNode());
            node = node.get(c);
        }
        node.setEnd();
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (node.containsKey(c))
                node = node.get(c);
            else
                return null;
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }


    class TrieNode {
        private TrieNode[] links = new TrieNode[R];

        private static final int R = 26;

        private boolean isEnd;

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }


}
