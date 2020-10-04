package com.karolismed.hashfunction.benchmark;

public enum OperationMode {
    CL_ARG("ARG"),
    INTERACTIVE("I"),
    FILE_ARG("FILE"),
    BENCHMARK("BENCH"),
    VERSUS("VS");

    private String value;

    OperationMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OperationMode fromString(String source) {
        for (OperationMode mode : OperationMode.values()) {
            if (mode.getValue().equalsIgnoreCase(source)) {
                return mode;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return value;
    }

}
