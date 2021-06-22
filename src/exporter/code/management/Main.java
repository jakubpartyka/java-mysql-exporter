package exporter.code.management;
import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;

import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Config.readConfig();
        Config.readQueries();
        Exporter.start();

        while (true){
            for (Query query : Query.queries) {
                try {
                    double val = DatabaseConnector.executeQuery(query);
                    Exporter.setMetricValue(query,val);
                } catch (SQLException e) {
                    Logger.log("failed to execute query " + query.statement + " due to " + e.getMessage(), LogLevel.ERROR);
                    e.printStackTrace();
                }
            }

            Thread.sleep(Config.INTERVAL);
        }

    }
}