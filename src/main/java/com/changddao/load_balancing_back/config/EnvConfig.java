package com.changddao.load_balancing_back.config;

import io.github.cdimascio.dotenv.Dotenv;


public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}
