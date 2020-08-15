import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

context.name = "eureka-server"
appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%level] [%thread] [%logger{50}] >>> %msg%n"
    }
}
appender("FILE", RollingFileAppender) {
    file = "/apps/logs/log_receiver/spring-cloud-study/application.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%level] [%thread] [%logger{50}] >>> %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "data.%d{yyyy-MM-dd}.log"
        maxHistory = 7
        totalSizeCap = "1GB"
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = "5MB"
    }
}