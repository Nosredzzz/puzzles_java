package dp.fibonacci;

public class FibonacciIterative extends Fibonacci {
    public long fib(int n) {
        if (n <= 1) return n;
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
