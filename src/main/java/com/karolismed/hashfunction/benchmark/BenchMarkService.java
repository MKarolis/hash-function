package com.karolismed.hashfunction.benchmark;

import com.karolismed.hashfunction.constants.ResourceFilename;
import com.karolismed.hashfunction.utils.FileUtils;
import com.karolismed.hashfunction.utils.StringHelper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class BenchMarkService {

    private FileUtils fileUtils;

    public BenchMarkService() {
        fileUtils = new FileUtils();
    }

    public void benchmarkHashFunction() {
        prepareBenchmarkData();

        cleanupTestData();
    }

    private void prepareBenchmarkData() {
        try {
            PrintWriter printWriter =
                new PrintWriter(ResourceFilename.PAIRS_100K.toString());

            outputPairsOfLength(printWriter, 25_000, 10);
            outputPairsOfLength(printWriter, 25_000, 100);
            outputPairsOfLength(printWriter, 25_000, 500);
            outputPairsOfLength(printWriter, 25_000, 1000);

            printWriter.close();

            printWriter =
                new PrintWriter(ResourceFilename.PAIRS_100K_DIFF_ONE_SYMBOL.toString());

            outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 10);
            outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 100);
            outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 500);
            outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 1000);

            printWriter.close();

        } catch (IOException e) {
            System.out.println("ERROR: Benchmark file preparation failed, " + e.getMessage());
            System.exit(1);
        }
    }

    private void outputPairsOfLength(PrintWriter writer, int count, int length) {
        for (int i = 0; i < count; i++) {
            writer.println(String.format(
                "%s %s", StringHelper.generateString(length), StringHelper.generateString(length))
            );
        }
    }

    private void outputPairsOfLengthDiffInOneSymbol(PrintWriter writer, int count, int length) {
        for (int i = 0; i < count; i++) {
            String str = StringHelper.generateString(length);

            writer.println(String.format(
                "%s %s", str, StringHelper.incrementCharInString(str, i % length))
            );
        }
    }

    private void cleanupTestData() {
        File file;
        for (ResourceFilename filename : ResourceFilename.values()) {
            file = new File(filename.toString());
            if(!file.delete()) {
                System.out.println("WARNING: Failed to delete test file " + filename);
            }
        }
    }

    private double stringDiffPercentage(String base, String test) {
        if (base.length() != test.length()) {
            throw new RuntimeException(String.format(
                "ERROR: Strings %s and %s are of different lengths", base, test)
            );
        }
        byte[] baseBytes = base.getBytes();
        byte[] testBytes = test.getBytes();
        int sameChars = 0;
        for (int i = 0; i < baseBytes.length; i++) {
            if (baseBytes[i] == testBytes[i]) sameChars++;
        }

        return 100.00 - (sameChars / (double)baseBytes.length) * 100.0;
    }

    private double binaryDiffPercentage(String base, String test) {
        if (base.length() != test.length()) {
            throw new RuntimeException(String.format(
                "ERROR: Strings %s and %s are of different lengths", base, test)
            );
        }
        byte[] baseBytes = base.getBytes();
        byte[] testBytes = test.getBytes();

        int sameChars = 0;
        for (int i = 0; i < baseBytes.length; i++) {
            sameChars += Integer.bitCount(baseBytes[i] & testBytes[i]);
            sameChars += Integer.bitCount((~baseBytes[i] & 0xFF) & (~testBytes[i] & 0xFF));
        }

        return 100.0 - (sameChars / ((double)baseBytes.length * Byte.SIZE)) * 100.0;
    }
}
