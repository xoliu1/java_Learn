package 数据结构;

import java.util.*;

public class Trie01 {
    public static void main(String[] args) {
    }
}

/**
class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    //插入单词操作
    public void insert(String word) {
        TrieNode p = root;      //创建移动节点
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (p.child[ch - 'a'] == null) {  //判断该结点是否有过前缀
                p.child[ch - 'a'] = new TrieNode(ch);         //没有则创建
            }
            //移动到下一个节点
            p = p.child[ch - 'a'];
            //打上末尾字符标记
            if (i == word.length() - 1) {
                p.isEnd = true;
            }
        }
    }

    //查询操作
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); ++i) {
            int c = word.charAt(i) - 'a';
            if (p.child[c] == null) {   //一旦发现某个字母未出现在前缀上，返回false
                return false;
            }
            p = p.child[c];
        }
        return p.isEnd;
    }

    class TrieNode {
        char val;
        boolean isEnd = false;
        TrieNode[] child = new TrieNode[26];
        //数组大小跟字符类型的数量相关，我这里只考虑26小写字母

        public TrieNode() {
        }

        public TrieNode(char val) {
            this.val = val;
        }
    }
}
 */


/**
 * ===============力扣：
 */
/*
class SolutionOfLeetCode {

    //================820. 单词的压缩编码
    public int minimumLengthEncoding(String[] words) {
        if (words.length == 0) {
            return 0;
        }
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        Trie trie = new Trie();
        int ans = 0;
        for (int i = 0; i < words.length; ++i) {
           // ans += trie.insert(words[i]);
        }
        return ans;
    }

    //=====================

}
*/
/**
 * 211. 添加与搜索单词 - 数据结构设计
 */
/*class WordDictionary {
    Trienode root;
    class Trienode{
        char val;
        boolean isEnding = false;
        Trienode[] children = new Trienode[26];

        public Trienode() {}

        public Trienode(char val) {
            this.val = val;
        }

        public Trienode[] getChildren() {
            return children;
        }
    }
    public WordDictionary() {
        this.root = new Trienode();
        root.isEnding = true;
    }

    public void addWord(String word) {
        Trienode p = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if(p.children[ch - 'a'] == null){
                p.children[ch - 'a'] = new Trienode(ch);
            }
            p = p.children[ch - 'a'];
            if(i == word.length() - 1){
                p.isEnding = true;
            }
        }
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }
    private boolean dfs(String word, int index, Trienode node) {
        if (index == word.length()) {
            return node.isEnding;
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            Trienode child = node.getChildren()[childIndex];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                Trienode child = node.getChildren()[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}*/

