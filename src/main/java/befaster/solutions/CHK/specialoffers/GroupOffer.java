package befaster.solutions.CHK.specialoffers;

import java.util.ArrayList;

public class GroupOffer extends SpecialOffer
{
    private ArrayList<String> groupSKUs;
    private int requiredCount;
    private int price;

    public GroupOffer(ArrayList<String> groupSKUs, int requiredCount, int price) {
        this.groupSKUs = groupSKUs;
        this.requiredCount = requiredCount;
        this.price = price;
    }

    public ArrayList<String> getGroupSKUs() {
        return groupSKUs;
    }

    public void setGroupSKUs(ArrayList<String> groupSKUs) {
        this.groupSKUs = groupSKUs;
    }

    public int getRequiredCount() {
        return requiredCount;
    }

    public void setRequiredCount(int requiredCount) {
        this.requiredCount = requiredCount;
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
                ", requiredCount=" + requiredCount +
                ", price=" + price +
                '}';
    }
}

