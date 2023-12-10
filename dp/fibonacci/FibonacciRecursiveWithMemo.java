package dp.fibonacci;

public class FibonacciRecursiveWithMemo extends Fibonacci{

    private long[] memo;

    public long fib(int n) {
        if (n < 0) return 0;
        memo = new long[n + 1];
        return fibHelper(n);
    }

    private long fibHelper(int n) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fibHelper(n - 1) + fibHelper(n - 2);
        return memo[n];
    }
}
