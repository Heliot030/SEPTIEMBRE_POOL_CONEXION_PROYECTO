package com.devtalles.proyecto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL ="jdbc:postgresql://ep-calm-dream-axz37whx-pooler.c-4.us-east-2.aws.neon.tech/neondb?sslmode=require&channelBinding=require";
    private static final String USER ="neondb_owner";
    private static final String PASSWORD ="npg_4H9cmPjRbovM";

    private static DataBaseConnection instance;
    private Connection connection;

    public DataBaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL,USER,PASSWORD);;
        System.out.println("Conectado...");
    }

    private static synchronized DataBaseConnection getInstance() throws SQLException {
        if(instance == null || instance.connection.isClosed()){
            instance = new DataBaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
