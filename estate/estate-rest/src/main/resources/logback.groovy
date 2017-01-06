import ch.qos.logback.classic.Level
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import java.nio.charset.StandardCharsets

String localhost = InetAddress.getLocalHost().getHostName()
String levelstr = (System.getProperty('logback.level') ?: System.getenv('logback_level')) ?: ''
String pathstr = (System.getProperty('logback.path') ?: System.getenv('logback_path')) ?: ''
String filenamestr = (System.getProperty('logback.filename') ?: System.getenv('logback.filename')) ?: 'ljs3'

pathstr = !pathstr || pathstr.endsWith('/') ? pathstr : pathstr + "/"

jmxConfigurator()

appender('CONSOLE', ConsoleAppender) {
    filter(ThresholdFilter) {
        level = Level.toLevel(levelstr, Level.INFO)
    }
    encoder(PatternLayoutEncoder) {
        charset = StandardCharsets.UTF_8
        pattern = "%date $localhost[%thread] %.-5level %logger{32} - [%mdc{executionContext.correlationId}] %message%n%exception{32}"
    }
}

if (!levelstr) {
    appender('FILE', RollingFileAppender) {
        file = pathstr + filenamestr + "-estaterest.log"
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = pathstr + filenamestr + "-estaterest.%d{yyyy-MM-dd}.log"
            maxHistory = 30
        }
        filter(ThresholdFilter) {
            level = Level.INFO
        }
        encoder(PatternLayoutEncoder) {
            charset = StandardCharsets.UTF_8
            pattern = "%date $localhost[%thread] %.-5level %logger{32} - [%mdc{executionContext.correlationId}] %message%n%exception{32}"
        }
    }

    logger("springfox.documentation.schema", Level.INFO)
    logger("org.springframework", Level.INFO)
    root(Level.toLevel(levelstr, Level.INFO), ['FILE'])// prod log file only
} else {
    root(Level.toLevel(levelstr, Level.INFO), ['CONSOLE'])
}
