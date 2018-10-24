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
        benchmark(executable, invokeCount, 1);
    }

    private static void displayResult(Instant start, Instant stop, long invokeCount, int timeDivider) {
        long duration = Duration.between(start, stop).toNanos();
        double avgDuration = duration / (double) invokeCount;
        System.out.println("AVG execution time: " + (avgDuration / timeDivider) + " nanoseconds");
    }

    public static void benchmark(Executable executable, long invokeCount, int timeDivider) {
        long executionCount = invokeCount < MIN_INVOKE_COUNT ? 1 : invokeCount;

        Instant start = Instant.now();
        for (int i = 0; i < executionCount; i++) {
            executable.execute();
        }
        Instant stop = Instant.now();

        displayResult(start, stop, invokeCount, timeDivider);
    }
}
