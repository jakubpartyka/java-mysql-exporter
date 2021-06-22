package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

public class Config {
    private static final String CONFIG_PATH = "config";
    public static String QUERIES_FILE_PATH = "queries";
    public static int EXPOSE_PORT = 20026;
    public static LogLevel LOG_LEVEL = LogLevel.INFO;
    public static String EXPORTER_NAMESPACE = "java_mysql_exporter";
    public static long INTERVAL = 30*1000;

    public static void readConfig() throws IOException {
        File config = new File(CONFIG_PATH);

        // CREATE PROPERTIES OBJECT
        Properties prop = new Properties();
        prop.load(new FileReader(config));

        LOG_LEVEL = LogLevel.valueOf(prop.getProperty("LOG_LEVEL",LOG_LEVEL.name()));
        EXPOSE_PORT = Integer.parseInt(prop.getProperty("EXPOSE_PORT", String.valueOf(EXPOSE_PORT)));
        INTERVAL = Integer.parseInt(prop.getProperty("INTERVAL", String.valueOf(INTERVAL)));
        EXPORTER_NAMESPACE = prop.getProperty("EXPORTER_NAMESPACE", EXPORTER_NAMESPACE);
        QUERIES_FILE_PATH = prop.getProperty("QUERIES_FILE_PATH", QUERIES_FILE_PATH);


        Logger.log("config read from " + config.getAbsolutePath(), LogLevel.INFO);
    }

    public static void readQueries() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(QUERIES_FILE_PATH));
        String line = reader.readLine();
        while (line != null){
            if (line.isBlank() || line.startsWith("#")){
                line = reader.readLine();
                continue;
            }

            String [] data = line.split(";");
            System.out.println(Arrays.toString(data));

            if(data.length != 10){
                Logger.log("malformed query line (skipping): " + line,LogLevel.WARN);
                continue;
            }
            String address = data[0];
            int port = Integer.parseInt(data[1]);
            String user = data[2];
            String pass = data[3];
            String db_name = data[4];
            MetricType type = MetricType.valueOf(data[5].toUpperCase(Locale.ROOT));
            String name = data[6];
            String namespace = data[7];
            String description = data[8];
            String query = data[9];

            Query query1 = new Query(address,db_name,user,pass,query,name,namespace,description,type,port);
            Logger.log("query created: " + query1,LogLevel.DEBUG);

            line = reader.readLine();
        }

        Logger.log("queries read from " + QUERIES_FILE_PATH,LogLevel.INFO);
    }
}
