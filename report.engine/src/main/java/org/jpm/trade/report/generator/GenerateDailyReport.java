package org.jpm.trade.report.generator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jpm.trade.report.enums.TransactionTypeEnum;
import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.services.SettlementService;
import org.jpm.trade.report.services.impl.RankingServiceImpl;
import org.jpm.trade.report.services.impl.SettlementServiceImpl;
import org.jpm.trade.report.util.ReportReader;

/**
 * @author Namita
 * This is the main class to generate reports.
 * You can pass the external file path as command line argument,
 * if not it will read the file form resources folder and generate report
 */
public class GenerateDailyReport {

    final static Logger logger = Logger.getLogger("GenerateDailyReport");

    public static String TEST_FILE = "src/main/resources/SampleTradeData.csv";

    public static void main(String[] args) {
        List<Instruction> instructions;

        // Set path from command line if passed as an argument, else use file from resources folder
        if ((args == null) || (args.length == 0))
            instructions = ReportReader.processData(TEST_FILE);
        else
            instructions = ReportReader.processData(args[0]);

        if(instructions != null) {
            logger.info("Start of GenerateDailyReport");
            SettlementService service = new SettlementServiceImpl();
            logger.info("Incoming Settlement report: "+ service.generateSettlementReport(instructions, TransactionTypeEnum.SELL.value()));
            logger.info("Outgoing Settlement report: " + service.generateSettlementReport(instructions, TransactionTypeEnum.BUY.value()));
            logger.info("Ranking: " + new RankingServiceImpl().prepareRankings(instructions));

        } else {
            logger.log(Level.INFO, "Unable to read the instructions file, please check the path");
        }

        logger.log(Level.INFO,"End of GenerateDailyReport");
    }
}
