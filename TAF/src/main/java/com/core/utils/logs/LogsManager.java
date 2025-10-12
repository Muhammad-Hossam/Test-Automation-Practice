package com.core.utils.logs;

import com.core.utils.report.AllureConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class LogsManager {
    public static final String LOGS_PATH = AllureConstants.USER_DIR+"test-output/Logs/";

    private static Logger logger()
    {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void info(String... messages)
    {
        logger().info(String.join(" ", messages));
    }

    public static void error(String... messages)
    {
        logger().error(String.join(" ", messages));
    }

    public static void warn(String... messages)
    {
        logger().warn(String.join(" ", messages));
    }

    public static void debug(String... messages)
    {
        logger().debug(String.join(" ", messages));
    }

    public static void fatal(String... messages)
    {
        logger().fatal(String.join(" ", messages));
    }
}
