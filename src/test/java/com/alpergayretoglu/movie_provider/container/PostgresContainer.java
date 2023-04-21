package com.alpergayretoglu.movie_provider.container;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {

    private static final String IMAGE_VERSION = "postgres:13.2";
    public static final String DATABASE_NAME = "movie_provider_test";
    private static PostgreSQLContainer<?> container;

    private PostgresContainer() {
        super(IMAGE_VERSION);

    }

    public static PostgreSQLContainer<?> getInstance() {
        if (container == null) {
            container = new PostgresContainer()
                    .withDatabaseName(DATABASE_NAME)
                    .withUsername("postgres")
                    .withPassword("12345");
        }

        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}