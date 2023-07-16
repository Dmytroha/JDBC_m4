package org.example;
import org.apache.log4j.BasicConfigurator;
import org.example.task4.DatabaseQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Main {
    private static final Logger loggerMain = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {

        // configure logger
        BasicConfigurator.configure();

        DatabaseQueryService dtbQrySrvs = new DatabaseQueryService();

        loggerMain.info("\n<---------------Find longest project---------->");
        dtbQrySrvs.findLongestProject().forEach(longestProject -> loggerMain.info(longestProject.toString()));
        loggerMain.info("\n<---------------Find client with max project---------->");
        dtbQrySrvs.findMaxProjectsClient().forEach(maxProjectsClient -> loggerMain.info(maxProjectsClient.toString()));
        loggerMain.info("\n<---------------Find worker with max salary---------->");
        dtbQrySrvs.findMaxSalaryWorker().forEach(maxSalaryWorker -> loggerMain.info(maxSalaryWorker.toString()));
        loggerMain.info("\n<---------------Find Youngest Eldest workers---------->");
        dtbQrySrvs.findYoungestEldestWorkers().forEach(youngestEldestWorker ->
                                                        loggerMain.info(youngestEldestWorker.toString()));
        loggerMain.info("\n<---------------Print Project Prices---------->");
        dtbQrySrvs.printProjectPrices().forEach(projectPrice -> loggerMain.info(projectPrice.toString()));
        loggerMain.info("\n\n Application executed successfully");
    }
}