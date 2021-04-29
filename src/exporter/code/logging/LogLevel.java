package exporter.code.logging;

public enum LogLevel {
    ERROR(1),
    WARN(2),
    INFO(3),
    DEBUG(4),
    TRACE(5);

    int value;
    LogLevel(int value) {
        this.value = value;
    }
}
