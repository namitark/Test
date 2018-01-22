package org.jpm.trade.report.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jpm.trade.report.calendars.MonToFridayCalendar;
import org.jpm.trade.report.calendars.SunToThursCalendar;
import org.jpm.trade.report.constants.TradeConstants;
import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.services.SettlementService;
import org.jpm.trade.report.util.DateConverter;

public class SettlementServiceImpl implements SettlementService {

	private static Logger logger = Logger.getLogger("SettlementServiceImpl");

	/**
	 *This method is generating Trading Settlement Report depending on Buy/Sell
	 * @param instructions
	 * @param direction
	 * @return
	 */
	public Map<String, Double> generateSettlementReport(List<Instruction> instructions, String direction) {

		Map<String, Double> settledAmountMap = new HashMap<>();
		Map<String, Double> sortedSettlementByDate = new LinkedHashMap<>();
		filterByTransaction(instructions, direction).forEach(trade -> {
			processInstruction(trade, settledAmountMap);
		});
		
		settledAmountMap.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByKey()).forEachOrdered(x->sortedSettlementByDate.put(x.getKey(), x.getValue()));
		return sortedSettlementByDate;
	}

	/**
	 * Method to prepare a map with instruction date and settled amount.
	 * @param instruction
	 * @param settledAmountMap
	 */
	private void processInstruction(Instruction instruction, Map<String,Double> settledAmountMap) {
		Double amount = instruction.getRate() * instruction.getNoOfUnits() * instruction.getPricePerUnit();

		try {
			LocalDate settlementDate = DateConverter.convertStringToDate(instruction.getSettlementDate());
			LocalDate businessDate = null;
			try {
				if(null != instruction && null != instruction.getCurrency()){
					if(TradeConstants.AED_CUR.equalsIgnoreCase(instruction.getCurrency())||
							TradeConstants.SAR_CUR.equalsIgnoreCase(instruction.getCurrency())) {
						businessDate = new SunToThursCalendar().getBusinessDay(settlementDate);
					} else {
						businessDate = new MonToFridayCalendar().getBusinessDay(settlementDate);
					}
				}

			} catch (DateTimeParseException e) {
				logger.log(Level.SEVERE, "Exception while parsing settlement date" +e);
				return;
			}

			String businessDateStr = DateConverter.convertDateToString(businessDate);

			if(settledAmountMap.containsKey(businessDateStr)){
				settledAmountMap.put(businessDateStr, Double.sum(settledAmountMap.get(businessDateStr), amount));
			}
			else{
				settledAmountMap.put(businessDateStr, amount);
			}
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return a new list based on the Buy/Sell indicator
	 * 
	 * @param instructions
	 * @param direction
	 * @return
	 */
	private List<Instruction> filterByTransaction(List<Instruction> instructions, String direction) {
		return instructions.stream().filter(trade -> (trade != null && trade.getDirection().equals(direction)))
				.collect(Collectors.toList());

	}
}
