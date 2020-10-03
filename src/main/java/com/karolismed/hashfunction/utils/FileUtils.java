package com.karolismed.hashfunction.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class FileUtils {

    public String readStringFromClasspath(String fileName) {
        String data = "";

        try (InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)) {
            data = new String(in.readAllBytes());
        } catch (IOException ex) {
            System.out.println("Error while reading data from file " + fileName);
        }

        return data;
    }

    public void generateTestDataPairs(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        outputPairsOfLength(printWriter, 25_000, 10);
        outputPairsOfLength(printWriter, 25_000, 100);
        outputPairsOfLength(printWriter, 25_000, 500);
        outputPairsOfLength(printWriter, 25_000, 1000);

        printWriter.close();
    }

    public void generateTestDataPairsDiffInOneSymbol(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 10);
        outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 100);
        outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 500);
        outputPairsOfLengthDiffInOneSymbol(printWriter, 25_000, 1000);

        printWriter.close();
    }

    private void outputPairsOfLength(PrintWriter writer, int count, int length) {
        for (int i = 0; i < count; i++) {
            writer.println(String.format("%s %s", generateString(length), generateString(length)));
        }
    }

    private void outputPairsOfLengthDiffInOneSymbol(PrintWriter writer, int count, int length) {
        for (int i = 0; i < count; i++) {
            String str = generateString(length);

            byte[] bytes = str.getBytes();
            bytes[i % length]++;

            writer.println(String.format("%s %s", str, new String(bytes)));
        }
    }

    private String generateString(int length) {
        return RandomStringUtils.random(length, true, true);
    }
}
