package com.alpergayretoglu.movie_provider.constants;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationConstants {
    private ApplicationConstants() {
    }

    @Value("${server.servlet.context-path}")
    private static String mainPath;

    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 32;
    public static final String MAIN_PATH = mainPath;

}
