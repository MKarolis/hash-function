package com.karolismed.hashfunction.constants;

public enum ResourceFilename {
    PAIRS_100K("100k_pairs.txt"),
    PAIRS_100K_DIFF_ONE_SYMBOL("100k_pairs_diffOneSymbol.txt");

    private String fileName;

    ResourceFilename(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
