package pl.dmcs;

import java.io.*;

public class Serializator {
    public static void serialize(Object object, String path) {
        try (
                FileOutputStream f = new FileOutputStream(new File(path));
                ObjectOutputStream o = new ObjectOutputStream(f)

        ) {
            o.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String pathname) {
        try (
                FileInputStream fi = new FileInputStream(new File(pathname));
                ObjectInputStream oi = new ObjectInputStream(fi)
        ) {
            return (T) oi.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
