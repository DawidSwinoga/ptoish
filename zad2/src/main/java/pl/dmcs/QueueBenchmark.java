package pl.dmcs;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.Collections.shuffle;

public class QueueBenchmark {
    public static final int INVOKE_COUNT = 10;
    private static List<Integer> queueSizes = Arrays.asList(10, 100, 1000, 10000, 100000, 1000000, 1000000);
    private Supplier<Queue<String>> queueStringsSupplier;
    private List<String> data;
    private Queue<String> test;
    private List<String> temporaryData;

    public static void benchmark(Supplier<Queue<String>> queueStringsSupplier) {
        new QueueBenchmark(queueStringsSupplier).benchmark();
    }

    public QueueBenchmark(Supplier<Queue<String>> queueStringsSupplier) {
        this.queueStringsSupplier = queueStringsSupplier;
    }

    private void benchmark() {
        System.out.println("Benchmark --- " + queueStringsSupplier.get().getClass().getSimpleName() + " ---");
        generateData();
        System.out.println("Add at the beginning");
        queueSizes.forEach(this::benchmarkAddAtBeginning);
        System.out.println("Browsing using iterator");
        queueSizes.forEach(this::browsingUsingIterator);
        System.out.println("Remove");
        queueSizes.forEach(this::remove);
    }

    private void browsingUsingIterator(Integer size) {
        Benchmark.benchmark(this::browsingUsingIterator, INVOKE_COUNT, size);
    }

    private void browsingUsingIterator() {
        for (String text : test) {
        }
    }

    private void remove(Integer size) {
        shuffle(data);
        Benchmark.benchmark(this::doRemove, INVOKE_COUNT, size, () -> beforeRemoveTest(size));
    }

    private void beforeRemoveTest(Integer size) {
        shuffle(data);
        test = queueStringsSupplier.get();
        List<String> chosenData = data.subList(0, size);
        test.addAll(chosenData);
        temporaryData = new ArrayList<>();
        temporaryData.addAll(chosenData);
    }

    private void doRemove() {
        temporaryData.forEach(test::remove);
    }

    private void benchmarkAddAtBeginning(Integer size) {
        shuffle(data);
        Benchmark.benchmark(() -> benchmarkAdd(data.subList(0, size)), INVOKE_COUNT, size, () -> test = queueStringsSupplier.get());
    }

    private void benchmarkAdd(List<String> strings) {
        strings.forEach(test::add);
    }

    private void generateData() {
        Integer maxSetSize = queueSizes.stream().min(Comparator.reverseOrder()).orElse(0);
        data = LongStream.range(0L, maxSetSize).boxed().map(i -> getString()).collect(Collectors.toList());
    }

    private String getString() {
        return IntStream.range(0, 10).boxed().map(d -> UUID.randomUUID().toString()).collect(Collectors.joining());
    }
}
