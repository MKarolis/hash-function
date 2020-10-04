package com.karolismed.hashfunction.jcommander;

import com.beust.jcommander.Parameter;
import com.karolismed.hashfunction.benchmark.OperationMode;
import com.karolismed.hashfunction.jcommander.converter.OperationModeConverter;

public class CLParams {

    @Parameter(
        names = {"-m", "-mode"},
        description = "Operation mode of the program",
        converter = OperationModeConverter.class
    )
    private OperationMode operationMode = OperationMode.CL_ARG;

    @Parameter(
        description = "[Value]"
    )
    private String value = "";

    @Parameter(names = {"--help"}, help = true, description = "Shows app usage")
    private boolean help;

    public OperationMode getOperationMode() {
        return operationMode;
    }

    public String getValue() {
        return value;
    }

    public boolean isHelp() {
        return help;
    }
}
