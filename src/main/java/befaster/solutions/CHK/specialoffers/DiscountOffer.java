package befaster.solutions.CHK.specialoffers;

public class DiscountOffer extends SpecialOffer
{
    private String requiredSKU;
    private int requiredAmount;
    private int price;

    public DiscountOffer(String requiredSKU, int requiredAmount, int price) {
        this.requiredSKU = requiredSKU;
        this.requiredAmount = requiredAmount;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DiscountOffer{" +
                "requiredSKU='" + requiredSKU + '\'' +
                ", requiredAmount=" + requiredAmount +
                ", price=" + price +
                '}';
    }
}
