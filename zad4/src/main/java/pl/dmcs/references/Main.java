package pl.dmcs.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Dawid on 07.12.2018 at 14:22.
 */
//-Xmx300m
public class Main {
    public static List<Reference> list = new ArrayList();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("stop");
        scanner.nextLine();
        for (int i = 0; i < 100000000; i++) {
            System.out.println(i);
//            list.add(weakReference());
//            list.add(softReference());
            list.add(phantomReference());
        }

    }

    private static Reference softReference() {
        return new SoftReference(generateData(), new ReferenceQueue());
    }

    private static Reference weakReference() {
        return new WeakReference(generateData(), new ReferenceQueue());
    }

    private static Reference phantomReference() {
        return new PhantomReference(generateData(), new ReferenceQueue());
    }

    private static String generateData() {
        return IntStream.range(0, 10000).boxed().map(t -> UUID.randomUUID().toString()).collect(Collectors.joining());
    }
}
