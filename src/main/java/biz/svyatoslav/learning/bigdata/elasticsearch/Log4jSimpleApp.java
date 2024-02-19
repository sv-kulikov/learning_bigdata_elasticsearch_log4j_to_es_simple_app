package biz.svyatoslav.learning.bigdata.elasticsearch;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 1) Make sure that your Elasticsearch, Logstash and Kibana containers are running (see "readme!/docker-compose.yml")
// 2) Make sure that ip addresses are correct in "logstash_es.conf" and "log4j2.xml".

public class Log4jSimpleApp {
    private static final Logger logger = LogManager.getLogger(biz.svyatoslav.learning.bigdata.elasticsearch.Log4jSimpleApp.class);

    public static void main(String[] args) {

        System.out.println("Logging three test messages...");
        logger.info("This is a test INFO message.");
        logger.warn("This is a test WARN message.");
        logger.error("This is a test ERROR message.");

        Configurator.setLevel(logger.getName(), Level.WARN);

        System.out.println("Logging another three test messages with WARN minimum logging level...");
        logger.info("This is a NEW test INFO message.");
        logger.warn("This is a NEW test WARN message.");
        logger.error("This is a NEW test ERROR message.");

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDate = currentDate.format(formatter);

        System.out.println("Done.");
        System.out.println("Visit http://localhost:9200/_cat/indices for indexes list, you should see something like 'spark-logs-" + formattedDate + "' there.");
        System.out.println("Visit http://localhost:9200/spark-logs-" + formattedDate + "/_search?pretty to see the result. Change the date in the index name!");

    }
}