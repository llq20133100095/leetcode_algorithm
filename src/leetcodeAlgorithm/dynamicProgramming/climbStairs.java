package leetcodeAlgorithm.dynamicProgramming;

/***
 * 爬楼梯的动态规划:
 * 状态转移方程：
 * dp[n]=dp[n-1]+dp[n-2]
 *
 * 边界问题：
 * dp[1]=1
 * dp[2]=2
 */
public class climbStairs {

    public int n=5;
    public int[] dp=new int[n];

    public int DP(int[] dp,int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        dp[n]=DP(dp,n-1)+DP(dp,n-2);
        return dp[n];
    }

    public static void main(String[] args){
        climbStairs cs=new climbStairs();
        int result=cs.DP(cs.dp,4);
        System.out.println(result);
    }
}
