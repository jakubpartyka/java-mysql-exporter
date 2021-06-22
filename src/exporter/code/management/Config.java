package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_PATH = "config";
    public static String QUERIES_FILE_PATH = "queries";
    public static int EXPOSE_PORT = 20026;
    public static LogLevel LOG_LEVEL = LogLevel.INFO;
    public static String EXPORTER_NAMESPACE = "java-mysql-exporter";

    public static void readConfig() throws IOException {
        File config = new File(CONFIG_PATH);

        // CREATE PROPERTIES OBJECT
        Properties prop = new Properties();
        prop.load(new FileReader(config));

        LOG_LEVEL = LogLevel.valueOf(prop.getProperty("LOG_LEVEL",LOG_LEVEL.name()));
        EXPOSE_PORT = Integer.parseInt(prop.getProperty("EXPOSE_PORT", String.valueOf(EXPOSE_PORT)));
        EXPORTER_NAMESPACE = prop.getProperty("EXPORTER_NAMESPACE", EXPORTER_NAMESPACE);
        QUERIES_FILE_PATH = prop.getProperty("QUERIES_FILE_PATH", QUERIES_FILE_PATH);


        Logger.log("config read from " + config.getAbsolutePath(), LogLevel.INFO);
    }
}
