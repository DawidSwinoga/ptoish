package pl.dmcs.cache;

import pl.dmcs.cache.provider.Cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Dawid on 04.12.2018 at 22:22.
 */
public class MockedDB {
    private List<String> stringsData;
    private Cache cache;
    private Random random;

    public MockedDB(Cache cache) {
        this.cache = cache;
        random = new Random();
        generateData();
    }

    private void generateData() {
        List<String> data = IntStream.range(0, 120).boxed().map(it -> UUID.randomUUID().toString()).collect(Collectors.toList());
        List<String> strings = data.subList(0, 40);
        List<String> strings2 = data.subList(0, 40);
        this.stringsData = new ArrayList<>(data);
        stringsData.addAll(strings);
        stringsData.addAll(strings2);
    }

    public String getData() {
        String data = stringsData.get(getRandomIndex());
        String value = cache.get(data);

        if (value != null) {
            return value;
        } else {
            return readFromDb(data);
        }
    }

    private String readFromDb(String data) {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        cache.putData(data, data);
        return data;
    }

    private int getRandomIndex() {
        return random.nextInt(stringsData.size() - 1);
    }
}
