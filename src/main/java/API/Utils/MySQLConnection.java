package API.Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConnection {

    private static Connection connection = null;
    private static ClassLoader loader = Thread.currentThread().getContextClassLoader();
    private static Properties properties = new Properties();

    static
    {
        String url = "jdbc:mysql:// localhost:3306/classicmodels";
        String user = "root";
        String pass = "root0000";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            InputStream resourceStream = loader.getResourceAsStream("application.properties");
            properties.load(resourceStream);
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    public static Connection getConnection()
    {
        return connection;
    }

    public static Properties getProperties()
    {
        return properties;
    }
}
