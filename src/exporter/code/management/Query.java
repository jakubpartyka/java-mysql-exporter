package exporter.code.management;

import java.util.ArrayList;
import java.util.Objects;

public class Query {
    public static ArrayList<Query> queries = new ArrayList<>();

    String db_address, db_name, db_user, db_pass;
    String statement;
    String name,namespace,description;
    MetricType metricType;
    int db_port;

    public Query(String db_address, String db_name, String db_user, String db_pass, String query, String name, String namespace, String description, MetricType metricType, int db_port) {
        this.db_address = db_address;
        this.db_name = db_name;
        this.db_user = db_user;
        this.db_pass = db_pass;
        this.name = name;
        this.statement = query;
        this.namespace = namespace;
        this.description = description;
        this.metricType = metricType;
        this.db_port = db_port;
        Query.queries.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query1 = (Query) o;
        return db_port == query1.db_port && Objects.equals(db_address, query1.db_address) && Objects.equals(db_name, query1.db_name) && Objects.equals(db_user, query1.db_user) && Objects.equals(db_pass, query1.db_pass) && Objects.equals(statement, query1.statement) && Objects.equals(name, query1.name) && Objects.equals(namespace, query1.namespace) && Objects.equals(description, query1.description) && metricType == query1.metricType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(db_address, db_name, db_user, db_pass, statement, name, namespace, description, metricType, db_port);
    }

    @Override
    public String toString() {
        return "Query{" +
                "db_address='" + db_address + '\'' +
                ", db_name='" + db_name + '\'' +
                ", db_user='" + db_user + '\'' +
                ", db_pass='" + db_pass + '\'' +
                ", query='" + statement + '\'' +
                ", name='" + name + '\'' +
                ", namespace='" + namespace + '\'' +
                ", description='" + description + '\'' +
                ", metricType=" + metricType +
                ", db_port=" + db_port +
                '}';
    }
}
