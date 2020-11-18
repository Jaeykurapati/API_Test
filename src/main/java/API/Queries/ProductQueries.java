package API.Queries;

import API.Model.Product;
import API.Utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductQueries {

    static Connection con = MySQLConnection.getConnection();
    static Properties properties = MySQLConnection.getProperties();


    public static List<Product> getProducts(String query){
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product();
                product.setProductName(rs.getString("product_name"));
                product.setProductCode(rs.getString("product_code"));
                product.setProductDescription(rs.getString("product_description"));
                product.setProductVendor(rs.getString("product_vendor"));
                System.out.println(product.getProductName());
                productList.add(product);
            }

            return productList;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static List<Product> getProductByOrderID(String orderId){
        String query = properties.getProperty("products.getByOrderId");
        query = query.replace("ordernum",orderId);
        return getProducts(query);
    }

    public static List<Product> getAllProducts(){
        return getProducts(properties.getProperty("products.getAll"));
    }
}
