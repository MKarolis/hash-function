package com.karolismed.hashfunction.jcommander.converter;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.karolismed.hashfunction.constants.OperationMode;

import java.util.Optional;

public class OperationModeConverter implements IStringConverter<OperationMode> {
    @Override
    public OperationMode convert(String value) {
        return Optional.ofNullable(OperationMode.fromString(value))
            .orElseThrow(() -> new ParameterException(
                "Invalid value for operation mode, use --help to find possible options"
            ));
    }
}
