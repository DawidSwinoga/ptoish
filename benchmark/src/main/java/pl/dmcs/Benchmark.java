package pl.dmcs;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by Dawid on 13.10.2018 at 22:46.
 */
public class Benchmark {
    public static final int MIN_INVOKE_COUNT = 1;
    private final static long DEFAULT_INVOKE_COUNT = 100L;

    public static void benchmark(Executable executable) {
        benchmark(executable, DEFAULT_INVOKE_COUNT);
    }

    public static void benchmark(Executable executable, long invokeCount) {
        benchmark(executable, invokeCount, 1, () -> {});
    }

    private static void displayResult(double avgDuration) {

        System.out.println("AVG execution time: " + (avgDuration) + " nanoseconds");
    }

    public static void benchmark(Executable executable, long invokeCount, int timeDivider) {
        benchmark(executable, invokeCount, timeDivider, () -> {

        });
    }

    public static void benchmark(Executable executable, long invokeCount, int timeDivider, Runnable beforeTest) {
        long executionCount = invokeCount < MIN_INVOKE_COUNT ? 1 : invokeCount;

        long skipTime = 0;
        long start = System.nanoTime();
        for (int i = 0; i < executionCount; i++) {
            long skipTimeStart = System.nanoTime();
            beforeTest.run();
            skipTime = skipTime + (System.nanoTime() - skipTimeStart);
            executable.execute();
        }
        long stop = System.nanoTime();

        long duration = stop - start;
        double avgDuration = (((duration - skipTime)/ (double) invokeCount) / timeDivider);
        displayResult(avgDuration);
    }
}
