package pl.dmcs.optimization;

import java.util.Objects;

/**
 * Created by Dawid on 08.12.2018 at 16:07.
 */
public class StringMain {
    public static void main(String[] args) {
        System.out.println("new String(\"test\").equals(\"test\")");
        System.out.println(new String("test").equals("test"));
        System.out.println("new String(\"test\").intern() == \"test\"");
        System.out.println(new String("test").intern() == "test");
        System.out.println("new String(\"test\") == new String(\"test\")");
        System.out.println(new String("test") == new String("test"));
        System.out.println("\"test\" == \"test\"");
        System.out.println("test" == "test");
        System.out.println("\"test\" == \"te\" + \"st\"");
        System.out.println("test" == "te" + "st");
        System.out.println("Objects.equals(\"test\", new String(\"test\"))");
        System.out.println(Objects.equals("test", new String("test")));
        System.out.println("Objects.equals(null, \"test\")");
        System.out.println(Objects.equals(null, "test"));
        System.out.println("Objects.equals(null, null)");
        System.out.println(Objects.equals(null, null));
    }
}
