package org.jpm.trade.report.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jpm.trade.report.constants.TradeConstants;
import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.validators.InstructionValidator;

/**
 * Purpose of this class is to read the file from particular location and convert the input records into a List of
 * Instructions
 */
public class ReportReader {

    private static final Logger logger = Logger.getLogger("ReportReader");

    /**
     * Reads all the records from csv file into TradeInstruction
     * @param filePath
     * @return
     */
    public static List<Instruction> processData(String filePath) {

        List<Instruction> inputList = new ArrayList<Instruction>();
        if(null != filePath && !filePath.trim().equalsIgnoreCase(TradeConstants.BLANK_SPACE))
        try(Stream<String> stream = Files.lines(Paths.get(filePath))){
        	inputList = stream.skip(0).map(mapToItem).collect(Collectors.toList());
        }
        catch(IOException ie){
            logger.log(Level.SEVERE,"Unable to read the file");
            return null;
        }

        return inputList ;
    }

    /**
     * Map columns to fields in object
     */
    private static Function<String, Instruction> mapToItem = (line) -> {
        String[] p = line.split(",");
        Instruction instruction = new Instruction();
        instruction.setEntity(p[0]);
        instruction.setDirection(p[1]);
        try {
            instruction.setRate(Double.valueOf(p[2]));
        } catch(Exception e) {
            instruction.setRate(null);
        }
        instruction.setCurrency(p[3]);
        instruction.setInstructionDate(p[4]);
        instruction.setSettlementDate(p[5]);
        try {
            instruction.setNoOfUnits(Integer.valueOf(p[6]));
        } catch(Exception e) {
            instruction.setNoOfUnits(0);
        }
        try {
            instruction.setPricePerUnit(Double.valueOf(p[7]));
        } catch(Exception e) {
            instruction.setPricePerUnit(null);
        }

        return (InstructionValidator.validate(instruction))?instruction:null;
    };

}
