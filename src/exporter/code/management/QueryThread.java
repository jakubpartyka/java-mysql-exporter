package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;

@SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
public class QueryThread extends Thread {

    private final Query query;

    public QueryThread(Query query){
        this.query = query;
    }

    @Override
    public void run() {
        while (true) {
            try {
                double val = DatabaseConnector.executeQuery(query);
                Exporter.setMetricValue(query,val);
            } catch (Exception e){
                Logger.log("failed to execute query " + query.statement + " due to: " + e.getMessage(), LogLevel.ERROR);
                e.printStackTrace();
            }
            finally {
                try {
                    Thread.sleep(Config.INTERVAL);
                } catch (InterruptedException e) {
                    Logger.log("sleep failed: " + e.getMessage(),LogLevel.ERROR);
                    e.printStackTrace();
                }
            }
        }
    }
}
