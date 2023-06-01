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














}