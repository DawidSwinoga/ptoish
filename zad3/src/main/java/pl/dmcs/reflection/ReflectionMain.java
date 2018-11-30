package pl.dmcs.reflection;

import java.lang.reflect.Field;

/**
 * Created by Dawid on 30.11.2018 at 18:21.
 */
public class ReflectionMain {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.getClass().getMethod("test").invoke(test);
        Field priv = test.getClass().getDeclaredField("priv");
        priv.setAccessible(true);
        System.out.println("private field: " + priv.get(test));
        System.out.println("public field: " + test.getClass().getField("pub").get(test));
    }
}
