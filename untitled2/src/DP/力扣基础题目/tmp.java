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
    
//sadsadsa

}
