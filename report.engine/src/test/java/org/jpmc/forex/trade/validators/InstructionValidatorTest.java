package org.jpmc.forex.trade.validators;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.jpm.trade.report.model.Instruction;
import org.jpm.trade.report.validators.InstructionValidator;

public class InstructionValidatorTest {

    Instruction invalidCurrencyInstruction;
    Instruction invalidEntityInstruction;
    Instruction invalidDirectionInstruction;
    Instruction invalidInstrDateInstruction;
    Instruction invalidSettleDateInstruction;
    Instruction invalidNoOfUnitsInstruction;
    Instruction validInstruction;

    @Before
    public void setUp(){

        validInstruction = new Instruction("foo","B",new Double("100"),
                "AED","2017-01-01","2017-01-01",
                10,new Double("120"));

        invalidCurrencyInstruction = new Instruction("foo","B",new Double("100"),
                "","2017-01-01","2017-01-01",
                10,new Double("120"));

        invalidEntityInstruction = new Instruction("","B",new Double("100"),
                "SAR","2017-01-01","2017-01-01",
                10,new Double("120"));

        invalidDirectionInstruction = new Instruction("bar","",new Double("100"),
                "SAR","2017-01-01","2017-01-01",
                10,new Double("120"));

        invalidInstrDateInstruction = new Instruction("bar","S",new Double("100"),
                "SAR","2017-21-21","2017-01-01",
                10,new Double("120"));

        invalidSettleDateInstruction = new Instruction("bar","S",new Double("100"),
                "SAR","2017-01-02","2017-31-31",
                10,new Double("120"));

        invalidNoOfUnitsInstruction = new Instruction("bar","S",new Double("100"),
                "SAR","2017-01-01","2017-01-01",
                -10,new Double("120"));
    }


    @Test
    public void testValidate() {

        assertEquals(Boolean.valueOf(false) , InstructionValidator.validate(invalidCurrencyInstruction));
        assertEquals(Boolean.valueOf(false) , InstructionValidator.validate(invalidEntityInstruction));
        assertEquals(Boolean.valueOf(false) , InstructionValidator.validate(invalidDirectionInstruction));
        assertEquals(Boolean.valueOf(false) , InstructionValidator.validate(invalidInstrDateInstruction));
        assertEquals(Boolean.valueOf(false) , InstructionValidator.validate(invalidSettleDateInstruction));
        assertEquals(Boolean.valueOf(false) , InstructionValidator.validate(invalidNoOfUnitsInstruction));

        assertEquals(Boolean.valueOf(true) , InstructionValidator.validate(validInstruction));
    }
}
