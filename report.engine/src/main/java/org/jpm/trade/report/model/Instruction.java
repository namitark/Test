package org.jpm.trade.report.model;

/**
 * Model class for an Instruction
 */
public class Instruction {

    private String entity;

    private String direction;

    private Double fxRate;

    private String currency;

    private String instructionDate;

    private String settlementDate;

    private int noOfUnits;

    private Double pricePerUnit;

    public Instruction() {

    }

    public Instruction(String entity, String direction, Double rate, String currency,
                       String instructionDate, String settlementDate, int noOfUnits, Double pricePerUnit) {
        this.entity = entity;
        this.direction = direction;
        this.fxRate = rate;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.noOfUnits = noOfUnits;
        this.pricePerUnit = pricePerUnit;
    }

    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public Double getRate() {
        return fxRate;
    }
    public void setRate(Double rate) {
        this.fxRate = rate;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getInstructionDate() {
        return instructionDate;
    }
    public void setInstructionDate(String instructionDate) {
        this.instructionDate = instructionDate;
    }
    public String getSettlementDate() {
        return settlementDate;
    }
    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }
    public int getNoOfUnits() {
        return noOfUnits;
    }
    public void setNoOfUnits(int noOfUnits) {
        this.noOfUnits = noOfUnits;
    }
    public Double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     *
     * @return Instruction details string
     */
    @Override
    public String toString(){
        return "\n Entity: " +entity +" Buy/Sell: "+ direction +" AgreedFx: " +fxRate
                +" Currency: " + currency +"InstructionDate: " + instructionDate
                +" SettlementDate: " +settlementDate +" Units: "+ noOfUnits + " Price Per Unit:" +pricePerUnit +"\n";
    }
}
