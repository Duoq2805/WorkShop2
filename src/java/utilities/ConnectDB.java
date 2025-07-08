package utilities;

import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private String hostName;
    private String instance;
    private String port;
    private String dbName;
    private String user;
    private String pass;

    public ConnectDB() {
        this.hostName = "127.0.0.1";
        this.instance = "";
        this.port = "1433";
        this.dbName = "ProductIntro";
        this.user = "sa";
        this.pass = "12345";
    }

    public ConnectDB(ServletContext sc) {
        this.hostName = sc.getInitParameter("hostAddress");
        this.instance = sc.getInitParameter("instance");
        this.dbName = sc.getInitParameter("dbName");
        this.port = sc.getInitParameter("dbPort");
        this.user = sc.getInitParameter("username");
        this.pass = sc.getInitParameter("password");
    }

    public String getURLString() {
        String fm = "jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s;";
        return String.format(fm, hostName, port, dbName, user, pass);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        System.out.println("Thử kết nối tới: " + getURLString());
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(getURLString(), user, pass);
        System.out.println("Kết nối thành công: " + conn);
        return conn;
    }
}
