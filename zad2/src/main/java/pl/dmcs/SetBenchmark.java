package pl.dmcs;

import java.util.*;
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
    private List<String> dataToRemove;

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
        Benchmark.benchmark(() -> doRemove(), INVOKE_COUNT, size, () -> beforeRemoveTest(size));
    }

    private void beforeRemoveTest(Integer size) {
        shuffle(data);
        test = stingSetSupplier.get();
        List<String> chosenData = data.subList(0, size);
        test.addAll(chosenData);
        shuffle(chosenData);
        dataToRemove = new ArrayList<>();
        dataToRemove.addAll(chosenData);
    }

    private void doRemove() {
        dataToRemove.forEach(test::remove);
    }

    private void benchmarkAdd(Integer size) {
        shuffle(data);
        Benchmark.benchmark(() -> benchmarkAdd(data.subList(0, size)), INVOKE_COUNT, size, () -> test = stingSetSupplier.get());
    }

    private void benchmarkAdd(List<String> strings) {
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
