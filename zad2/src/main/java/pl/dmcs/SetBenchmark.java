package pl.dmcs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.Collections.shuffle;

/**
 * Created by Dawid on 23.10.2018 at 21:34.
 */
public class SetBenchmark {
    public static final int INVOKE_COUNT = 50;
    private static List<Integer> setSizes = Arrays.asList(10, 100, 1000, 10000, 100000, 1000000, 1000000);
    private Supplier<Set<String>> stingSetSupplier;
    private List<String> data;
    private Set<String> test;

    public static void benchmark(Supplier<Set<String>> stingSetSupplier) {
        new SetBenchmark(stingSetSupplier).benchmark();
    }

    public SetBenchmark(Supplier<Set<String>> stingSetSupplier) {
        this.stingSetSupplier = stingSetSupplier;
    }

    private void benchmark() {
        System.out.println("Benchmark --- " + stingSetSupplier.get().getClass().getSimpleName() + " ---");
        generateData();
        System.out.println("Add");
        setSizes.forEach(this::benchmarkAdd);
        System.out.println("Remove");
        setSizes.forEach(this::remove);
    }

    private void remove(Integer size) {
        shuffle(data);
        Benchmark.benchmark(() -> doRemove(size), INVOKE_COUNT, size);
    }

    private void doRemove(Integer size) {
        data.re
    }

    private void benchmarkAdd(Integer size) {
        shuffle(data);
        Benchmark.benchmark(() -> benchmarkAdd(data.subList(0, size)), INVOKE_COUNT, size);
    }

    private void benchmarkAdd(List<String> strings) {
        test = stingSetSupplier.get();
        strings.forEach(test::add);
    }

    private void generateData() {
        Integer maxSetSize = setSizes.stream().min(Comparator.reverseOrder()).orElse(0);
        data = LongStream.range(0L, maxSetSize).boxed().map(i -> getString()).collect(Collectors.toList());
    }

    private String getString() {
        return IntStream.range(0, 10).boxed().map(d -> UUID.randomUUID().toString()).collect(Collectors.joining());
    }
}
