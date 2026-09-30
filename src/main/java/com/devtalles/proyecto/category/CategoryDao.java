package com.devtalles.proyecto.category;

import com.devtalles.proyecto.db.ConnectionPool;
import com.devtalles.proyecto.product.Product;

import javax.crypto.spec.PSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao {
    private final Connection connection;

    public CategoryDao(Connection connection) {
        this.connection = connection;
    }

    public Category save(Category category ) {
        String sql = "INSERT INTO categorias (name) VALUES (?) RETURNING id";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

         ) {
            preparedStatement.setString(1, category.getName());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()){
                        Long id = resultSet.getLong("id");
                        category.setId(id);
                        System.out.println("LA CATEGORIA FUE CREAD CORRECTAMENTE  ");
                    }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return category;
    }

    private void showMessage(int rows, String messageOK, String messageErrror){
        if(rows > 0){
            System.out.println("messageOK");
        } else if (!messageErrror.isBlank()) {
            System.out.println(messageErrror);
        }
    }
}
