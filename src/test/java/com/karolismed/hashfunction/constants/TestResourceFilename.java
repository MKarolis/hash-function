package com.karolismed.hashfunction.constants;

public enum TestResourceFilename {
    ONE_SYMBOL_1("oneSymbol_1.txt"),
    ONE_SYMBOL_2("oneSymbol_2.txt"),
    WORD_LENGTH_1000_1("word_length_1000_1.txt"),
    WORD_LENGTH_1000_2("word_length_1000_2.txt"),
    WORD_LENGTH_1000_SYMBOL_DIFF_1("word_length_1000_symbol_diff_1.txt"),
    WORD_LENGTH_1000_SYMBOL_DIFF_2("word_length_1000_symbol_diff_2.txt");

    private String fileName;

    TestResourceFilename(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
