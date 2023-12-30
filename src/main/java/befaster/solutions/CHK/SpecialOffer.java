package befaster.solutions.CHK;

import org.javatuples.Pair;

public class SpecialOffer {
    private int amount;
    private int price;
    private Pair<String, Integer> skuFreeAmount;

    public SpecialOffer() {
        this.price = -Integer.MIN_VALUE;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Pair<String, Integer> getSkuFreeAmount() {
        return skuFreeAmount;
    }

    public void setSkuFreeAmount(Pair<String, Integer> skuFreeAmount) {
        this.skuFreeAmount = skuFreeAmount;
    }

    @Override
    public String toString() {
        return "SpecialOffer{" +
                "amount=" + amount +
                ", price=" + price +
                ", skuFreeAmount=" + skuFreeAmount +
                '}';
    }
}

