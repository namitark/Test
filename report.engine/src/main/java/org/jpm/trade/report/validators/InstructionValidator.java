package org.jpm.trade.report.validators;

import static org.jpm.trade.report.constants.TradeConstants.BLANK_SPACE;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.jpm.trade.report.model.Instruction;


/**
 * Class is used to validate the Instruction details given in input file.
 *
 */
public class InstructionValidator {

    /**
     * Method validates each parameter of the Instruction
     * @param instr
     * @return isValid
     */
    public static Boolean validate(Instruction instr) {

        Boolean isValid = true;

        if (null == instr.getCurrency() || instr.getCurrency().trim().equals(BLANK_SPACE))
            return false;
        if (null == instr.getEntity() || instr.getEntity().trim().equals(BLANK_SPACE))
            return false;
        if (null == instr.getDirection() || instr.getDirection().trim().equals(BLANK_SPACE))
            return false;
        if (instr.getNoOfUnits() <= 0)
            return false;
        if (null == instr.getRate() || instr.getRate().isNaN())
            return false;
        if (null == instr.getPricePerUnit() || instr.getPricePerUnit().isNaN())
            return false;

        try {
            LocalDate.parse(instr.getInstructionDate());
            LocalDate.parse(instr.getSettlementDate());
        } catch (DateTimeParseException dpe) {
            isValid = false;
        }

        return isValid;
    }
}
