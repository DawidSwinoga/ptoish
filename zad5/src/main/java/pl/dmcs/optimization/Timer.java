package pl.dmcs.optimization;

/**
 * Created by Dawid on 08.12.2018 at 15:22.
 */
public class Timer {
    private final long startTime;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public void print(String text) {
        long duration = System.currentTimeMillis() - startTime;
        System.out.println(text + ": " + duration + " millis");
    }
}
