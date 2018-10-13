package pl.dmcs;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * Created by Dawid on 13.10.2018 at 20:26.
 */
public class FileGenerator {
    public static void generateFile(String path) {
        byte[] bytes = new byte[500000000];
        new Random().nextBytes(bytes);

        try (RandomAccessFile stream = new RandomAccessFile(path, "rw");
             FileChannel out = stream.getChannel()) {
            out.write(ByteBuffer.wrap(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
