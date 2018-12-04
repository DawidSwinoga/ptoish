package pl.dmcs.cache;

import pl.dmcs.Benchmark;
import pl.dmcs.cache.provider.RemoveEldestElementCache;
import pl.dmcs.cache.provider.RemoveTheLeastUsedElementCache;

/**
 * Created by Dawid on 04.12.2018 at 22:14.
 */
public class Main {

    public static final int INVOKE_COUNT = 10000;

    public static void main(String[] args) {
        System.out.println("Remove eldest element cache");
        removeEldestElementCache();
        System.out.println("Remove the least used element cache");
        removeTheLeastUsedElementCache();
    }

    private static void removeTheLeastUsedElementCache() {
        MockedDB mockedDB = new MockedDB(new RemoveTheLeastUsedElementCache());
        benchmark(mockedDB);
    }


    private static void removeEldestElementCache() {
        MockedDB mockedDB = new MockedDB(new RemoveEldestElementCache());
        benchmark(mockedDB);
    }

    private static void benchmark(MockedDB mockedDB) {
        Benchmark.benchmark(mockedDB::getData, INVOKE_COUNT);
    }
}
