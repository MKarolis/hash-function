package com.karolismed.hashfunction.hashing;

import static com.karolismed.hashfunction.hashing.HashingService.FREE_BUCKET_SIZE;

class HashGenerator {

    private static final int WORDS_PER_BUCKET = 64;
    private static final int WORD_GENERATION_LOWER_BOUND = 16;

    private int[] hashWords;
    private int hashWordIndex;

    public HashGenerator(int[] initHashWords) {
        hashWords = initHashWords.clone();
        hashWordIndex = 0;
    }

    public void processBucket(byte[] bytes, int startIndex) {
        int[] words = new int[WORDS_PER_BUCKET];
        addWordsFromExistingBytes(bytes, words, startIndex);

        System.out.println(wordsToBinary(words));
    }

    public String formatHash() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int word : hashWords) {
            stringBuilder.append(String.format("%08x", word));
        }

        return  stringBuilder.toString();
    }

    private void addWordsFromExistingBytes(byte[] bytes, int[] words, int startIndex) {
        int wordsProcessed = 0;

        for (int i = startIndex; i < Math.min(bytes.length, startIndex + FREE_BUCKET_SIZE);) {
            for(int bi = Integer.BYTES - 1;
                bi >= 0 && i < Math.min(bytes.length, startIndex + FREE_BUCKET_SIZE);
                bi--, i++
            ) {
                words[wordsProcessed] |= bytes[i] << bi * Byte.SIZE;
            }

            wordsProcessed++;
        }

        words[WORD_GENERATION_LOWER_BOUND - 1] = bytes.length;
    }

    // Helper fns
    private String wordToBinary(int w) {
        return String.format("%32s", Integer.toBinaryString(w)).replace(' ', '0');
    }

    private String wordsToBinary(int[] words) {
        StringBuilder binary = new StringBuilder();
        for (int w : words) {
            binary.append(wordToBinary(w));
            binary.append('\n');
        }

        return binary.toString();
    }
}
