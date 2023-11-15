package com.main.exchangeBatch.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchageUtils {
    private static HttpURLConnection connection;
    private static URL url;
    String exChangeData;


    public String getExchageRate() {

        return exChangeData;
    }
}
