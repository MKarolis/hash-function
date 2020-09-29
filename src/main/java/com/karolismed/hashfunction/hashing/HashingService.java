package com.karolismed.hashfunction.hashing;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class HashingService {
    static final int BUCKET_SIZE = 64; // bytes
    static final int FREE_BUCKET_SIZE = 60; // bytes

    static final int BYTES_PER_BUCKET = BUCKET_SIZE / Byte.SIZE; // 64

    static final int[] HASH_SEED = {
            0b11000011011111010011011000110000,
            0b10011010101100100000110010010010,
            0b11001001101010101101100100101100,
            0b10100011000100010011011011010101,
            0b10001111011001101101001001001011,
            0b10111011000100110010010010100010,
            0b10010001010001110010000101010110,
            0b11101111001011011010111110011111
    };
    public String hash(String input) {
        HashGenerator generator = new HashGenerator(HASH_SEED);

        byte[] inputBytes =  input.getBytes(StandardCharsets.UTF_8);
        int bucketCount = (int)Math.ceil(inputBytes.length / (double) FREE_BUCKET_SIZE);

        String binaryInput = strToBinary(input);

        int bucketIndex = 0;
        do {
            generator.processBucket(inputBytes, FREE_BUCKET_SIZE * bucketIndex);
            bucketIndex++;
        } while (bucketIndex < bucketCount);

        return generator.formatHash();
    }

    // Converts certain number of bytes into bucket of boolean values, possibly redundant
    private boolean[] getBucket(byte[] bytes, int bucketIndex) {
        boolean[] bucket = new boolean[BUCKET_SIZE];
        int positiveBitCount = 0;

        int bytePos = BYTES_PER_BUCKET * bucketIndex;
        int endingBytePos = Math.min(bytes.length, bytePos + BYTES_PER_BUCKET);

        // Populate the bucket with bytes converted to binary digits
        for (int i = 0; bytePos < endingBytePos; bytePos++) {
            byte value = bytes[bytePos];

            for (int b = 0; b < Byte.SIZE; b++, i++) {
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

    private void enrichBucketWithSizeBytes(byte[] bytes, int size) {

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
