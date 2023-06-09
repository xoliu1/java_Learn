package 专项突破;

import java.util.*;

public class leetcodesolution {
}


class offer {
    /**
     * 剑指 Offer II 001. 整数除法
     */
    public int divide(int a, int b) {
        if (a == 0x80000000 && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (b == 1) {
            return a;
        }

        byte flag = 2;
        if (a < 0) {
            --flag;
            a = -a;
        }
        if (b < 0) {
            --flag;
            b = -b;
        }
        int ans = devideans(a, b);
        return flag == 1 ? -ans : ans;
    }

    public int devideans(int a, int b) {
        int ret = 0;
        while (a > b) {
            int value = b;
            int quotient = 1;
            while (value >= 0xc0000000 && a <= value + value) {
                quotient += quotient;
                value += value;
            }
            ret += quotient;
            a -= value;
        }
        return ret;
    }

    /**
     * 剑指 Offer II 002. 二进制加法
     */
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int numa = i >= 0 ? a.charAt(i--) - '0' : 0 ;
            int numb = j >= 0 ? b.charAt(j--) - '0' : 0 ;
            int sum = numa  + numb + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            ans.append(sum);
        }
        if (carry == 1){
            ans.append(1);
        }
        return ans.reverse().toString();
    }
    /**剑指 Offer II 003. 前 n 个数字二进制中 1 的个数*/
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for(int i = 1; i <= n; ++i){
            int j = i;
            while(j != 0){
                ++ans[i];
                j &= j - 1;
            }
        }
        return ans;
    }
    /**剑指 Offer II 004. 只出现一次的数字 */
    public int singleNumber(int[] nums) {
        int[] digit = new int[32];
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < 32; ++j) {
                digit[i] += (nums[i] >> (31 - i) & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < digit.length; ++i) {
            ans = ans << 1 + digit[i] % 3;
        }
        return ans;
    }
    /**剑指 Offer II 005. 单词长度的最大乘积*/
    public int maxProduct(String[] words) {
        boolean[][] hashmap = new boolean[words.length][26];
        for (int i = 0; i < words.length; ++i) {
            for (char ch : words[i].toCharArray()) {
                hashmap[i][ch - 'a'] = true;    //没有出现的就是false
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < words.length - 1; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                int idx = 0;
                while(idx < 26){
                    if(hashmap[i][idx] && hashmap[j][idx]){
                        break;  //一旦遇到同时true即同时出现的就break;
                    }
                    ++idx;
                }
                if(idx == 26){
                    maxProduct = Math.max(words[i].length() * words[j].length(), maxProduct);
                }
            }
        }
        return maxProduct;
    }


    /**
     * 剑指 Offer II 006. 排序数组中两个数字之和
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{numbers[i], numbers[j]};
            } else if (numbers[i] + numbers[j] >= target) {
                --j;
            } else {
                ++i;
            }
        }
        return null;
    }

    /**
     * 剑指 Offer II 007. 数组中和为 0 的三个数
     */
    //twosum的进化，且要进行去重处理
    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (nums.length >= 3) {
            Arrays.sort(nums);
            int i = 0;
            while (i < nums.length - 2) {
                TwoSum(ans, nums, i);
                int tmp = nums[i];
                while (nums[i] == tmp && i < nums.length - 2) {
                    ++i;
                }
            }
        }
        return ans;
    }

    public void TwoSum(List<List<Integer>> ans, int[] nums, int idx) {
        int j = idx + 1, k = nums.length - 1;
        while (j < k) {
            if (nums[idx] + nums[j] + nums[k] == 0) {
                ans.add(Arrays.asList(nums[idx], nums[j], nums[k]));//还可以这么用！涨姿势！
                //进行去重，防止重复，让j往后走
                int tmp = nums[j];
                while (j < k && nums[j] == tmp) {
                    ++j;
                }
            } else if (nums[idx] + nums[j] + nums[k] <= 0) {
                ++j;
            } else {
                --k;
            }
        }
    }
    /**剑指 Offer II 008. 和大于等于 target 的最短子数组*/
        public int minSubArrayLen(int target, int[] nums) {
            int i = 0, sum = 0, length = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += nums[j];
                while (sum >= target) {
                    if (length == 0) {
                        length = j - i + 1;
                    } else {
                        length = Math.min(length, j - i + 1);
                    }
                    sum -= nums[i];
                    ++i;
                }
            }
            return length;
        }
    /**剑指 Offer II 009. 乘积小于 K 的子数组*/
    public int numSubarrayProductLessThanK(int[] a, int k) {
        if (k == 1 || k == 0) {
            return 0;
        }
        int i = 0, j = 0, sum = 1;
        int ans = 0;
        while (j < a.length) {
            sum *= a[j];
            while (sum >= k) {
                sum /= a[i];
                ++i;
            }
            ans += j - i + 1;
            ++j;
        }
        return ans;
    }
    /**剑指 Offer II 010. 和为 k 的子数组*/
    public int subarraySum(int[] nums, int k) {
        int sum = 0, cnt = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        //如果 sum == k ,即 containKey(0),也需要算上。
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if(map.containsKey(sum - k)){
                cnt += map.get(sum - k);
            }
            map.put(sum,map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
    /**剑指 Offer II 011. 0 和 1 个数相同的子数组*/
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                --nums[i];
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;

    }
    /**剑指 Offer II 012. 左右两边子数组的和相等*/
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for(int i = 0;i < nums.length;++i){
            sum += nums[i];
        }
        int tmp = 0;
        for(int i = 0;i < nums.length;++i){
            if(tmp == sum - nums[i] - tmp){
                return i;
            }
            tmp += nums[i];
        }
        return -1;
    }
    /**剑指 Offer II 013. 二维子矩阵的和*/
    class NumMatrix {
        int [][] prefix;
        public NumMatrix(int[][] matrix) {
            if(matrix.length != 0){
                int n = matrix[0].length, m = matrix.length;
                prefix = new int[m + 1][n + 1];
                for (int i = 1; i <= m; ++i) {
                    for (int j = 1; j <= n; ++j) {
                        prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int x1, int y1, int x2, int y2) {
            return prefix[x2][y2] - prefix[x1 - 1][y2] - prefix[x2][y1 - 1] + prefix[x1 - 1][y1 - 1];
        }
    }


     /**
     * 剑指 Offer II 018. 有效的回文
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            }
            while (i < j && Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            ++i;
            --j;
        }
        return i == j;
    }


    /**
     * 剑指 Offer II 019. 最多删除一个字符得到回文
     */
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        for (; left < s.length() / 2; ++left, --right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
        }
        if (left == s.length() / 2) {
            return true;
        } else {
            return ispalindrome(s, left + 1, right) || ispalindrome(s, left, right - 1);
        }
    }

    public boolean ispalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            ++left;
            --right;
        }
        return left >= right;

    }


}