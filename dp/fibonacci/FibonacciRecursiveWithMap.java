package dp.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciRecursiveWithMap extends Fibonacci{

    private Map<Integer, Long> memo = new HashMap<>();
    public long fib(int n) {
        if (n < 0) return 0;
        if (n <= 1) return n;
        // verify if the value is already in the map
        if (memo.containsKey(n)) return memo.get(n);
        // if not, calculate it
        memo.put(n, fib(n - 1) + fib(n - 2));
        // and return it
        return memo.get(n);
    }
}
