package pl.dmcs;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SetBenchmark.benchmark(HashSet::new);
        SetBenchmark.benchmark(TreeSet::new);
        SetBenchmark.benchmark(LinkedHashSet::new);
    }
}
