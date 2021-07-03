import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/3/23 10:58
 * @description 211. 添加与搜索单词 - 数据结构设计 中等
 */
public class DesignAddAndSearchWordsDataStructure {
    /*
        请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
    实现词典类 WordDictionary ：
    WordDictionary() 初始化词典对象
    void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
    bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；
    否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
    示例：
    输入：
    ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
    [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    输出：
    [null,null,null,null,false,true,true,true]
    解释：
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    wordDictionary.search("pad"); // return False
    wordDictionary.search("bad"); // return True
    wordDictionary.search(".ad"); // return True
    wordDictionary.search("b.."); // return True
    提示：
    1 <= word.length <= 500
    addWord 中的 word 由小写英文字母组成
    search 中的 word 由 '.' 或小写英文字母组成
    最多调用 50000 次 addWord 和 search
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class WordDictionary {

//        Map<Integer, List<String>> map = new HashMap<>();
//        /** Initialize your data structure here. */
//        public WordDictionary() {
//        }
//
//        public void addWord(String word) {
//            List<String> list = map.getOrDefault(word.length(),new ArrayList<>());
//            list.add(word);
//            map.put(word.length(),list);
//        }
//
//        public boolean search(String word) {
//            int len = word.length();
//            if (!map.containsKey(len))
//                return false;
//            List<String> list = map.get(len);
//            for (String s : list){
//                boolean flag = true;
//                for (int i = 0; i < len; i++) {
//                    char c = word.charAt(i);
//                    if (c == '.' || c == s.charAt(i))
//                        continue;
//                    flag = false;
//                    break;
//                }
//                if (flag)
//                    return true;
//            }
//            return false;
//        }
        //用前缀树实现

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean flag;
        }
        TrieNode root;
        public WordDictionary() {
            root = new TrieNode();
        }
        public void addWord(String word) {
            char[] array = word.toCharArray();
            TrieNode cur = root;
            for (char c : array) {
                if (cur.children[c - 'a'] == null)
                    cur.children[c - 'a'] = new TrieNode();
                cur = cur.children[c - 'a'];
            }
            cur.flag = true;
        }
        public boolean search(String word) {
            char[] array = word.toCharArray();
            return searchHelp(array,0, root);
        }
        private boolean searchHelp(char[] word, int start, TrieNode root) {
            TrieNode cur = root;
            int len = word.length;
            for (int i = start; i < len; i++) {
                if(word[i] == '.'){
                    for(int j = 0;j < 26; j++){
                        if(cur.children[j] != null){
                            if(searchHelp(word, i + 1,cur.children[j]))
                                return true;
                        }
                    }
                    return false;
                }
                if (cur.children[word[i] - 'a'] == null)
                    return false;
                cur = cur.children[word[i] - 'a'];
            }
            return cur.flag;
        }

    }
}
