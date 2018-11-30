package pl.dmcs;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import static pl.dmcs.SetBenchmark.benchmark;

public class SetTest {
    public static void main(String[] args) {
        benchmark(HashSet::new);
        benchmark(TreeSet::new);
        benchmark(LinkedHashSet::new);
    }
}
