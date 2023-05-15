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

    /**309. 最佳买卖股票时机含冷冻期*/
    public int maxProfitFrost(int[] prices) {
        int[] dp=new int[4];
        //四种状态，0手里有，1手里没有(之前卖出），2手里没有（今天卖出），3冷冻期
        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i <= prices.length; i++){
            // 使用临时变量来保存dp[0], dp[2]
            // 因为马上dp[0]和dp[2]的数据都会变
            int temp = dp[0];
            int temp1 = dp[2];
            //要么买过了，要么今天才买入(①前一天冷冻期，②前一天不是冷冻期但手中没有股票)
            dp[0] = Math.max(dp[0], Math.max(dp[3], dp[1]) - prices[i-1]);
            //要么昨天是冷冻期，要么昨天不是冷冻期
            dp[1] = Math.max(dp[1], dp[3]);
            //只能是今天卖出
            dp[2] = temp + prices[i-1];
            //只能是昨天卖出
            dp[3] = temp1;
        }
        //取三种的最大值
        return Math.max(dp[3],Math.max(dp[1],dp[2]));
    }

    /**714. 买卖股票的最佳时机含手续费*/
    public int maxProfitCommission(int[] prices, int fee) {
        if (prices.length < 2){
            return 0;
        }
        //0手里没有，1手里有
        /*
        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];*/
        //过啦！65%
        /**一维优化*/
        int[] dp = new int[2];
        dp[1] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i] - fee);
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
        }
        return dp[0];
        //过啦！75%
    }
}
