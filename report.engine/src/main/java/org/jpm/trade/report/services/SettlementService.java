package org.jpm.trade.report.services;

import java.util.List;
import java.util.Map;

import org.jpm.trade.report.model.Instruction;

public interface SettlementService {
    Map<String,Double> generateSettlementReport(List<Instruction> instructions, String direction);
}
