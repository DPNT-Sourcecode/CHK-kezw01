package befaster.solutions.CHK;

import org.javatuples.Pair;

public class SpecialOffer {
    private String skuRequired;
    private int amountRequired;
    private int totalPrice;
    private String skuFree;
    private int freeAmount;

    public int getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(int amountRequired) {
        this.amountRequired = amountRequired;
    }

    public SpecialOffer(String skuRequired, int amountRequired, int totalPrice) {
        this.skuRequired = skuRequired;
        this.amountRequired = amountRequired;
        this.totalPrice = totalPrice;
    }

    public SpecialOffer(String skuRequired, int amountRequired, String skuFree, int freeAmount) {
        this.skuRequired = skuRequired;
        this.amountRequired = amountRequired;
        this.skuFree = skuFree;
        this.freeAmount = freeAmount;
    }

    public String getSkuFree() {
        return skuFree;
    }

    public void setSkuFree(String skuFree) {
        this.skuFree = skuFree;
    }

    public int getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(int freeAmount) {
        this.freeAmount = freeAmount;
    }

    public String getSkuRequired() {
        return skuRequired;
    }

    public void setSkuRequired(String skuRequired) {
        this.skuRequired = skuRequired;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}


