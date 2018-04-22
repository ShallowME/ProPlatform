package com.skyworth.log4j;

import org.apache.log4j.Logger;

public class Log4jTest {
    private static final Logger logger = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        logger.debug("debug");
        logger.error("error");
        logger.info("info");
    }
}
