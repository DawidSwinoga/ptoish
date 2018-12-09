package pl.dmcs.memoryleak;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static pl.dmcs.memoryleak.DataGenerator.randomData;

/**
 * Created by Dawid on 09.12.2018 at 13:50.
 */
public class MemoryLeak {
    public static final List<String> data = range(0, 100).boxed().map(it -> randomData()).collect(Collectors.toList());

}
