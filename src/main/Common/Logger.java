package main.Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public enum LogLevels {
        CRITICAL(3, "Critical"),
        WARNING(2, "Warning"),
        INFO(1, "Info"),
        DEBUG(0, "Debug");

        public final int Level;
        public final String LevelName;

        private LogLevels(int i, String s) {
            this.Level = i;
            this.LevelName = s;
        }
    }

    public LogLevels LogLevel = LogLevels.DEBUG;

    public void Add(String message, LogLevels l) {
        if (l.Level >= LogLevel.Level) {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss.SS dd-MM-yyyy");
            System.out.println("[" + LocalDateTime.now().format(f) + "]" + " [" + l.LevelName + "]" + " " + message);
        }
    }

    public static Logger Instance = new Logger();
}
