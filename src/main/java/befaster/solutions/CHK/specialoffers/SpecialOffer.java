package befaster.solutions.CHK.specialoffers;

import org.javatuples.Pair;

import java.util.ArrayList;

public class SpecialOffer {
    private String requiredSKU;
    private int amountRequired;
    private int totalPrice;
    private String freeSKU;
    private int freeAmount;
    private ArrayList<String> requiredSKUGroup;

    public SpecialOffer() {
    }

    public SpecialOffer(String requiredSKU, int amountRequired, int totalPrice) {
        this.requiredSKU = requiredSKU;
        this.amountRequired = amountRequired;
        this.totalPrice = totalPrice;
    }

    public SpecialOffer(String requiredSKU, int amountRequired, String freeSKU, int freeAmount) {
        this.requiredSKU = requiredSKU;
        this.amountRequired = amountRequired;
        this.freeSKU = freeSKU;
        this.freeAmount = freeAmount;
    }

    public int getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(int amountRequired) {
        this.amountRequired = amountRequired;
    }

    public int getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(int freeAmount) {
        this.freeAmount = freeAmount;
    }

    public String getRequiredSKU() {
        return requiredSKU;
    }

    public void setRequiredSKU(String requiredSKU) {
        this.requiredSKU = requiredSKU;
    }

    public String getFreeSKU() {
        return freeSKU;
    }

    public void setFreeSKU(String freeSKU) {
        this.freeSKU = freeSKU;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

