package pl.dmcs;

import java.io.BufferedReader;
import java.io.FileReader;

import static pl.dmcs.FileGenerator.generateFile;

/**
 * Hello world!
 */
public class App {

    private static final String FILE_PATH = "zad1.2/file.bin";

    public static void main(String[] args) {
        new App().runBenchmark();
    }

    private void runBenchmark() {
        generateFile(FILE_PATH);
        Benchmark.benchmark(null);
//        benchmark(this::bufferedReader);
    }

    public void bufferedReader() {
        try (
                FileReader fr = new FileReader(FILE_PATH);
                BufferedReader br = new BufferedReader(fr)) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
