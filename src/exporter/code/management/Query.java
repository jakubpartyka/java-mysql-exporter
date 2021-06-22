package exporter.code.management;

import java.util.ArrayList;

public class Query {
    public static ArrayList<Query> queries = new ArrayList<>();

    String db_address, db_name, db_user, db_pass;
    String query;
    String name,namespace,description;
    MetricType metricType;
    int db_port;

    public Query(String db_address, String db_name, String db_user, String db_pass, String query, String name, String namespace, String description, MetricType metricType, int db_port) {
        this.db_address = db_address;
        this.db_name = db_name;
        this.db_user = db_user;
        this.db_pass = db_pass;
        this.name = name;
        this.query = query;
        this.namespace = namespace;
        this.description = description;
        this.metricType = metricType;
        this.db_port = db_port;
    }

    @Override
    public String toString() {
        return "Query{" +
                "db_address='" + db_address + '\'' +
                ", db_name='" + db_name + '\'' +
                ", db_user='" + db_user + '\'' +
                ", db_pass='" + db_pass + '\'' +
                ", query='" + query + '\'' +
                ", name='" + name + '\'' +
                ", namespace='" + namespace + '\'' +
                ", description='" + description + '\'' +
                ", metricType=" + metricType +
                ", db_port=" + db_port +
                '}';
    }
}
