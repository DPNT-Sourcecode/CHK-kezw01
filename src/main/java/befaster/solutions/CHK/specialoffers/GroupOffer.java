package befaster.solutions.CHK.specialoffers;

import java.util.ArrayList;

public class GroupOffer extends SpecialOffer
{
    private ArrayList<String> groupSKUs;

    public GroupOffer(ArrayList<String> groupSKUs) {
        this.groupSKUs = groupSKUs;
    }

    public ArrayList<String> getGroupSKUs() {
        return groupSKUs;
    }

    public void setGroupSKUs(ArrayList<String> groupSKUs) {
        this.groupSKUs = groupSKUs;
    }

    @Override
    public String toString() {
        return "GroupOffer{" +
                "groupSKUs=" + groupSKUs +
                '}';
    }
}

