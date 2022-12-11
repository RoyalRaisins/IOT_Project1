package com.huawei.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {

    private static final String DEFAULT_ENCODE = "utf-8";

    public static String inputStream2String(InputStream inputStream, String charsetName) {

        if (inputStream == null) {
            return null;
        }
        InputStreamReader inputStreamReader = null;

        try {
            if (charsetName == null || charsetName.trim().length() <= 0) {
                inputStreamReader = new InputStreamReader(inputStream, DEFAULT_ENCODE);
            } else {
                inputStreamReader = new InputStreamReader(inputStream, charsetName);
            }

            int readLen = 0;
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[1024];
            while ((readLen = inputStreamReader.read(buffer)) != -1) {
                builder.append(buffer, 0, readLen);
            }
            return builder.toString();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            close(inputStreamReader);
        }
        return null;
    }

    private static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
