package API.Queries;

import API.Utils.MySQLConnection;
import API.Model.Customer;
import API.Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomerQueries {

    static Connection con = MySQLConnection.getConnection();
    static Properties properties = MySQLConnection.getProperties();

    public static List<Customer> getCustomers() {

        String query = properties.getProperty("customer.getAll");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Customer> customerList = new ArrayList<>();

            while (rs.next()) {
                Customer emp = new Customer();
                getEmployee(rs, emp);
                customerList.add(emp);
            }

            return customerList;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Customer getCustomerById(String id) {

        String query = properties.getProperty("customer.getById");
        query = query.replace("custnum",id);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                getEmployee(rs, customer);
            }

            return customer;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static List<Order> getCustomerOrdersById(String id) {

        String query = properties.getProperty("customer.getOrders");
        query = query.replace("custnum",id);
        return OrderQueries.getOrders(query);
    }

    private static void getEmployee(ResultSet rs, Customer customer) throws SQLException {
        customer.setCustomerNumber(rs.getInt("customerNumber"));
        customer.setCustomerName(rs.getString("customerName"));
        customer.setCity(rs.getString("city"));
        customer.setState(rs.getString("state"));
        customer.setCountry(rs.getString("country"));
        customer.setZipcode(rs.getString("postalCode"));
        customer.setPhoneNumber(rs.getString("phone"));
    }
}
