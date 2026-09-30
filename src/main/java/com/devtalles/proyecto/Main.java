package com.devtalles.proyecto;

import com.devtalles.proyecto.category.Category;
import com.devtalles.proyecto.product.Product;
import com.devtalles.proyecto.product.ProductDAO;
import com.devtalles.proyecto.product.ProductService;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException{

        ProductService productService = new ProductService();

        Product product= new Product("SONY 33", 2200,23);
        Category category = new Category("Tecnology");

        try {
            productService.saveProductWithCategory(product,category);
           List<Product> products = productService.findProduct();
           products.forEach(System.out::println);
            System.out.println("---------------------------------------");
            productService.deleteProduct(6L);
            products = productService.findProduct();
            products.forEach(System.out::println);



        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }
    }
}