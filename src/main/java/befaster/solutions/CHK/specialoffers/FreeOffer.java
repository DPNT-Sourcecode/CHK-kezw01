package befaster.solutions.CHK.specialoffers;

public class FreeOffer extends SpecialOffer
{
    private String requiredSKU;
    private int requiredAmount;
    private String freeSKU;
    private int freeAmount;

    public FreeOffer(String requiredSKU, int requiredAmount, String freeSKU, int freeAmount) {
        this.requiredSKU = requiredSKU;
        this.requiredAmount = requiredAmount;
        this.freeSKU = freeSKU;
        this.freeAmount = freeAmount;
    }

    public String getRequiredSKU() {
        return requiredSKU;
    }

    public void setRequiredSKU(String requiredSKU) {
        this.requiredSKU = requiredSKU;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public String getFreeSKU() {
        return freeSKU;
    }

    public void setFreeSKU(String freeSKU) {
        this.freeSKU = freeSKU;
    }

    public int getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(int freeAmount) {
        this.freeAmount = freeAmount;
    }

    @Override
    public String toString() {
        return "FreeOffer{" +
                "requiredSKU='" + requiredSKU + '\'' +
                ", requiredAmount=" + requiredAmount +
                ", freeSKU='" + freeSKU + '\'' +
                ", freeAmount=" + freeAmount +
                '}';
    }
}
