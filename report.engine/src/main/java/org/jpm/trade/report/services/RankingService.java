package org.jpm.trade.report.services;

import java.util.List;
import java.util.Map;

import org.jpm.trade.report.model.Instruction;

public interface RankingService {

    Map<String, Map<String,Double>> prepareRankings(List<Instruction> instructions) ;

}
