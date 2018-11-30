package pl.dmcs.reflection;

/**
 * Created by Dawid on 30.11.2018 at 18:14.
 */
public class Test implements Testable {
    private Integer priv = 20;
    public  Integer pub = 30;

    @Override
    public void test() {
        System.out.println("Test");
    }
}
