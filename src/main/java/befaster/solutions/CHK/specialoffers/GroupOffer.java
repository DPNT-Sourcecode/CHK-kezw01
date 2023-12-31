package befaster.solutions.CHK.specialoffers;

import java.util.ArrayList;

public class GroupOffer extends SpecialOffer
{
    private ArrayList<String> groupSKUs;
    private int requiredAmount;
    private int price;

    public GroupOffer(ArrayList<String> groupSKUs, int requiredAmount, int price) {
        this.groupSKUs = groupSKUs;
        this.requiredAmount = requiredAmount;
        this.price = price;
    }

    public ArrayList<String> getGroupSKUs() {
        return groupSKUs;
    }

    public void setGroupSKUs(ArrayList<String> groupSKUs) {
        this.groupSKUs = groupSKUs;
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
        return "GroupOffer{" +
                "groupSKUs=" + groupSKUs +
                ", requiredAmount=" + requiredAmount +
                ", price=" + price +
                '}';
    }
}


