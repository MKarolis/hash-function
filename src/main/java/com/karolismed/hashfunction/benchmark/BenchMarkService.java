package com.karolismed.hashfunction.benchmark;

import com.karolismed.hashfunction.constants.ResourceFilename;
import com.karolismed.hashfunction.hashing.HashingService;
import com.karolismed.hashfunction.utils.FileUtils;
import com.karolismed.hashfunction.utils.StringHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

import static java.util.Objects.requireNonNull;

public class BenchMarkService {

    private FileUtils fileUtils;
    private HashingService hashingService;

    public BenchMarkService() {
        fileUtils = new FileUtils();
        hashingService = new HashingService();
    }

    public void benchmarkHashFunction() {
        System.out.println("Preparing benchmarking input data...");
        prepareBenchmarkData();

        benchmarkConstitutionHash();
        benchmarkCollisions();

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

    private void benchmarkConstitutionHash() {
        StopWatch stopWatch = new StopWatch();
        System.out.println(
            String.format("Starting benchmarking of file %s...", ResourceFilename.CONSTITUTION)
        );

        try {
            InputStream inputStream =
                ClassLoader.getSystemResourceAsStream(ResourceFilename.CONSTITUTION.toString());

            List<String> inputLines = IOUtils.readLines(
                requireNonNull(inputStream),
                StandardCharsets.UTF_8
            );

            stopWatch.start();;
            inputLines.forEach(hashingService::hash);
            stopWatch.stop();
        } catch (IOException e) {
            System.out.println(
                String.format(
                    "ERROR: benchmarking of file %s failed, %s",
                    ResourceFilename.CONSTITUTION,
                    e.getMessage()
                )
            );
            return;
        }

        System.out.println(String.format("Hashing took %sms", stopWatch.getTime(TimeUnit.MILLISECONDS)));
    }

    private void benchmarkCollisions() {
        System.out.println("Looking for collisions among 100 000 String pairs...");
        int collisions = 0;

        try (BufferedReader reader = new BufferedReader(
            new FileReader(ResourceFilename.PAIRS_100K.toString()))
        ) {
            for (String line; (line = reader.readLine()) != null; ) {
                if (hashingService.hash(line.split(" ")[0])
                    .equals(hashingService.hash(line.split(" ")[1]))) {
                    collisions++;
                }
            }
        } catch (IOException e) {
            System.out.println(
                String.format(
                    "ERROR: benchmarking of file %s failed, %s",
                    ResourceFilename.PAIRS_100K,
                    e.getMessage()
                )
            );
            return;
        }

        System.out.println(String.format("Found %s collision among 100 000 pairs", collisions));
    }

    private void cleanupTestData() {
        File file;
        ResourceFilename[] resourcesToClean = {
            ResourceFilename.PAIRS_100K_DIFF_ONE_SYMBOL,
            ResourceFilename.PAIRS_100K
        };

        for (ResourceFilename filename : resourcesToClean) {
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
