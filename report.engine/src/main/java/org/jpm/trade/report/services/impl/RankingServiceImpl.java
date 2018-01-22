package org.jpm.trade.report.services.impl;

import org.jpm.trade.report.enums.TransactionTypeEnum;
import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.services.RankingService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RankingServiceImpl implements RankingService {

    private static Logger logger = Logger.getLogger("RankingServiceImpl");

    /**
     * Method to prepare Settled Amount Map for each entity based for Incoming/Outgoing.
     * @param instructions
     * @return
     */
    public Map<String, Map<String,Double>> prepareRankings(List<Instruction> instructions) {
        Map<String, Map<String,Double>> entityRankingsMap = new HashMap<>();
        logger.info("Begin prepareRankings for Incoming and Outgoing Ranks");

        entityRankingsMap.put(TransactionTypeEnum.SELL.description(), getEntityRankingsMap(filterByDirection(instructions, TransactionTypeEnum.SELL.value())));

        entityRankingsMap.put(TransactionTypeEnum.BUY.description(),  getEntityRankingsMap(filterByDirection(instructions, TransactionTypeEnum.BUY.value())));
        logger.info("End prepareRankings for Incoming and Outgoing Ranks");
        return entityRankingsMap;

    }

    /**
     * Return a Map with Entity and corresponding Settled USD Amount.
     * @param tradeInstructions
     * @return
     */
    private static Map<String, Double> getEntityRankingsMap(List<Instruction> tradeInstructions) {
        Map<String, Double> entityRankingsMap = new HashMap<>();
        Map<String, Double> sortedByDirectionMap = new LinkedHashMap<>();

        tradeInstructions.forEach(trade -> {
            if(entityRankingsMap.containsKey(trade.getEntity())) {
                entityRankingsMap.put(trade.getEntity(), Double.sum(entityRankingsMap.get(trade.getEntity()), trade.getPricePerUnit()*trade.getNoOfUnits()*trade.getRate()));
            } else {
                entityRankingsMap.put(trade.getEntity(), trade.getPricePerUnit()*trade.getNoOfUnits()*trade.getRate());
            }

        });

        entityRankingsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(x -> sortedByDirectionMap.put(x.getKey(), x.getValue()));

        return sortedByDirectionMap;
    }

    /**
     * Return a new list based on the Buy/Sell indicator
     * @param tradesList
     * @param direction
     * @return
     */
    private List<Instruction> filterByDirection(List<Instruction> tradesList, String direction) {
        return tradesList.stream().filter(trade -> (trade != null && trade.getDirection().equals(direction))).collect(Collectors.toList());

    }
}
