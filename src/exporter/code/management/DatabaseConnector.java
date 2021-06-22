package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;

import java.sql.*;

public class DatabaseConnector {

    public static double executeQuery(Query query) throws SQLException{
        Connection connection = connect(query);
        PreparedStatement statement = connection.prepareStatement(query.statement);
        ResultSet resultSet = statement.executeQuery();
        double result = -1;
        if (resultSet.next())
            result = resultSet.getDouble(1);
        connection.close();
        return result;
    }

    private static Connection connect(Query query) throws SQLException {
        Logger.log("creating mysql connection", LogLevel.TRACE);
        Connection connection;

        //establish connection
        String addr     = query.db_address;
        String user     = query.db_user;
        String pass     = query.db_pass;
        String name     = query.db_name;
        int port        = query.db_port;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            Logger.log("a fatal error occurred:\n" + e.getMessage(), LogLevel.ERROR);
        }
        String address = "jdbc:mysql://" + addr + ':' + port + '/' + name + "?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";
        connection = DriverManager.getConnection(address,user,pass);

        return connection;
    }
}
