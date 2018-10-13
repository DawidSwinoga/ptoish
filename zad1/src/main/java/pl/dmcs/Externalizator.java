package pl.dmcs;

import java.io.*;

public class Externalizator {

    public static void externalize(Externalizable externalizable, String path) {
        try (
                FileOutputStream f = new FileOutputStream(new File(path));
                ObjectOutputStream o = new ObjectOutputStream(f)

        ) {
            externalizable.writeExternal(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T deexternalize(Externalizable externalizable, String path) {
        try (
                FileInputStream fi = new FileInputStream(new File(path));
                ObjectInputStream oi = new ObjectInputStream(fi)
        ) {
            externalizable.readExternal(oi);
            return (T) externalizable;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
