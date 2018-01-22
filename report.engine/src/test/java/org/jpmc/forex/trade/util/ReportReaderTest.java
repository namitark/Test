package org.jpmc.forex.trade.util;

import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.util.ReportReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ReportReaderTest {

    public static String TEST_FILE_PATH = "src/test/resources/SampleTradeData.csv";
    public static String INVALID_FILE_PATH = "src/test/resources/sampleTradeData12.csv";

    @Test
    public void testProcessInstructions() {
        List<Instruction> instructions = ReportReader.processData(TEST_FILE_PATH);
        Assert.assertNotNull(instructions);
        Assert.assertEquals(15, instructions.size());
    }

    @Test
    public void testProcessInstructionsInvalidPath() {
        List<Instruction> instructions = ReportReader.processData(INVALID_FILE_PATH);
        Assert.assertNull(instructions);
    }
}
