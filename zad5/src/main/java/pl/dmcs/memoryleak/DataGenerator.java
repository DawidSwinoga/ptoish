package pl.dmcs.memoryleak;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * Created by Dawid on 09.12.2018 at 15:01.
 */
public class DataGenerator {
    public static String randomData() {
        return range(0, 100000).boxed().map(it -> randomUUID().toString()).collect(joining());
    }
}
