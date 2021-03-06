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
    private Supplier<Set<String>> stringSetSupplier;
    private List<String> data;
    private Set<String> test;
    private List<String> temporaryData;

    public static void benchmark(Supplier<Set<String>> stingSetSupplier) {
        new SetBenchmark(stingSetSupplier).benchmark();
    }

    public SetBenchmark(Supplier<Set<String>> stingSetSupplier) {
        this.stringSetSupplier = stingSetSupplier;
    }

    private void benchmark() {
        System.out.println("Benchmark --- " + stringSetSupplier.get().getClass().getSimpleName() + " ---");
        generateData();
        System.out.println("Adding at the beginning");
        setSizes.forEach(this::benchmarkAddingAtTheBeginning);
        System.out.println("Remove");
        setSizes.forEach(this::remove);
        System.out.println("Search / Check if element exist");
        setSizes.forEach(this::search);
    }

    private void search(Integer size) {
        Benchmark.benchmark(this::doSearch, INVOKE_COUNT, size, () -> beforeSearchTest(size));
    }

    private void beforeSearchTest(Integer size) {
        shuffle(data);
        test = stringSetSupplier.get();
        List<String> data = this.data.subList(0, size);
        test.addAll(data);
        temporaryData = data;
        shuffle(temporaryData);
    }

    private void doSearch() {
        temporaryData.forEach(test::contains);
    }

    private void remove(Integer size) {
        shuffle(data);
        Benchmark.benchmark(this::doRemove, INVOKE_COUNT, size, () -> beforeRemoveTest(size));
    }

    private void beforeRemoveTest(Integer size) {
        shuffle(data);
        test = stringSetSupplier.get();
        List<String> chosenData = data.subList(0, size);
        test.addAll(chosenData);
        shuffle(chosenData);
        temporaryData = new ArrayList<>();
        temporaryData.addAll(chosenData);
    }

    private void doRemove() {
        temporaryData.forEach(test::remove);
    }

    private void benchmarkAddingAtTheBeginning(Integer size) {
        shuffle(data);
        Benchmark.benchmark(() -> benchmarkAdd(data.subList(0, size)), INVOKE_COUNT, size, () -> test = stringSetSupplier.get());
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
