package DP.力扣基础题目;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/
public class Stock_Problem {

}
class stock{

    /**122. 买卖股票的最佳时机 II*/
    public int maxProfit2(int[] prices) {
        if (prices.length == 1){
            return prices[0];
        }
        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];
        //dp[i][1] 表示第 i 天手里有股票的最大收益
        //dp[i][0] 表示第 i 天手里没有股票的最大收益
        //那么分为前天有股票或者没股票的情况
        for (int i = 1; i < prices.length; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //①前一天已经没有股票，②或者有了，那么就把他卖出
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[prices.length - 1][0];
    }
    /**123. 买卖股票的最佳时机 III*/
    public int maxProfit3(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        /*
        //dp[i][0 - 4] 五种状态
        int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][4];*/
        /**以上花费28ms
         * 下边的花费2ms，击败92%
         * */
        int[] dp = new int[5];
        dp[1] = dp[3] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }
        return dp[4];
    }


    /**188. 买卖股票的最佳时机 IV*/
    public int maxProfit4(int k, int[] prices) {
        if (prices.length == 1){
            return 0;
        }
        int[] dp = new int[2 * k + 1];
        for (int i = 1; i < dp.length; i = i + 2) {
                dp[i] = -prices[0];
        }
        for (int i = 1; i < prices.length; ++i) {
            for (int j = 1; j < dp.length; ++j) {
                if (j % 2 == 0){
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);
                }else{
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                }
            }
        }
        return dp[2 * k];
    }//1ms


}
