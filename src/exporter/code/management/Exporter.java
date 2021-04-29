package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import java.io.IOException;

public class Exporter {
    public static Counter counter_errors = Counter.build().namespace("otomoto_scanner").name("errors").help("Total number of all messages marked with ERROR").register();

    public static void start(){
        try {
            new HTTPServer(Config.EXPOSE_PORT);
            Logger.log("Metric exporter server stared on port " + Config.EXPOSE_PORT, LogLevel.INFO);
        } catch (IOException e) {
            Logger.log("Failed to start metric exporter due to: \n" + e.getMessage(), LogLevel.ERROR);
        }
    }
}
