package API.Queries;

import API.Model.Customer;
import API.Utils.MySQLConnection;
import API.Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderQueries {

    static Connection con = MySQLConnection.getConnection();
    static Properties properties = MySQLConnection.getProperties();

    public static List<Order> getOrders(String query) {

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Order> orderList = new ArrayList<>();

            while (rs.next()) {
                Order order = new Order();
                getOrder(rs, order);
                orderList.add(order);
            }

            return orderList;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Order getOrder(String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Order order = new Order();

            while (rs.next()) {
                getOrder(rs, order);
            }

            return order;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
    public static Order getOrderById(String orderId) {

        String query = properties.getProperty("order.getById");
        query = query.replace("ordernum",orderId);
        return getOrder(query);
    }

    public static List<Order> getOrdersInYear(String year){
        String query = properties.getProperty("order.getInYear");
        query = query.replace("input",year);
        return getOrders(query);
    }

    public static List<Order> getOrdersBetweenDates(String fromYear, String toYear){

        String query = properties.getProperty("order.betweenDates").replace("date1",fromYear).replace("date2",toYear);
        return getOrders(query);
    }

    public static Customer getCustomerForOrder(String query){

        query = properties.getProperty("order.getCustomer").replace("ordernum",query);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setCustomerName(rs.getString("customerName"));
                customer.setCustomerNumber(rs.getInt("customerNumber"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setCountry(rs.getString("country"));
                customer.setZipcode(rs.getString("postalCode"));
                customer.setPhoneNumber(rs.getString("phone"));
            }

            return customer;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
    public static Order getRecentOrder(){
        String query = properties.getProperty("order.recent");
        return getOrder(query);
    }

    public static Order getOlderOrder(){
        String query = properties.getProperty("order.older");
        return getOrder(query);
    }

    private static void getOrder(ResultSet rs, Order order) throws SQLException {
        order.setOrderNumber(rs.getInt("orderNumber"));
        order.setCustomerNumber(rs.getInt("customerNumber"));
        order.setOrderDate(rs.getDate("orderDate"));
        order.setRequiredDate(rs.getDate("requiredDate"));
        order.setShippedDate(rs.getDate("shippedDate"));
        order.setStatus(rs.getString("status"));
    }
}
