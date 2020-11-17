package API.Test;

import API.Model.Product;
import API.Queries.ProductQueries;
import API.Utils.MySQLConnection;
import API.Model.Customer;
import API.Model.Order;
import API.Queries.CustomerQueries;
import API.Queries.OrderQueries;

import java.util.List;
import java.util.Properties;

public class RunQueries {


    static Properties properties = MySQLConnection.getProperties();

    public static void main(String[] args) {

        //Get customers
        List<Customer> customerList = CustomerQueries.getCustomers();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }

        //Get orders
        List<Order> orderList = OrderQueries.getOrders(properties.getProperty("order.getAll"));
        for (Order order : orderList) {
            System.out.println(order);
        }

        //get customer by id
        System.out.println(CustomerQueries.getCustomerById("4984"));

        //get order by id
        System.out.println(OrderQueries.getOrderById("10123"));

        //get customer orders by id
        System.out.println(CustomerQueries.getCustomerOrdersById("103"));

        //get orders in given year
        System.out.println(OrderQueries.getOrdersInYear("2003"));

        //get orders placed between dates
        System.out.println(OrderQueries.getOrdersBetweenDates("2003-01-20","2003-12-20"));

        //get recent order
        System.out.println(OrderQueries.getRecentOrder());

        //get older order
        System.out.println(OrderQueries.getOlderOrder());

        //get all products
        System.out.println(ProductQueries.getAllProducts());

        //get products in the given order of given customer.
        List<Product> productList = ProductQueries.getProductByOrderID("10123");
        for(Product product:productList)
            System.out.println(product.getProductName());

        //get customer for given order
        System.out.println(OrderQueries.getCustomerForOrder("10123"));
    }
}
