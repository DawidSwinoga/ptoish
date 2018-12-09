package pl.dmcs.memoryleak;

import java.io.BufferedReader;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

import static pl.dmcs.memoryleak.DataGenerator.randomData;

/**
 * Created by Dawid on 08.12.2018 at 15:58.
 */
public class LeakMain {


    public static void main(String[] args) {
//        staticCollectionMemoryLeak();
//        invalidHashCodeLeak();
        phantomReferenceLeak();

        new Scanner(System.in).nextLine();
    }

    private static void phantomReferenceLeak() {
        IntStream.range(0, 100).boxed()
                .map(it -> randomData())
                .map(it -> new PhantomReference(it, new ReferenceQueue()))
                .forEach(PhantomReferenceStore.references::add);
    }

    private static void staticCollectionMemoryLeak() {
        MemoryLeak.data.get(0);
    }

    private static void invalidHashCodeLeak() {
        Test test = null;
        for (int i = 0; i <100; i++) {
            test = new Test(randomData());
            InvalidHashCodeLeak.data.add(test);
            test.setData(randomData());
            InvalidHashCodeLeak.data.remove(test);
        }
    }
}
