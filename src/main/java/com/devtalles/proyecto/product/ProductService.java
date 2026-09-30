package com.devtalles.proyecto.product;

import com.devtalles.proyecto.category.Category;
import com.devtalles.proyecto.category.CategoryDao;
import com.devtalles.proyecto.db.ConnectionPool;
import jdk.swing.interop.SwingInterOpUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {

    public void saveProductWithCategory(Product product, Category category) throws SQLException {
        Connection connection = null;

        try{
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false); //inicio de trancaccion

            CategoryDao categoryDao = new CategoryDao(connection);
            ProductDAO productDAO = new ProductDAO(connection);

           Category newCategory = categoryDao.save(category);
            product.setCategory(newCategory);
            productDAO.save(product);
            //se cumple se hace el commit
            connection.commit();
            System.out.println("Producto ingresado correctamente ,,,,,");
        } catch (SQLException e) {
            if(connection != null  ){
                connection.rollback();
            }
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (connection != null)
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                }catch (SQLException e){
                    System.out.println("errro ala cerrar conexion");
                }
        }
    }

    public void updateProduct(Product product) throws SQLException {
        Connection connection = null;

        try{
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false); //inicio de trancaccion

            ProductDAO productDAO = new ProductDAO(connection);
            productDAO.update(product);

            connection.commit();
            System.out.println("Producto fue actualizado correctamente....");
        } catch (SQLException e) {
            if(connection != null  ){
                connection.rollback();
            }
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (connection != null)
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                }catch (SQLException e){
                    System.out.println("errro ala cerrar conexion");
                }
        }
    }

    public void deleteProduct(Long id) throws SQLException {
        Connection connection = null;

        try{
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false); //inicio de trancaccion

            ProductDAO productDAO = new ProductDAO(connection);
            productDAO.delete(id);

            connection.commit();
            System.out.println("Producto fue eliominado correctamente....");
        } catch (SQLException e) {
            if(connection != null  ){
                connection.rollback();
            }
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (connection != null)
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                }catch (RuntimeException e){
                    System.out.println("errro ala cerrar conexion");
                }
        }
    }

    public List<Product> findProduct() throws SQLException {
        // try-with-resources cierra la conexión automáticamente al salir del bloque
        try (Connection connection = ConnectionPool.getConnection()) {
            ProductDAO productDAO = new ProductDAO(connection);
            return productDAO.findAll();
        } catch (SQLException e) {
            System.err.println("Error al buscar productos: " + e.getMessage());
            throw e;
        }
    }

}
