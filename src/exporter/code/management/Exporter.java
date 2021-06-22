package exporter.code.management;

import exporter.code.logging.LogLevel;
import exporter.code.logging.Logger;
import io.prometheus.client.Collector;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.SimpleCollector;
import io.prometheus.client.exporter.HTTPServer;
import java.io.IOException;
import java.util.HashMap;


@SuppressWarnings("rawtypes")
public class Exporter {
    public static HashMap<Query,SimpleCollector> collectors = new HashMap<>();

    public static Counter counter_errors = Counter.build().namespace(Config.EXPORTER_NAMESPACE).name("counter_errors").help("Total number of errors").register();

    public static void createMetrics(){
        Logger.log("exporter creating metrics",LogLevel.DEBUG);

        if (Query.queries.isEmpty()){
            Logger.log("no queries found!",LogLevel.WARN);
            return;
        }

        for (Query q : Query.queries) {
            if (q.metricType == MetricType.GAUGE){
                Gauge collector = Gauge.build().namespace(q.namespace).name(q.name).help(q.description).register();
                collectors.put(q,collector);
            }
            else if(q.metricType == MetricType.COUNTER){
                Counter collector = Counter.build().namespace(q.namespace).name(q.name).help(q.description).register();
                collectors.put(q,collector);
            }
            else
                Logger.log("undefined metric type (skipping): " + q.metricType,LogLevel.WARN);
        }
    }

    public static void setMetricValue(Query query, double value){
        Logger.log("setting value " + value + " for metric " + query.name,LogLevel.DEBUG);
        Collector collector = collectors.get(query);
        switch (query.metricType){
            case GAUGE ->
                    ((Gauge)collector).set(value);
            case COUNTER -> {
                double old_val = ((Counter) collector).get();
                ((Counter) collector).inc(value - old_val);
            }
            default ->
                    Logger.log("undefined metric type: " + query.metricType,LogLevel.ERROR);
        }
    }

    public static void start(){
        try {
            createMetrics();
            new HTTPServer(Config.EXPOSE_PORT);
            Logger.log("Metric exporter server stared on port " + Config.EXPOSE_PORT, LogLevel.INFO);
        } catch (IOException e) {
            Logger.log("Failed to start metric exporter due to: \n" + e.getMessage(), LogLevel.ERROR);
        }
    }
}
