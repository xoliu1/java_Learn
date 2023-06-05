package leetcode;

import java.util.*;

public class solution {
    public static void main(String[] args) {

    }
}


class Solution {
    //============================字符串相乘
    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] ans = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; --i) {
            for (int j = n2 - 1; j >= 0; --j) {
                int tmp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                tmp += ans[i + j + 1];
                ans[i + j] = tmp / 10;
                ans[i + j + 1] = tmp % 10;
            }
        }
        StringBuilder rtans = new StringBuilder();
        int idx = 0;
        while (idx < ans.length && ans[idx] == 0) {
            ++idx;
        }
        for (int i = idx; i < ans.length; ++i) {
            rtans.append(ans[i]);
        }
        return rtans.toString();
    }

    //=============================有序数组转avl
    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    public TreeNode toBST(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r + 1) >> 1;
        return new TreeNode(nums[mid], toBST(nums, l, mid - 1), toBST(nums, mid + 1, r));
    }

    //================================完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 1;
        ans += countNodes(root.left);
        ans += countNodes(root.right);
        return ans;
    }

    //===============================删除二叉搜索树的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            TreeNode p = new TreeNode();
            p = root.left;
            while (p.right != null) {
                p = p.right;
            }
            root.left = deleteNode(root.left, p.val);
            p.left = root.left;
            p.right = root.right;
            return p;
        }
        return root;
    }

    //======================修剪二叉搜索树
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    //=======================节点与祖先的最大差值
    public int maxAncestorDiff(TreeNode root) {
        return getDiff(root, root.val, root.val);
    }

    public int getDiff(TreeNode root, int mi, int mx) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        ans = Math.max(Math.abs(root.val - mi), Math.abs(root.val - mx));
        mi = Math.min(root.val, mi);
        mx = Math.max(root.val, mx);
        ans = Math.max(ans, getDiff(root.left, mi, mx));
        ans = Math.max(ans, getDiff(root.right, mi, mx));
        return ans;
    }

    //=========================二叉树的右视图
    //BFS
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode p = queue.poll();
                if (p.left != null) {
                    queue.offer(p.left);
                }
                if (p.right != null) {
                    queue.offer(p.right);
                }
                if (i == size - 1) {
                    ans.add(p.val);
                }
            }
        }
        return ans;
    }

    //===============================1003. 检查替换后的词是否有效
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                stack.push(ch);
            } else if (ch == 'b') {
                if (stack.isEmpty() || stack.peek() != 'a') {
                    return false;
                } else {
                    stack.push(ch);
                }
            } else if (ch == 'c') {
                if (stack.isEmpty() || stack.peek() != 'b') {
                    return false;
                } else {
                    stack.pop();
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 337. 打家劫舍 III
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        ans += rob(root.left);
        return 1;
    }

    /**
     * 1072. 按列翻转得到最大值等行数
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
       /* //本质是找出所有行中模式相同的行数最大值
        HashMap<int[], Integer> map = new HashMap<>();
        for (int[] array : matrix) {
            map.put(array, map.getOrDefault(array, 0) + 1);
            //记录这些模式的次数
        }
        //因为要记录的时模式，所以我们也要将反转后的进行记录
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                matrix[i][j] = Math.abs(matrix[i][j] - 1);
            }
        }
        for (int[] array : matrix) {
            map.put(array, map.getOrDefault(array, 0) + 1);
        }
        Collection<Integer> values = map.values();
        int mx = 1;
        for (Integer i : values) {
            mx = Math.max(i, mx);
        }
        return mx;*/
        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[i][0] == 1) {    //让所有开头都为 0
                for (int j = 0; j < matrix[0].length; ++j) {
                    matrix[i][j] = Math.abs(matrix[i][j] - 1);
                }
            }
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int[] array : matrix) {
            map.put(Arrays.toString(array), map.getOrDefault(Arrays.toString(array), 0) + 1);
        }
        int mx = 1;
        Collection<Integer> values = map.values();
        for (Integer i : values) {
            mx = Math.max(i, mx);
        }
        return mx;
    }

    /**
     * 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            List<Integer> path = new ArrayList<>();
            int size = deque.size();
            while (size-- > 0) {
                TreeNode p = deque.poll();
                path.add(p.val);
                if (p.left != null) {
                    deque.offer(p.left);
                }
                if (p.right != null) {
                    deque.offer(p.right);
                }
            }
            res.add(path);
        }
        return res;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root == null) {
            return res;
        }
        boolean flag = true;
        deque.offer(root);
        while (!deque.isEmpty()) {
            Deque<Integer> path = new LinkedList<>();
            int size = deque.size();
            while (size-- > 0) {
                TreeNode p = deque.poll();
                if (flag) {
                    path.offerLast(p.val);
                } else {
                    path.offerFirst(p.val);
                }
                if (p.left != null) {
                    deque.offer(p.left);
                }
                if (p.right != null) {
                    deque.offer(p.right);
                }
            }
            res.add(new LinkedList<>(path));//转换
            flag = !flag;
        }
        return res;
    }


    /**
     * 107. 二叉树的层序遍历 II
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root == null) {
            return res;
        }
        deque.offer(root);
        while (!deque.isEmpty()) {
            ArrayList<Integer> path = new ArrayList<>();
            int size = deque.size();
            while (size-- > 0) {
                TreeNode p = deque.poll();
                path.add(p.val);
                if (p.left != null) {
                    deque.offer(p.left);
                }
                if (p.right != null) {
                    deque.offer(p.right);
                }
            }
            res.add(path);
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 二叉树的最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = 1;
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                TreeNode p = que.poll();
                if (p.left == null && p.right == null) {
                    return height;
                }
                if (p.left != null) {
                    que.offer(p.left);
                }
                if (p.right != null) {
                    que.offer(p.right);
                }
            }
            ++height;
        }
        return height;
    }

    /**
     * 515. 在每个树行中找最大值
     */
    public List<Integer> largestValues(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int mx = Integer.MIN_VALUE;
            int size = que.size();
            while (size-- > 0) {
                TreeNode p = que.poll();
                mx = Math.max(mx, p.val);
                if (p.left != null) {
                    que.offer(p.left);
                }
                if (p.right != null) {
                    que.offer(p.right);
                }
            }
            res.add(mx);
        }
        return res;
    }

    /**
     * 429. N 叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(Node1 root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<Node1> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            List<Integer> path = new LinkedList<>();
            int size = deque.size();
            while (size-- > 0) {
                Node1 p = deque.poll();
                path.add(p.val);
                for (Node1 child : p.children) {
                    if (child != null) {
                        deque.offer(child);
                    }
                }
            }
            res.add(path);
        }
        return res;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> p_path = getPath(root, p);
        List<TreeNode> q_path = getPath(root, q);
        TreeNode res = root;
        for (int i = 0; i < Math.min(p_path.size(), q_path.size()); ++i) {
            if(p_path.get(i) == q_path.get(i)){
                res = p_path.get(i);
                //找路径相同的最后一个点
            }else{
                break;
            }
        }
        return res;
    }
    public List<TreeNode> getPath(TreeNode root, TreeNode key){
        List<TreeNode> path = new LinkedList<>();
        while(root != key){
            path.add(root);
            if (root.val > key.val){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        path.add(root);
        return path;
    }
    /**538. 把二叉搜索树转换为累加树*/
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return root;
        }
        MidTravel(root);
        return root;
    }
    public void MidTravel(TreeNode root){
        if (root == null){
            return;
        }
        MidTravel(root.right);
        pre += root.val;
        root.val = pre;
        MidTravel(root.left);
    }
    /**116. 填充每个节点的下一个右侧节点指针*/
    public Node connect(Node root) {
        if (root == null){
            return root;
        }
        Node pre = root;
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            pre = que.peek();
            while(size-- > 0){
                Node p = que.poll();
                if(size != 0 || size != que.size() - 1){
                    pre.next = p;
                    pre = p;
                }
                if(p.left != null){
                    que.offer(p.left);
                }
                if(p.right != null){
                    que.offer(p.right);
                }
                if(size == 0){
                    pre.next = null;
                }
            }
        }
        return root;
    }

    /**200. 岛屿数量*/
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1'){
                    fillAround(grid, i, j);//把该点锁在岛屿上的点全部标记
                    ++res;
                }
            }
        }
        return res;
    }
    public void fillAround(char[][]grid, int i, int j){
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] != '1'){
            return;     //如果不是陆地或者超出范围就返回
        }
        grid[i][j] = '0';//把该点抹去
        //祸害周围的
        fillAround(grid, i - 1, j);
        fillAround(grid, i + 1, j);
        fillAround(grid, i, j + 1);
        fillAround(grid, i, j - 1);
    }

    /**98. 验证二叉搜索树*/
    public boolean isValidBST(TreeNode root) {
        return validNode(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    public boolean validNode(TreeNode root, long mx, long mn){
        if (root == null){
            return true;
            //有空节点应返回true
        }
        if (root.val <= mn || root.val >= mx){
            return false;//如果出了范围，直接返回false
        }
        //然后看子节点是否在所应该在的范围内
        return validNode(root.left, root.val, mn) && validNode(root.right, mx, root.val);
    }


    /**208. 实现 Trie (前缀树)*/
    class Trie {
        TrieNode root;
        class TrieNode{
            char val;
            boolean isEnd = false;
            TrieNode[] children;

            public TrieNode() {
                this.children = new TrieNode[26];
            }

            public TrieNode(char val) {
                this.val = val;
                this.children = new TrieNode[26];
            }
        }
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if (p.children[ch - 'a'] == null){
                    p.children[ch - 'a'] = new TrieNode(ch);
                }
                p = p.children[ch - 'a'];
                if (i == word.length() - 1){
                    p.isEnd = true;
                }
            }
        }

        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if(p.children[ch - 'a'] == null){
                    return false;
                }
                p = p.children[ch - 'a'];
            }
            return p.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); ++i) {
                char ch = prefix.charAt(i);
                if(p.children[ch - 'a'] == null){
                    return false;
                }
                p = p.children[ch - 'a'];
            }
            return true;
        }
    }

    /**695. 岛屿的最大面积*/
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if(grid[i][j] == 1){
                    res = Math.max(dfsIsland(grid, i, j), res);
                }
            }
        }
        return res;
    }
    public int dfsIsland(int[][] grid, int i, int j){
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == 0){
            return 0;
        }
        int ans = 1;
        grid[i][j] = 0;
        ans += dfsIsland(grid, i - 1, j);
        ans += dfsIsland(grid, i + 1, j);
        ans += dfsIsland(grid, i, j - 1);
        ans += dfsIsland(grid, i, j + 1);
        return ans;
    }


    /**654. 最大二叉树*/
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return MaxTree(nums, 0, nums.length - 1);
    }
    public TreeNode MaxTree(int[] nums, int l, int r){
        if (l > r){
            return null;
        }
        if (l == r){
            return new TreeNode(nums[l], null, null);
        }
        int maxIndex = getMaxIndex(nums, l, r);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = MaxTree(nums, l, maxIndex - 1);
        root.right = MaxTree(nums, maxIndex + 1, r);
        return root;
    }
    public int getMaxIndex(int[] nums, int l, int r){
        int idx = l;
        for (int i = l + 1; i <= r; ++i) {
            if (nums[i] > nums[idx]){
                idx = i;
            }
        }
        return idx;
    }

    /*380. O(1) 时间插入、删除和获取随机元素*/
    class RandomizedSet {
        ArrayList<Integer> nums;
        HashMap<Integer, Integer> map;
        Random r;

        public RandomizedSet() {
            nums = new ArrayList<>();
            map = new HashMap<>();//存<元素数值, 索引下标>
            r = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)){
                return false;
            }
            int size = nums.size();
            nums.add(val);
            map.put(val, size);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)){
                return false;
            }
            int idx = map.get(val);//获取在数组内的下标
            int lastNum = nums.get(nums.size() - 1);
            nums.set(idx, lastNum);//将要删除数字的位置上边的数字改成最后一个，然后把最后一个删掉。
            nums.remove(nums.size() - 1);
            map.put(lastNum, idx);//把最后一个元素放到新的位置上了，需要更新
            map.remove(val);//哈希表中删除该元素信息
            return true;
        }

        public int getRandom() {
            int RandomIdx = r.nextInt(nums.size());
            return nums.get(RandomIdx);
        }
    }

    /**1161. 最大层内元素和*/
    public int maxLevelSum(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int mx = Integer.MIN_VALUE;
        int idx = 1, i = 1;
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int sum = 0;
            while(size-- != 0){
                TreeNode p = queue.poll();
                sum += p.val;
                if (p.left != null){
                    queue.add(p.left);
                }
                if (p.right != null){
                    queue.add(p.right);
                }
            }
            if (mx < sum){
                mx = sum;
                idx = i;
            }
            ++i;
        }
        return idx;
    }
    /**1448. 统计二叉树中好节点的数目*/
    public int goodNodes(TreeNode root) {
        return travelGoodNodes(root.left, root.val) + travelGoodNodes(root.right, root.val);
    }
    public int travelGoodNodes(TreeNode root, int mx){
        if (root == null){
            return 0;
        }
        int res = 0;
        if (root.val > mx){
            ++res;
            mx = root.val;
        }
        res += travelGoodNodes(root.left, mx);
        res += travelGoodNodes(root.right, mx);
        return res;
    }















//=========================================================================
//=========================================================================
//=========================================================================
//=========================================================================
//=========================================================================
//=========================================================================
//=========================================================================
//=========================================================================
//=========================================================================

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Node1 {
        public int val;
        public List<Node1> children;

        public Node1() {
        }

        public Node1(int _val) {
            val = _val;
        }

        public Node1(int _val, List<Node1> _children) {
            val = _val;
            children = _children;
        }
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
