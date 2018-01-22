package org.jpmc.forex.trade.services;

import org.jpm.trade.report.enums.TransactionTypeEnum;
import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.services.RankingService;
import org.jpm.trade.report.services.impl.RankingServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankingServiceTest {

    List<Instruction> inputList;

    RankingService rankingService;

    @Before
    public void setUp() {
        inputList = new ArrayList<Instruction>();
        inputList.add(new Instruction("foo","B",new Double("100"),
                "AED","2017-01-01","2017-01-01",
                10,new Double("120")));
        inputList.add(new Instruction("bar","B",new Double("10"),
                "BAR","2017-01-01","2017-01-01",
                20,new Double("120")));

        inputList.add(new Instruction("bar","S",new Double("100"),
                "AED","2017-01-01","2017-01-01",
                10,new Double("120")));
        inputList.add(new Instruction("foo","S",new Double("10"),
                "BAR","2017-01-01","2017-01-01",
                20,new Double("120")));
        rankingService =  new RankingServiceImpl();
    }

    @Test
    public void testPrepareRankings() {

        Map<String, Map<String,Double>> rankings = rankingService.prepareRankings(inputList);
        System.out.println(rankings);
        Assert.assertNotNull(rankings);
        Assert.assertEquals(2,rankings.size());
        Assert.assertEquals(2,rankings.get(TransactionTypeEnum.BUY.description()).size());
        Assert.assertEquals(2,rankings.get(TransactionTypeEnum.SELL.description()).size());

        Assert.assertEquals(120000.0,rankings.get(TransactionTypeEnum.BUY.description()).get("foo").doubleValue(),0);
        Assert.assertEquals(24000.0,rankings.get(TransactionTypeEnum.BUY.description()).get("bar").doubleValue(),0);

        Assert.assertEquals(24000.0,rankings.get(TransactionTypeEnum.SELL.description()).get("foo").doubleValue(),0);
        Assert.assertEquals(120000.0,rankings.get(TransactionTypeEnum.SELL.description()).get("bar").doubleValue(),0);

    }

    @After
    public void tearDown() {
        inputList = null;
        rankingService = null;

    }
}
