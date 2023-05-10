package DP.z01背包问题;

import java.util.*;
import java.io.*;

/**
 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。 第 i 件物品的体积是 vi ，价值是 wi 。
 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。
 */
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

        /**二维*/
       /* int[][] dp = new int[n + 1][m + 1];//dp[i][j]表示在 只能选择前i个物品，背包容量为j的情况下，背包中物品的最大价值
        //二维
        //分成两个集合，即包含i和不包含i
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if(j >= volume[i]){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - volume[i]] + weight[i]);
                // 前一个为不包含i的情况，j容量下，选择前 i - 1 物品的最大价值
                //后一个为包含i，即转化为：选择在 i - 1 个物品最大价值，在加上第i个物品的价值
                }else{
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        System.out.println(dp[n][m]);*/
    /** 一维*/
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = m; j >= volume[i]; --j) {
                //选择逆序遍历，这样不会让 i - 1 被覆盖，dp[j - volume[i]] 一定先被计算，所以结果是第i层的而非 i - 1 层
                dp[j] = Math.max(dp[j], dp[j - volume[i]] + weight[i]);     //
            }
        }
        System.out.println(dp[m]);
    }
}


class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}