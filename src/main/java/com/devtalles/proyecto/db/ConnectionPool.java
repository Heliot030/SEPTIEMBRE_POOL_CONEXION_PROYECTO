package com.devtalles.proyecto.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static HikariDataSource  hikariDataSource;

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:postgresql://ep-calm-dream-axz37whx-pooler.c-4.us-east-2.aws.neon.tech/neondb?sslmode=require&channelBinding=require");
        config.setUsername("neondb_owner");
        config.setPassword("npg_4H9cmPjRbovM");

        config.setMaximumPoolSize(12);
        config.setMinimumIdle(2); //dps actovas lista  utilizat

        config.setIdleTimeout(3000); //sin usarse
        config.setConnectionTimeout(3000); //todas estan ocupadas

        config.setLeakDetectionThreshold(15000);

        hikariDataSource = new HikariDataSource(config);

    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

}
