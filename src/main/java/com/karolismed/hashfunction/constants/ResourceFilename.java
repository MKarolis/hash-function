package com.karolismed.hashfunction.constants;

public enum ResourceFilename {
    PAIRS_100K("100k_pairs.txt"),
    PAIRS_100K_DIFF_ONE_SYMBOL("100k_pairs_diffOneSymbol.txt"),
    ONE_SYMBOL_1("oneSymbol_1.txt"),
    ONE_SYMBOL_2("oneSymbol_2.txt"),
    WORD_LENGTH_1000_1("word_length_1000_1.txt"),
    WORD_LENGTH_1000_2("word_length_1000_2.txt"),
    WORD_LENGTH_1000_SYMBOL_DIFF_1("word_length_1000_symbol_diff_1.txt"),
    WORD_LENGTH_1000_SYMBOL_DIFF_2("word_length_1000_symbol_diff_2.txt");

    private String fileName;

    ResourceFilename(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
