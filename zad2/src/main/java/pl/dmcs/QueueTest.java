package pl.dmcs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;

import static pl.dmcs.QueueBenchmark.benchmark;

public class QueueTest {
    public static void main(String[] args) {
        benchmark(ArrayDeque::new);
        benchmark(LinkedList::new);
        benchmark(PriorityQueue::new);
    }
}
