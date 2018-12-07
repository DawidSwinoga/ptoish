package pl.dmcs.proxy;

import static pl.dmcs.proxy.DynamicProxy.createProxy;

/**
 * Created by Dawid on 07.12.2018 at 10:56.
 */
public class Main {
    public static void main(String[] args) {
        new Main().test();
    }

    private void test() {
        Runnable test1 = createProxy(new Test1(), "test", Runnable.class);
        test1.run();
        Runnable test2 = createProxy(new Test2(), "test", Runnable.class);
        test2.run();
    }
}
