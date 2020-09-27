package com.karolismed.hashfunction.hashing;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class HashingService {
    private static final int BUCKET_SIZE = 512;
    private static final int BUCKET_RESERVED_SIZE = 16;

    private static final int BYTE_SIZE = 8;
    private static final int BYTES_PER_BUCKET = (BUCKET_SIZE - BUCKET_RESERVED_SIZE) / BYTE_SIZE;

    public String hash(String input) {
        byte[] inputBytes =  input.getBytes(StandardCharsets.UTF_8);

        int bucketCount = (int)Math.ceil(BYTES_PER_BUCKET / (double)inputBytes.length);

        for (int i = 0; i < bucketCount; i++) {
            boolean[] bucket = new boolean[BUCKET_SIZE];
        }

        BitSet bitSet = BitSet.valueOf(inputBytes);
        String binaryInput = strToBinary(input);
        String binarySet = bitsetToBinary(bitSet);

        // Convert to binary
        //  Generate blocks 512 bits in size
        //      Make 64 32-bit length words from the bucket
        //          Perform hashing on the words
        // Perform hashing on the blocks
        // Merge the results in hexadecimal
        return input + "hashed";
    }

    private String strToBinary(String str) {
        byte[] bytes = str.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return binary.toString();
    }

    private String bitsetToBinary(BitSet set) {
        StringBuilder binary = new StringBuilder();
        for(int i = 0; i < set.length(); i++) {
            if (i % 8 == 0) binary.append(" ");
            binary.append(set.get(i) ? "1" : "0");
        }
        return binary.toString();
    }
}
