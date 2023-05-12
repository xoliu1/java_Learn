package DP.完全背包问题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] volume = new int[n + 1];  //体积（重量）
        int[] weight = new int[n + 1];  //价值（权重）
        for (int i = 1; i <= n; ++i) {
            volume[i] = in.nextInt();
            weight[i] = in.nextInt();
        }

        //分的集合是选择第i个物品选择第k个来划分，且 k * volume[i] <= 容量j
        /** 朴素做法*/
      //  int[][] dp = new int[n + 1][m + 1];
      /*
      for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k * volume[i] <= j; ++k) {      //最多k个，也就是全部装第 i 个
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * volume[i]] + k * weight[i]);
                }
            }
        }
      */
        /** 二维优化做法*/
    /*
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j >= volume[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - volume[i]] + weight[i]);
                }
            }
        }
        System.out.println(dp[n][m]);
        */

        /**一维优化做法*/
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = volume[i]; j <= m; ++j) {
                dp[j] = Math.max(dp[j], dp[j - volume[i]] + weight[i]);
            }
        }
        System.out.println(dp[m]);
    }
}


class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
