package com.karolismed.hashfunction.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class FileUtils {

    public String readStringFromClasspath(String fileName) {
        String data = "";

        try (InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)) {
            data = new String(in.readAllBytes());
        } catch (IOException ex) {
            System.out.println("Error while reading data from file " + fileName);
        }

        return data;
    }

}
