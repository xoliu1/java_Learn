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

    /**337. 打家劫舍 III*/
    public int rob(TreeNode root) {
        if (root == null){
            return 0;
        }
        int ans = 0;
        ans += rob(root.left);
        return 1;
    }

    /**1072. 按列翻转得到最大值等行数*/
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

    /**二叉树的层序遍历*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while(!deque.isEmpty()){
            List<Integer> path = new ArrayList<>();
            int size = deque.size();
            while(size-- > 0){
                TreeNode p = deque.poll();
                path.add(p.val);
                if(p.left != null){
                    deque.offer(p.left);
                }
                if(p.right != null){
                    deque.offer(p.right);
                }
            }
            res.add(path);
        }
        return res;
    }
    /**103. 二叉树的锯齿形层序遍历*/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root == null){
            return res;
        }
        boolean flag = true;
        deque.offer(root);
        while(!deque.isEmpty()){
            Deque<Integer> path = new LinkedList<>();
            int size = deque.size();
            while(size-- > 0){
                TreeNode p = deque.poll();
                if (flag){
                    path.offerLast(p.val);
                }else{
                    path.offerFirst(p.val);
                }
                if (p.left != null){
                    deque.offer(p.left);
                }
                if (p.right != null){
                    deque.offer(p.right);
                }
            }
            res.add(new LinkedList<>(path));//转换
            flag = !flag;
        }
        return res;
    }


    /**107. 二叉树的层序遍历 II*/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root == null){
            return res;
        }
        deque.offer(root);
        while (!deque.isEmpty()){
            ArrayList<Integer> path = new ArrayList<>();
            int size = deque.size();
            while(size-- > 0){
                TreeNode p = deque.poll();
                path.add(p.val);
                if (p.left != null){
                    deque.offer(p.left);
                }
                if (p.right != null){
                    deque.offer(p.right);
                }
            }
            res.add(path);
        }
        Collections.reverse(res);
        return res;
    }
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
