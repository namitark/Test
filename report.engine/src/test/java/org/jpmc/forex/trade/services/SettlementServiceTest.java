package org.jpmc.forex.trade.services;

import org.jpm.trade.report.enums.TransactionTypeEnum;
import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.services.SettlementService;
import org.jpm.trade.report.services.impl.RankingServiceImpl;
import org.jpm.trade.report.services.impl.SettlementServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SettlementServiceTest {

    List<Instruction> inputList;

    SettlementService settlementService;

    @Before
    public void setUp() {
        inputList = new ArrayList<Instruction>();
        inputList.add(new Instruction("foo","B",new Double("100"),
                "AED","2017-09-01","2017-09-01",
                10,new Double("120")));
        inputList.add(new Instruction("bar","B",new Double("10"),
                "BAR","2017-09-01","2017-09-02",
                40,new Double("120")));

        inputList.add(new Instruction("bar","S",new Double("100"),
                "AED","2017-09-01","2017-09-02",
                10,new Double("120")));
        inputList.add(new Instruction("foo","S",new Double("10"),
                "BAR","2017-09-01","2017-09-06",
                100,new Double("120")));
        settlementService =  new SettlementServiceImpl();
    }

    @Test
    public void testPrepareRankings() {

        Map<String,Double> incomingReport = settlementService.generateSettlementReport(inputList,TransactionTypeEnum.SELL.value());
        System.out.println("Incoming Report:" + incomingReport);
        Map<String,Double> outgoingReport = settlementService.generateSettlementReport(inputList,TransactionTypeEnum.BUY.value());
        System.out.println("Outgoing Report:" + outgoingReport);
        Assert.assertNotNull(incomingReport);
        Assert.assertNotNull(outgoingReport);
        Assert.assertEquals(2,incomingReport.size());
        Assert.assertEquals(2,outgoingReport.size());
        Assert.assertEquals(120000.0,incomingReport.get("2017-09-06").doubleValue(),0);
        Assert.assertEquals(120000.0,incomingReport.get("2017-09-03").doubleValue(),0);

        Assert.assertEquals(120000.0,outgoingReport.get("2017-09-03").doubleValue(),0);
        Assert.assertEquals(48000.0,outgoingReport.get("2017-09-04").doubleValue(),0);
    }

    @After
    public void tearDown() {
        inputList = null;
        settlementService = null;

    }
}
