package exporter.code.logging;

import exporter.code.management.Config;
import exporter.code.management.Exporter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Logger {
    public static void log(String message, LogLevel level){
        // increment exporter's ERROR counter
        if(level == LogLevel.ERROR)
            Exporter.counter_errors.inc();

        if(level.value > Config.LOG_LEVEL.value)
            return;

        // create log entry message
        String timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS").format(new Timestamp(System.currentTimeMillis()));
        String logEntry = ("[" + timestamp + "]" + "[" + level + "]:" + message + '\n');
        System.out.print(logEntry);
    }
}
