package pl.dmcs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by Dawid on 30.11.2018 at 17:28.
 */
public class ListBenchmark {
    public static final int INVOKE_COUNT = 10;
    private static List<Integer> listSizes = Arrays.asList(10, 100, 1000, 10000, 100000);
    private Supplier<List<String>> stringListSupplier;
    private List<String> data;
    private List<String> test;
    private List<String> temporaryData;

    public static void benchmark(Supplier<List<String>> stingListSupplier) {
        new ListBenchmark(stingListSupplier).benchmark();
    }

    public ListBenchmark(Supplier<List<String>> stingListSupplier) {
        this.stringListSupplier = stingListSupplier;
    }

    private void benchmark() {
        System.out.println("Benchmark --- " + stringListSupplier.get().getClass().getSimpleName() + " ---");
        generateData();
        System.out.println("Adding at the beginning");
        listSizes.forEach(this::benchmarkAddingAtTheBeginning);
        System.out.println("Adding at he end");
        listSizes.forEach(this::benchmarkAddAtTheEnd);
        System.out.println("Adding at random place");
        listSizes.forEach(this::addAtRandomPlaceBenchmark);
        System.out.println("Removing from beginning");
        listSizes.forEach(this::removeFromBeginning);
        System.out.println("Removing from end");
        listSizes.forEach(this::removeFromEnd);
        System.out.println("Removing from random place");
        listSizes.forEach(this::removeFromRandomPlace);
        System.out.println("Browsing using indexes");
        listSizes.forEach(this::browseUsingIndexes);
        System.out.println("Browsing using iterator");
        listSizes.forEach(this::browseUsingIterator);
    }

    private void browseUsingIterator(Integer size) {
        Benchmark.benchmark(this::doBrowseUsingIterator, INVOKE_COUNT, size, () -> beforeBrowseTest(size));
    }

    private void doBrowseUsingIterator() {
        for (String t : test) {

        }
    }

    private void removeFromRandomPlace(Integer size) {
        Benchmark.benchmark(this::doRemoveFromRandomPlace, INVOKE_COUNT, size, () -> beforeRemoveTest(size));
    }

    private void doRemoveFromRandomPlace() {
        temporaryData.forEach(it -> test.remove(test.size() == 0 ? 0 : new Random().nextInt(test.size())));
    }

    private void removeFromEnd(Integer size) {
        Benchmark.benchmark(this::doRemoveFromBeginning, INVOKE_COUNT, size, () -> beforeRemoveTest(size));
    }

    private void addAtRandomPlaceBenchmark(Integer size) {
        Benchmark.benchmark(() -> benchmarkAddingAtTheRandomPlace(data.subList(0, size)), INVOKE_COUNT, size, () -> test = stringListSupplier.get());
    }

    private void benchmarkAddingAtTheRandomPlace(List<String> strings) {
        strings.forEach(it -> test.add(test.size() == 0 ? 0 : new Random().nextInt(test.size()), it));
    }

    private void benchmarkAddAtTheEnd(Integer size) {
        Benchmark.benchmark(() -> benchmarkAddingAtTheEnd(data.subList(0, size)), INVOKE_COUNT, size, () -> test = stringListSupplier.get());
    }

    private void benchmarkAddingAtTheEnd(List<String> strings) {
        strings.forEach(it -> test.add(test.size() == 0 ? 0 :test.size() - 1, it));
    }

    private void browseUsingIndexes(Integer size) {
        Benchmark.benchmark(this::doBrowseUsingIndexes, INVOKE_COUNT, size, () -> beforeBrowseTest(size));
    }

    private void beforeBrowseTest(Integer size) {
        test = stringListSupplier.get();
        List<String> data = this.data.subList(0, size);
        test.addAll(data);
    }

    private void doBrowseUsingIndexes() {
        for (int i = 0; i < test.size(); i++) {
            test.get(i);
        }
    }

    private void removeFromBeginning(Integer size) {
        Benchmark.benchmark(this::doRemoveFromEnd, INVOKE_COUNT, size, () -> beforeRemoveTest(size));
    }

    private void doRemoveFromEnd() {
        temporaryData.forEach(it -> test.remove(test.size() == 0 ? 0 :test.size() - 1));
    }

    private void beforeRemoveTest(Integer size) {
        test = stringListSupplier.get();
        List<String> chosenData = data.subList(0, size);
        test.addAll(chosenData);
        temporaryData = new ArrayList<>();
        temporaryData.addAll(chosenData);
    }

    private void doRemoveFromBeginning() {
        temporaryData.forEach(it -> test.remove(0));
    }

    private void benchmarkAddingAtTheBeginning(Integer size) {
        Benchmark.benchmark(() -> benchmarkAddingAtTheBeginning(data.subList(0, size)), INVOKE_COUNT, size, () -> test = stringListSupplier.get());
    }

    private void benchmarkAddingAtTheBeginning(List<String> strings) {
        strings.forEach(it -> test.add(0, it));
    }

    private void generateData() {
        Integer maxSetSize = listSizes.stream().min(Comparator.reverseOrder()).orElse(0);
        data = LongStream.range(0L, maxSetSize).boxed().map(i -> getString()).collect(Collectors.toList());
    }

    private String getString() {
        return IntStream.range(0, 10).boxed().map(d -> UUID.randomUUID().toString()).collect(Collectors.joining());
    }
}
