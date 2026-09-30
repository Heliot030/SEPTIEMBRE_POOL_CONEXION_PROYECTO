package com.devtalles.proyecto.product;

import com.devtalles.proyecto.db.ConnectionDB;
import com.devtalles.proyecto.db.ConnectionPool;
import com.sun.jdi.PrimitiveValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock, category_id) VALUES (?,?,?,?)";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setLong(4, product.getCategory().getId());

            int rows = preparedStatement.executeUpdate();
            if (rows>0){
                System.out.println("productyo insertado correctamente");
            }

            System.out.println("conectados");
        }
    }

    public List<Product> findAll() throws SQLException {
        String sql = "Select * from products";
        List<Product> products = new ArrayList<>();

        try (
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

        ) {

            while(resultSet.next()){
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                );
                products.add(product);
            }
        }

        return products;
    }

    public void update(Product product)throws SQLException {
        String sql = "UPDATE  products SET name =?, price=?, stock=?, category_id =? WHERE id = ?";

        try (

                PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setLong(4,product.getCategory().getId() );
            preparedStatement.setLong(5,product.getId());


            int rows = preparedStatement.executeUpdate();
            if (rows>0){
                System.out.println("producto ACTUALIZADO correctamente");
            }else {
                System.out.println("no se encotro el producto para actualizar ");
            }

            System.out.println("conectados");
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
            preparedStatement.setLong(1,id);

            int rows = preparedStatement.executeUpdate();
            if (rows>0){
                System.out.println("producto eliminado correctamente");
            }else {
                System.out.println("no se encotro el producto para eliminar ");
            }

            System.out.println("conectados");
        }
    }


}
