package dp.fibonacci;

public class FibonacciDynamicProgramming {
    public static void main(String[] args) {
        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }
    }

    private static int fib(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i - 2];
        }
        return dp[n];
    }
}
