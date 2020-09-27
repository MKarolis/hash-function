package com.karolismed.hashfunction.hashing;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class HashingService {
    static final int BUCKET_SIZE = 512;
    static final int BUCKET_RESERVED_SIZE = 16;

    static final int BYTE_SIZE = 8;
    static final int BYTES_PER_BUCKET = (BUCKET_SIZE - BUCKET_RESERVED_SIZE) / BYTE_SIZE;

    public String hash(String input) {
        byte[] inputBytes =  input.getBytes(StandardCharsets.UTF_8);

        int bucketCount = (int)Math.ceil(inputBytes.length / (double) BYTES_PER_BUCKET);

        // TODO: Remove
//        BitSet bitSet = BitSet.valueOf(inputBytes);
        String binaryInput = strToBinary(input);
//        String binarySet = bitsetToBinary(bitSet);

        for (int i = 0; i < bucketCount; i++) {
            boolean[] bucket = getBucket(inputBytes, i);
            bucketToBinary(bucket);
        }

        // Convert to binary
        //  Generate blocks 512 bits in size
        //      Make 64 32-bit length words from the bucket
        //          Perform hashing on the words
        // Perform hashing on the blocks
        // Merge the results in hexadecimal, Use HashGenerator for that
        return input + "hashed";
    }

    private boolean[] getBucket(byte[] bytes, int bucketIndex) {
        boolean[] bucket = new boolean[BUCKET_SIZE];
        int positiveBitCount = 0;

        int bytePos = BYTES_PER_BUCKET * bucketIndex;
        int endingBytePos = Math.min(bytes.length, bytePos + BYTES_PER_BUCKET);

        // Populate the bucket with bytes converted to binary digits
        for (int i = 0; bytePos < endingBytePos; bytePos++) {
            byte value = bytes[bytePos];

            for (int b = 0; b < BYTE_SIZE; b++, i++) {
                if ((value & 128) > 0) {
                    bucket[i] = true;
                    positiveBitCount++;
                }
                value <<= 1;
            }
        }
        // Populate ending of the bucket with count of positive bits
        for (int i = 1; positiveBitCount > 0; i++) {
            if ((positiveBitCount & 1) > 0) {
                bucket[BUCKET_SIZE - i] = true;
            }
            positiveBitCount >>= 1;
        }

        return bucket;
    }

    private String bucketToBinary(boolean[] bucket) {
        StringBuilder binary = new StringBuilder();
        for(int i = 0; i < BUCKET_SIZE; i++) {
            binary.append(bucket[i] ? "1" : "0");
        }
        return binary.toString();
    }

    private String strToBinary(String str) {
        byte[] bytes = str.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
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
