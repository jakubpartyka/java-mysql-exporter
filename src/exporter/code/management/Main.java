package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Config.readConfig();
        Config.readQueries();
        Exporter.start();

        Logger.log("starting query threads",LogLevel.INFO);

        // start threads
        for (Query query : Query.queries) {
            QueryThread qt = new QueryThread(query);
            qt.start();
        }

        Logger.log(Query.queries.size() + " query threads started",LogLevel.INFO);
    }
}