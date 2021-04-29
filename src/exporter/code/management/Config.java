package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/src/config";

    static String DB_ADDRESS, DB_USERNAME, DB_PASSWORD, DB_NAME;
    static int DB_PORT = 3306, EXPOSE_PORT;
    public static LogLevel LOG_LEVEL = LogLevel.INFO;


    public static void readConfig() throws IOException {
        File config = new File(CONFIG_PATH);

        // CREATE PROPERTIES OBJECT
        Properties prop = new Properties();
        prop.load(new FileReader(config));

        // READ PROPERTIES
        DB_ADDRESS = prop.getProperty("DB_ADDRESS");
        DB_USERNAME = prop.getProperty("DB_USERNAME");
        DB_PASSWORD = prop.getProperty("DB_PASSWORD");
        DB_NAME = prop.getProperty("DB_NAME");
        DB_PORT = Integer.parseInt(prop.getProperty("DB_PORT", String.valueOf(DB_PORT)));
        EXPOSE_PORT = Integer.parseInt(prop.getProperty("EXPOSE_PORT", String.valueOf(EXPOSE_PORT)));

        Logger.log("config read from " + config.getAbsolutePath(), LogLevel.INFO);
    }
}
