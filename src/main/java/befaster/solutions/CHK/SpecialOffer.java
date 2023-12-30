package befaster.solutions.CHK;

import org.javatuples.Pair;

public class SpecialOffer {
    private String skuRequired;
    private int totalPrice;
    private String skuFree;
    private int freeAmount
    private

    public SpecialOffer(String skuRequired, int totalPrice) {
        this.skuRequired = skuRequired;
        this.totalPrice = totalPrice;
    }

    public SpecialOffer(String skuRequired, String skuFree, int freeAmount) {
        this.skuRequired = skuRequired;
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
