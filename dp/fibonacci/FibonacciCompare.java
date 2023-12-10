package dp.fibonacci;

public class FibonacciCompare {
    public static void main(String[] args){
        Fibonacci[] algorithms = {
            //new FibonacciRecursive(),
            new FibonacciRecursiveWithMap(),
            new FibonacciDynamicProgramming(),
            new FibonacciRecursiveWithMemo(),
            new FibonacciIterative(),
        };
        for (Fibonacci algorithm : algorithms) {
            // saving the start time
            long startTime = System.nanoTime();
            System.out.println(algorithm.getClass().getSimpleName());
            testFib(algorithm);
            // saving the end time
            long endTime = System.nanoTime();
            // printing the time difference
            System.out.println("Time: " + (endTime - startTime) + " ns");
        }
    }

      // some unit tests for the fib function
    private static void testFib(Fibonacci fibAlgorithm){
        assert fibAlgorithm.fib(0) == 0;
        assert fibAlgorithm.fib(1) == 1;
        assert fibAlgorithm.fib(2) == 1;
        assert fibAlgorithm.fib(3) == 2;
        assert fibAlgorithm.fib(4) == 3;
        assert fibAlgorithm.fib(5) == 5;
        assert fibAlgorithm.fib(6) == 8;
        assert fibAlgorithm.fib(7) == 13;
        assert fibAlgorithm.fib(8) == 21;
        assert fibAlgorithm.fib(9) == 34;
        assert fibAlgorithm.fib(10) == 55;
        assert fibAlgorithm.fib(11) == 89;
        assert fibAlgorithm.fib(12) == 144;
        assert fibAlgorithm.fib(13) == 233;
        assert fibAlgorithm.fib(14) == 377;
        assert fibAlgorithm.fib(15) == 610;
        assert fibAlgorithm.fib(16) == 987;
        assert fibAlgorithm.fib(17) == 1597;
        assert fibAlgorithm.fib(18) == 2584;
        assert fibAlgorithm.fib(19) == 4181;
        assert fibAlgorithm.fib(20) == 6765;
        assert fibAlgorithm.fib(21) == 10946;
        assert fibAlgorithm.fib(22) == 17711;
        assert fibAlgorithm.fib(23) == 28657;
        assert fibAlgorithm.fib(24) == 46368;
        assert fibAlgorithm.fib(25) == 75025;
        assert fibAlgorithm.fib(26) == 121393;
        assert fibAlgorithm.fib(27) == 196418;
        assert fibAlgorithm.fib(28) == 317811;
        assert fibAlgorithm.fib(29) == 514229;
        assert fibAlgorithm.fib(30) == 832040;
        assert fibAlgorithm.fib(31) == 1346269;
        assert fibAlgorithm.fib(32) == 2178309;
        assert fibAlgorithm.fib(33) == 3524578;
        assert fibAlgorithm.fib(34) == 5702887;
        assert fibAlgorithm.fib(35) == 9227465;
        assert fibAlgorithm.fib(36) == 14930352;
        assert fibAlgorithm.fib(37) == 24157817;
        assert fibAlgorithm.fib(38) == 39088169;
        assert fibAlgorithm.fib(39) == 63245986;
        assert fibAlgorithm.fib(40) == 102334155;
        assert fibAlgorithm.fib(41) == 165580141;
        assert fibAlgorithm.fib(42) == 267914296;
        assert fibAlgorithm.fib(43) == 433494437;
        assert fibAlgorithm.fib(44) == 701408733;
        assert fibAlgorithm.fib(45) == 1134903170;
        assert fibAlgorithm.fib(46) == 1836311903;
        assert fibAlgorithm.fib(47) == 2971215073L;
        assert fibAlgorithm.fib(48) == 4807526976L;
        assert fibAlgorithm.fib(49) == 7778742049L;
        assert fibAlgorithm.fib(50) == 12586269025L;
        assert fibAlgorithm.fib(51) == 20365011074L;
        assert fibAlgorithm.fib(52) == 32951280099L;
        assert fibAlgorithm.fib(53) == 53316291173L;
        assert fibAlgorithm.fib(54) == 86267571272L;
        assert fibAlgorithm.fib(55) == 139583862445L;
        assert fibAlgorithm.fib(56) == 225851433717L;
        assert fibAlgorithm.fib(57) == 365435296162L;
        assert fibAlgorithm.fib(58) == 591286729879L;
        assert fibAlgorithm.fib(59) == 956722026041L;
        assert fibAlgorithm.fib(60) == 1548008755920L;
        assert fibAlgorithm.fib(61) == 2504730781961L;
        assert fibAlgorithm.fib(62) == 4052739537881L;
        assert fibAlgorithm.fib(63) == 6557470319842L;
        assert fibAlgorithm.fib(64) == 10610209857723L;
        assert fibAlgorithm.fib(65) == 17167680177565L;
        assert fibAlgorithm.fib(66) == 27777890035288L;
        assert fibAlgorithm.fib(67) == 44945570212853L;
        assert fibAlgorithm.fib(68) == 72723460248141L;
        assert fibAlgorithm.fib(69) == 117669030460994L;
        assert fibAlgorithm.fib(70) == 190392490709135L;
        assert fibAlgorithm.fib(71) == 308061521170129L;
        assert fibAlgorithm.fib(72) == 498454011879264L;
        assert fibAlgorithm.fib(73) == 806515533049393L;
        assert fibAlgorithm.fib(74) == 1304969544928657L;
        assert fibAlgorithm.fib(75) == 2111485077978050L;

    }
}
