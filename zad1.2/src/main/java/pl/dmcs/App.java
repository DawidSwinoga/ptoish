package pl.dmcs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static pl.dmcs.Benchmark.benchmark;
import static pl.dmcs.FileGenerator.generateFile;

/**
 * Hello world!
 */
public class App {

    private static final String FILE_PATH = "zad1.2/file2.bin";
    private static final int INVOKE_COUNT = 1;
    private static final int BUFFER_SIZE = 1024 * 1024;

    public static void main(String[] args) {
        new App().runBenchmark();
    }

    private void runBenchmark() {
        generateFile(FILE_PATH);
        System.out.println("BufferedReader-------------------------------------------------------------");
        benchmark(this::bufferedReader, INVOKE_COUNT);
        System.out.println("NIO library----------------------------------------------------------------");
        benchmark(this::nioLibrary, INVOKE_COUNT);
        System.out.println("Mapped file----------------------------------------------------------------");
        benchmark(this::mappedFile, INVOKE_COUNT);
    }

    private void mappedFile() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_PATH, "r")) {

            MappedByteBuffer mappedByteBuffer = randomAccessFile.getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, randomAccessFile.length());
            int length = (int) randomAccessFile.length();
            byte[] bytes = new byte[length];
            mappedByteBuffer.get(bytes, 0, length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void nioLibrary() {
        byte[] bytes = new byte[BUFFER_SIZE];
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try (RandomAccessFile stream = new RandomAccessFile(FILE_PATH, "r");
             FileChannel in = stream.getChannel()) {
            while (in.read(buffer) != -1) {
                buffer.clear();
                buffer.get(bytes, 0, buffer.remaining());
                buffer.clear();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bufferedReader() {
        try (
                FileReader fr = new FileReader(FILE_PATH);
                BufferedReader br = new BufferedReader(fr)) {

            while ((br.readLine()) != null) {
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
