package org.jpm.trade.report.enums;

/**
 * Enum defines the direction(Buy or Sell) of an trade Instruction
 */
public enum TransactionTypeEnum {

    BUY("B", "Outgoing Rankings:"),
    SELL("S", "Incoming Rankings:");

    private String instr;

    private String description;

    TransactionTypeEnum(String instr, String description) {
        this.instr = instr;
        this.description = description;
    }

    public String value() {
        return instr;
    }

    public String description() {
        return description;
    }
}
