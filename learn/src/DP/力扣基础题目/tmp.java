package DP.力扣基础题目;

import java.util.*;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/
public class tmp {
}

class Solution {
    /**
     * 740. 删除并获得点数
     */
    public int deleteAndEarn(int[] nums) {
        int mx = nums[0];
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        int[] buc = new int[mx + 1];
        for (int num : nums) {
            buc[num] += num;
        }
        int[] dp = new int[buc.length];
        dp[0] = buc[0];
        dp[1] = Math.max(buc[0], buc[1]);
        for (int i = 2; i < dp.length; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + buc[i]);
        }
        return dp[dp.length - 1];
    }

    /**
     * 64. 最小路径和
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        //dp数组含义：dp[i][j]表示为，走到(i,j)的路径最小和
        //初始化
        //第0行和第0列，一定只能走一条线
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < col; ++i) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        //接下来填充剩余部分
        for (int i = 1; i < row; ++i) {
            for (int j = 1; j < col; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 63. 不同路径 II
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) {
            return 0;
        }
        if(obstacleGrid.length == 1 && obstacleGrid[0].length ==1 && obstacleGrid[0][0] == 0){
            return 1;
        }
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        //dp数组含义：dp[i][j]表示为，走到(i,j)的路径数目
        //初始化
        //第0行和第0列，一定只能走一条线
        dp[0][0] = obstacleGrid[0][0];
        for (int i = 1; i < row; ++i) {
            if (obstacleGrid[i][0] == 1) {
                break;//第一行障碍之后的都走不通
            }
            dp[i][0] = 1;
        }
        for (int i = 1; i < col; ++i) {
            if (obstacleGrid[0][i] == 1) {
                break;//同理
            }
            dp[0][i] = 1;
        }
        //接下来填充剩余部分
        for (int i = 1; i < row; ++i) {
            for (int j = 1; j < col; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }
    /**120. 三角形最小路径和*/
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 1){
            return triangle.get(0).get(0);
        }
        if(triangle.size() == 2){
            return triangle.get(0).get(0) + Math.min(triangle.get(1).get(0), triangle.get(1).get(1));
        }
        //先对第二行进行初始化
        triangle.get(1).set(0, triangle.get(1).get(0) + triangle.get(0).get(0));
        triangle.get(1).set(1, triangle.get(1).get(1) + triangle.get(0).get(0));
        for (int i = 2; i < triangle.size(); ++i) {
            for (int j = 0; j < i + 1; ++j) {
                if(j == 0){
                    triangle.get(i).set(j, triangle.get(i - 1).get(0) + triangle.get(i).get(j));
                }else if(j == i){
                    triangle.get(i).set(j, triangle.get(i - 1).get(j - 1) + triangle.get(i).get(j));
                }else {
                    triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i - 1).get(j), triangle.get(i - 1).get(j - 1)));
                }
            }
        }
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.size(); ++i) {
            mi = Math.min(mi, triangle.get(triangle.size() - 1).get(i));
        }
        return mi;
    }
    /**931. 下降路径最小和*/
    public int minFallingPathSum(int[][] matrix) {
        int size = matrix.length;
        //注意，第一行不用初始化
        //第一列和最后一列的数据所进行的比较很特殊
        for (int i = 1; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if(j == 0){
                    matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i - 1][j + 1]) + matrix[i][j];
                }else if(j == size - 1){
                    matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]) + matrix[i][j];
                }else{
                    matrix[i][j] = Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][j + 1], matrix[i - 1][j - 1])) + matrix[i][j];
                }
            }
        }
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < size; ++i) {
            mi = Math.min(mi, matrix[size - 1][i]);
        }
        return mi;
    }
    /**221. 最大正方形*/
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int sideLength = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if(matrix[i][j] == '1'){
                    if(i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }else{
                    dp[i][j] = 0;
                }
                sideLength = Math.max(dp[i][j], sideLength);
            }
        }
        return sideLength * sideLength;
    }
    /**5.最长回文子串*/
    public String longestPalindrome(String s) {
        if (s.length() < 2){
            return s;
        }
        int size = s.length();
        int maxlen = 1;//最长长度暂定为1（单字符）
        int begin = 0;//记录最长会问子串的起始下标
        boolean[][] dp = new boolean[size][size];
        //每一个只含单字符的子串均为回文子串
        for (int i = 0; i < size; ++i) {
            dp[i][i] = true;
        }
        //先枚举子串长度，从2开始
        for (int L = 2; L <= size; ++L) {
            //定义左边界
            for (int i = 0; i < size; ++i) {
                //表示右边界
                int j = L + i - 1;
                //一旦发生数组越界，break;
                if(j >= size){
                    break;
                }
                //状态转移过程
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j] = false;
                }else{
                    if(j - i < 3){  //加快判断，如果 j - i < 3,则一定回文
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];//和dp[i + 1][j - 1] 保持一致
                    }
                }
                if (dp[i][j] && j - i + 1 > maxlen){    //如果是回文串且大于记录的最大长度，则进行更新
                    maxlen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxlen);
    }

    /**139. 单词拆分*/
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i <= s.length(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    /**518. 零钱兑换 II*/
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        //dp[i] 表示兑换i面额的组合数
        dp[0] = 1;
        for (int i = 0; i < dp.length; ++i) {
            for (int j = coins[i]; j <= amount; ++j) {
                dp[i] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
    /**516. 最长回文子序列*/
    public int longestPalindromeSubseq(String s) {
        if (s.length() <= 1){
            return s.length();
        }
        int[][] dp = new int[s.length()][s.length()];
        //dp[i][j] 表示从i到j的最长回文子序列长度
        //dp[i][i] = 1 ,每个单个字符都是回文子序列
        for (int i = 0; i < dp.length; ++i) {
            dp[i][i] = 1;
        }
        //如果边界相等，则等于dp[i + 1][j - 1] + 2
        //否则 找两边的最大值
        for (int i = dp.length - 1; i >= 0; --i) {
            //需要逆序，因为要从短的子序列到长的子序列
            for (int j = i + 1; j < dp.length; ++j) {
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][dp.length - 1];
    }
    /**712. 两个字符串的最小ASCII删除和*/
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //特殊情况处理，如果i = 0 或者 j = 0时，那么这个值就是另一个子串的所有ASCII码值
        for (int i = 1; i <= s1.length(); ++i) {
            dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
        }
        for (int i = 1; i <= s2.length(); ++i) {
            dp[0][i] = dp[0][i - 1] + s2.codePointAt(i - 1);
        }

        for (int i = 1; i < s1.length(); ++i) {
            for (int j = 1; j < s2.length(); ++j) {
                if(s1.charAt(i) == s2.charAt(j)){   //相等，不加东西，继承上一个的和
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    //此时三种情况，要i不要j,要j不要i，或者都不要，看哪个小
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.codePointAt(i), dp[i][j - 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
    /**
     * 转换思路，求最大ascii公共子序列，然后减去这个和*2，即为答案
     */

    /**72. 编辑距离*/
    public int minDistance(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        //dp[i][j]表示s1[0,i] 和 s2[0,j] 最少操作数
        //初始化，边界情况为另一个子串的长度
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                if (s1.charAt(i - 1) == s2.codePointAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return  dp[len1][len2];
    }

    /**646. 最长数对链*/
    public int findLongestChain(int[][] pairs) {
        if (pairs.length == 0 || pairs[0].length == 0){
            return 0;
        }
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] dp = new int[pairs.length + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i < pairs.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (pairs[j][1] < pairs[i][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int mx = -1001;
        for (int i = 0; i < dp.length; ++i) {
            mx = Math.max(mx, dp[i]);
        }
        return mx;
    }

    /**1218. 最长定差子序列*/
    public int longestSubsequence(int[] arr, int difference) {
//        if (arr.length < 2){
//            return arr.length;
//        }
//        int[] dp = new int[arr.length + 1];
//        Arrays.fill(dp, 1);
//        for (int i = 1; i < arr.length; ++i) {
//            for (int j = 0; j < i; ++j) {
//                if(arr[i] == arr[j] + difference){
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//        int mx = -1001;
//        for (int i = 0; i < dp.length; ++i) {
//            mx = Math.max(mx, dp[i]);
//        }
//        return mx;
        /**
         * 上述方法超时啦！ 草
         * 以下是哈希表的优化
         * */
        if (arr.length < 2){
            return arr.length;
        }
        int mx = -1001;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i - difference, 0) + 1);
            //如果存在该等差项的前一项，那么给这个次数加1
            mx = Math.max(mx, map.get(i));
            //更新最大值
        }
        return mx;
    }

    /**1027. 最长等差数列*/
    public int longestArithSeqLength(int[] nums) {
        if (nums.length < 2){
            return nums.length;
        }
        int mi = 501, mx = -1;
        for (int i : nums) {
            mx = Math.max(mx, i);
            mi = Math.min(mi, i);
        }
        int len = mx - mi;
        int[][] dp = new int[nums.length + 1][len];
        //dp[i][j] 表示 前i个的数字，等差为j的等差数列的个数。
        int mxlen = 1;
        for (int i = -len; i <= len; ++i) {
            mxlen = Math.max(mxlen, longestSubsequence(nums, i));
            //调用搜索定差数列的方法。
        }
        return mxlen;
        /**以上方法能过，但耗时太长太长*/


    }

    /**1035. 不相交的线*/
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        //含义为数组A的前i项和数组B的前j项的不相交的线的个数
        for (int i = 0; i < dp.length; ++i) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; ++i) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= nums1.length; ++i) {
            for (int j = 1; j <= nums2.length; ++j) {
                if (nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**96. 不同的二叉搜索树*/
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;//确保  j == 1 时， dp[i] += 1 * dp[i - j];
        dp[1] = 1;
        if (n == 1){
            return 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**377. 组合总和 Ⅳ*/
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; ++i) {
            for (int j : nums) {
                if (i >= j){
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[target];
    }


}
