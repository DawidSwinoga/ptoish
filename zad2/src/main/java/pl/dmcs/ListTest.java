package pl.dmcs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by Dawid on 30.11.2018 at 17:28.
 */
public class ListTest {
    public static void main(String[] args) {
        ListBenchmark.benchmark(ArrayList::new);
        ListBenchmark.benchmark(LinkedList::new);
        ListBenchmark.benchmark(Stack::new);
        ListBenchmark.benchmark(Vector::new);
    }
}
