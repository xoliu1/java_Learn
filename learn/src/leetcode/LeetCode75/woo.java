package leetcode.LeetCode75;

import java.util.*;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/
public class woo {
}

class Solve{
    /**151. 反转字符串中的单词*/
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
    /**238. 除自身以外数组的乘积*/
    public int[] productExceptSelf(int[] nums) {
        if (nums.length == 0){
            return new int[0];
        }else if (nums.length == 1){
            nums[0] = 0;
            return nums;
        }
        //新建两个int变量维护前缀和和后缀和
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = nums[0];
        suffix[n - 1] = nums[n - 1];
        for (int i = 1; i < n; ++i) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        for (int i = n - 2; i > 0; --i) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        int[] res = new int[nums.length];
        res[0] = suffix[1];
        res[n - 1] =prefix[n - 2];
        for (int i = 1; i < nums.length - 1; ++i) {
            res[i] = suffix[i + 1] * prefix[i - 1];
        }
        return res;
    }
    /**334. 递增的三元子序列*/
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE, b = a;
        for (int num : nums) {
            if (num <= a){
                a = num;
            }else if (num <= b){
                b = num;
            }else return true;
        }
        return false;
    }
    /**1679. K 和数对的最大数目*/
    public int maxOperations(int[] nums, int k) {
        if (nums.length <= 1){
            return 0;
        }
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (map.containsKey(k - i)){
                ++res;
                if (map.get(k - i) == 1){
                    map.remove(k - i);
                }else{
                    map.put(k - i, map.get(k - i) - 1);
                }
                if (map.get(k) == 1){
                    map.remove(k);
                }else{
                    map.put(k, map.get(k) - 1);
                }
            }
        }
        return res;
    }

    /**392. 判断子序列*/
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0){
            return true;
        }
        if (t.length() == 0){
            return false;
        }
        int idx = 0;
        for (int i = 0; i < t.length(); ++i) {
            if (idx > s.length() - 1){
                return true;
            }
            if (s.charAt(idx) == t.charAt(i)){
                ++idx;
            }
        }
        return false;
    }

    /**1456. 定长子串中元音的最大数目*/
    public int maxVowels(String s, int k) {
        if (s.length() == 0 || k == 0){
            return 0;
        }
        int sumOfVowel = 0;
        for (int i = 0; i < k; ++i) {
            if (isVowel(s.charAt(i))){
                ++sumOfVowel;
            }
        }
        int res = sumOfVowel;
        for (int i = k; i < s.length(); ++i) {
            char left = s.charAt(i - k);
            char right = s.charAt(i);
            if (isVowel(left)){
                --sumOfVowel;
            }
            if (isVowel(right)){
                ++sumOfVowel;
            }
            res = Math.max(res, sumOfVowel);
        }
        return res;
    }
    public boolean isVowel(char val){
        return val == 'a' || val == 'e' || val == 'i' || val == 'o' || val == 'u';
    }

    /**1004. 最大连续1的个数 III*/
    public int longestOnes(int[] nums, int k) {
        int leftNum = 0, rightNum = 0;//前缀和（0，n）故不用数组
        int i = 0, j = 0;
        int res = 0;
        for (j = 0; j < nums.length; ++j) {
            rightNum += 1 - nums[j];
            while(leftNum < rightNum - k){    //维护合法的滑动窗口移动左边界。
                leftNum += 1 - nums[i];
                ++i;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    /**643. 子数组最大平均数 I*/
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; ++i) {
            sum += nums[i];
        }
        int MaxSum = sum;
        for (int i = k; i < nums.length; ++i) {
            sum = sum - nums[i - k] + nums[i];
            MaxSum = Math.max(MaxSum, sum);
        }
        return (double)MaxSum / k;
    }

    /**1657. 确定两个字符串是否接近*/
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()){
            return false;
        }
        int n = word1.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[word1.charAt(i) - 'a'];
            ++cnt2[word2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] == 0 && cnt2[i] != 0 ||cnt2[i] == 0 && cnt1[i] != 0){
                return false;
            }
        }
        Arrays.sort(cnt1);
        Arrays.sort(cnt2);
        return Arrays.compare(cnt1, cnt2) == 0;
    }

    /**1207. 独一无二的出现次数*/
    public boolean uniqueOccurrences(int[] arr) {
        int[] hash = new int[2001];
        int[] hashOfHash = new int[2001];
        for (int i : arr) {
            ++hash[i + 1000];//统计每一个数的出现次数
        }

        for (int i : hash) {
            if(i != 0){
                ++hashOfHash[i];
                if(hashOfHash[i] > 1){
                    return false;
                }
            }
        }
        return true;
    }
    /**2130. 链表最大孪生和*/
    public int pairSum(ListNode head) {
        ListNode p = head;
        ArrayDeque<ListNode> deque = new ArrayDeque<>();
        while(p != null){
            deque.addFirst(p);
            p = p.next;
        }
        int mx = 0;
        while(!deque.isEmpty()){
            mx = Math.max(mx, deque.pollFirst().val + deque.pollLast().val);
        }
        return mx;
    }

    /**328.奇偶链表*/
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode p = head;
        ListNode p1 = head.next;
        ListNode p2 = p1;
        int n = 1;
        while(p != null && p1 != null && p1.next != null){
            if (n % 2 == 1){
                p.next = p.next.next;
                p = p.next;
            }else {
                p1.next = p1.next.next;
                p1 = p1.next;
            }
            ++n;
        }
        p.next = p2;
        return head;
    }



}

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