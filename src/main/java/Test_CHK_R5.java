package befaster.solutions.CHK;

import befaster.solutions.CHK.specialoffers.DiscountOffer;
import befaster.solutions.CHK.specialoffers.FreeOffer;
import befaster.solutions.CHK.specialoffers.GroupOffer;
import befaster.solutions.CHK.specialoffers.SpecialOffer;
import org.javatuples.Pair;

import java.util.*;

public class Test_CHK_R5 {

    public static void main(String[] args)
    {
        System.out.println(checkout("STX"));
    }

    public static Integer checkout(String skus) {
        if (!skus.isEmpty())
        {
            if (skus.matches("[A-Z]+"))
            {
                int totalPrice = 0;

                //Map with the price for each SKU
                HashMap<String, Integer> mapSKUsPrice = getSinglePrices();

                //Map with special offers
                ArrayList<SpecialOffer> specialOffers = getSpecialOffers();

                //1. Remove free items
                String skusFreeRemoved = removeFreeSKUs(skus, specialOffers);

                //2. Check group offers
                skusFreeRemoved = "STX";
                Pair<String, Integer> skusGroupsRemovedAndPrice = removeGroupOffers(skusFreeRemoved, specialOffers, mapSKUsPrice);
                String skusGroupsRemoved = skusGroupsRemovedAndPrice.getValue0();
                int currentPrice = skusGroupsRemovedAndPrice.getValue1();

                HashMap<String, Integer> mapSKUsCounter = getMapSKUSCounter(skusFreeRemoved);

                //A pair with a filtered map of SKUs and respective amounts (with group offers applied), and an integer representing the current total price
                Pair<HashMap<String, Integer>, Integer> pairSKUsAmountAndTotalPrice = checkForGroupOffers(mapSKUsCounter, specialOffers);

                //Calculate price
                for (String currentSku : mapSKUsCounter.keySet())
                {
                    int amount = mapSKUsCounter.get(currentSku);

                    while (amount > 0)
                    {
                        if (!specialOffers.isEmpty())
                        {
                            DiscountOffer bestDiscountOffer = getBestDiscountOffer(currentSku, amount, specialOffers);

                            //Special offer found
                            if (bestDiscountOffer != null)
                            {
                                int offerAmount = bestDiscountOffer.getRequiredAmount();
                                int offerPrice = bestDiscountOffer.getPrice();

                                //Check if there is enough items to apply the special offer
                                if (amount >= offerAmount)
                                {
                                    totalPrice += offerPrice;
                                    amount -= offerAmount;
                                }
                                //Not enough items to apply special offer, calculate using the "normal" price
                                else
                                {
                                    int price = mapSKUsPrice.get(currentSku);
                                    totalPrice += price;
                                    amount--;
                                }
                            }
                            //No special offers for this sku
                            else
                            {
                                //Apply normal price
                                int price = mapSKUsPrice.get(currentSku);
                                totalPrice += price;
                                amount--;
                            }
                        }
                        else
                        {
                            //Apply normal price
                            int price = mapSKUsPrice.get(currentSku);
                            totalPrice += price;
                            amount--;
                        }
                    }
                }
                return totalPrice;
            }
            else
            {
                return -1;
            }
        }
        return 0;
    }

    private static Pair<HashMap<String, Integer>, Integer> checkForGroupOffers(HashMap<String, Integer> mapCurrentAmountSKUs, ArrayList<SpecialOffer> specialOffers)
    {
        int totalPrice = 0;

        boolean tryToApplyOfferAgain = false;
        while (true)
        {
            tryToApplyOfferAgain = false;

            for (SpecialOffer specialOffer : specialOffers)
            {
                //Check only DiscountOffer objects
                if (specialOffer instanceof GroupOffer groupOffer)
                {
                    int requiredSKUsAmount = groupOffer.getRequiredCount();
                    int matchSKUsAmount = 0;

                    HashMap<String, Integer> mapSKUsToDecrease = new HashMap<>();

                    //Loop through SKUs in input SKUs counter map
                    for (String currentSKUInput : mapCurrentAmountSKUs.keySet())
                    {
                        //SKU is in a group offer
                        if (groupOffer.getGroupSKUs().contains(currentSKUInput))
                        {
                            int currentSKUInputAmount = mapCurrentAmountSKUs.get(currentSKUInput);

                            //For each SKU occurrence in the input
                            for (int i = 0; i < currentSKUInputAmount; i++)
                            {
                                matchSKUsAmount++;

                                mapSKUsToDecrease.put(currentSKUInput, i + 1);

                                //Apply group offer
                                if (matchSKUsAmount == requiredSKUsAmount)
                                {
                                    //Update price
                                    totalPrice += groupOffer.getPrice();

                                    //Decrease the counter of the SKUs in the map
                                    for (String skuToDecrease : mapSKUsToDecrease.keySet())
                                    {
                                        int currentAmount = mapCurrentAmountSKUs.get(currentSKUInput);
                                        int amountToDecrease = mapSKUsToDecrease.get(skuToDecrease);
                                        int updateAmount = currentAmount - amountToDecrease;
                                        mapCurrentAmountSKUs.put(skuToDecrease, updateAmount);
                                    }
                                    mapSKUsToDecrease.clear();
                                    matchSKUsAmount = 0;
                                    tryToApplyOfferAgain = true;
                                }
                            }
                        }
                    }
                }
            }

            if (!tryToApplyOfferAgain)
            {
                break;
            }
        }

        return new Pair<>(mapCurrentAmountSKUs, totalPrice);
    }

    private static DiscountOffer getBestDiscountOffer(String skuRequired, int amountOfItems, ArrayList<SpecialOffer> specialOffers)
    {
        float bestSingleItemPrice = Integer.MAX_VALUE;

        DiscountOffer bestDiscountOffer = null;

        for (SpecialOffer specialOffer : specialOffers)
        {
            //Check only DiscountOffer objects
            if (specialOffer instanceof DiscountOffer discountOffer)
            {
                if (skuRequired.equals(discountOffer.getRequiredSKU()))
                {
                    int offerAmount = discountOffer.getRequiredAmount();
                    int offerPrice = discountOffer.getPrice();

                    float singleItemPrice = (float) offerPrice / offerAmount;

                    if (amountOfItems >= offerAmount && singleItemPrice < bestSingleItemPrice)
                    {
                        bestSingleItemPrice = singleItemPrice;
                        bestDiscountOffer = discountOffer;
                    }
                }
            }
        }

        return bestDiscountOffer;
    }

    private static HashMap<String, Integer> getSinglePrices()
    {
        HashMap<String, Integer> mapSKUsPrice = new HashMap<>();

        mapSKUsPrice.put("A", 50);
        mapSKUsPrice.put("B", 30);
        mapSKUsPrice.put("C", 20);
        mapSKUsPrice.put("D", 15);
        mapSKUsPrice.put("E", 40);
        mapSKUsPrice.put("F", 10);
        mapSKUsPrice.put("G", 20);
        mapSKUsPrice.put("H", 10);
        mapSKUsPrice.put("I", 35);
        mapSKUsPrice.put("J", 60);
        mapSKUsPrice.put("K", 70);
        mapSKUsPrice.put("L", 90);
        mapSKUsPrice.put("M", 15);
        mapSKUsPrice.put("N", 40);
        mapSKUsPrice.put("O", 10);
        mapSKUsPrice.put("P", 50);
        mapSKUsPrice.put("Q", 30);
        mapSKUsPrice.put("R", 50);
        mapSKUsPrice.put("S", 20);
        mapSKUsPrice.put("T", 20);
        mapSKUsPrice.put("U", 40);
        mapSKUsPrice.put("V", 50);
        mapSKUsPrice.put("W", 20);
        mapSKUsPrice.put("X", 17);
        mapSKUsPrice.put("Y", 20);
        mapSKUsPrice.put("Z", 21);

        return mapSKUsPrice;
    }

    private static ArrayList<SpecialOffer> getSpecialOffers()
    {
        ArrayList<SpecialOffer> offers = new ArrayList<>();

        offers.add(new DiscountOffer("A", 3, 130));
        offers.add(new DiscountOffer("A", 5, 200));
        offers.add(new DiscountOffer("B", 2, 45));
        offers.add(new FreeOffer("E", 2, "B",1));
        offers.add(new FreeOffer("F", 2, "F",1));
        offers.add(new DiscountOffer("H", 5, 45));
        offers.add(new DiscountOffer("H", 10, 80));
        offers.add(new DiscountOffer("K", 2, 120));
        offers.add(new FreeOffer("N", 3, "M",1));
        offers.add(new DiscountOffer("P", 5, 200));
        offers.add(new DiscountOffer("Q", 3, 80));
        offers.add(new FreeOffer("R", 3, "Q",1));
        offers.add(new FreeOffer("U", 3, "U",1));
        offers.add(new DiscountOffer("V", 2, 90));
        offers.add(new DiscountOffer("V", 3, 130));

        ArrayList<String> groupSKUs = new ArrayList<>();
        groupSKUs.add("S");
        groupSKUs.add("T");
        groupSKUs.add("X");
        groupSKUs.add("Y");
        groupSKUs.add("Z");
        GroupOffer groupOffer = new GroupOffer(groupSKUs, 3, 45);
        offers.add(groupOffer);

        return offers;
    }

    private static HashMap<String, Integer> getMapSKUSCounter(String skus)
    {
        HashMap<String, Integer> mapSKUsCounter = new HashMap<String, Integer>();

        for (int i = 0; i < skus.length(); i++)
        {
            String sku = String.valueOf(skus.charAt(i));

            if (mapSKUsCounter.get(sku) != null)
            {
                int count = mapSKUsCounter.get(sku);
                count++;
                mapSKUsCounter.put(sku, count);
            }
            else {
                mapSKUsCounter.put(sku, 1);
            }
        }

        return mapSKUsCounter;
    }

    /*
    * Returns the filtered string without free items
    * */
    private static String removeFreeSKUs(String skus, ArrayList<SpecialOffer> specialOffers)
    {
        ArrayList<String> skusToKeep = new ArrayList<>();

        boolean tryToApplyOfferAgain = false;
        do {
            tryToApplyOfferAgain = false;

            for (SpecialOffer specialOffer : specialOffers) {

                //Check only FreeOffer objects
                if (specialOffer instanceof FreeOffer freeOffer) {
                    String offerRequiredSKU = freeOffer.getRequiredSKU();
                    String offerFreeSKU = freeOffer.getFreeSKU();

                    if (skus.contains(offerRequiredSKU) && skus.contains(offerFreeSKU)) {
                        int offerRequiredCount = freeOffer.getRequiredAmount();
                        int offerFreeCount = freeOffer.getFreeAmount();

                        int requiredSKUCount = countSKUs(skus, offerRequiredSKU);
                        int freeSKUCount = countSKUs(skus, offerFreeSKU);

                        //Able to apply offer
                        if (requiredSKUCount >= offerRequiredCount && freeSKUCount >= offerFreeCount) {
                            //Track which skus should be removed
                            for (int i = 0; i < offerFreeCount; i++) {
                                skus = skus.replaceFirst(offerFreeSKU, "");
                            }
                            for (int i = 0; i < offerRequiredCount; i++) {
                                skusToKeep.add(offerRequiredSKU);
                                skus = skus.replaceFirst(offerRequiredSKU, "");
                            }
                            tryToApplyOfferAgain = true;
                        }
                    }
                }
            }

        } while (tryToApplyOfferAgain);

        StringBuilder skusBuilder = new StringBuilder(skus);

        for (String sku : skusToKeep)
        {
            skusBuilder.append(sku);
        }
        skus = skusBuilder.toString();

        return skus;
    }


    /*
     * Returns the filtered string without items covered by group offers, and the current total price
     * */
    private static Pair<String, Integer> removeGroupOffers(String skus, ArrayList<SpecialOffer> specialOffers, HashMap<String, Integer> mapSKUsPrice)
    {
        skus = "XXXTS";
        int totalPrice = 0;

        String sortedSKUs = sortSKUsDescendingPrice(mapSKUsPrice, skus);

        boolean tryToApplyOfferAgain = false;
        do {
            tryToApplyOfferAgain = false;

            for (SpecialOffer specialOffer : specialOffers) {

                //Check only GroupOffer objects
                if (specialOffer instanceof GroupOffer groupOffer)
                {
                    ArrayList<String> offerRequiredSKUs = groupOffer.getGroupSKUs();
                    int offerRequiredCount = groupOffer.getRequiredCount();

                    int requiredSKUCount = 0;
                    ArrayList<String> skusToRemove = new ArrayList<>();

                    // Check if there is enough SKUs to apply the offer
                    for (int i = 0; i < sortedSKUs.length(); i++)
                    {
                        String currentSKU = String.valueOf(sortedSKUs.charAt(i));
                        if (offerRequiredSKUs.contains(currentSKU))
                        {
                            requiredSKUCount++;
                            skusToRemove.add(currentSKU);
                        }

                        // Apply offer
                        if (requiredSKUCount == offerRequiredCount)
                        {


                            tryToApplyOfferAgain = true;
                        }
                    }

                    System.out.println("dsd");
                }
            }

        } while (tryToApplyOfferAgain);

        Pair<String, Integer> p = new Pair<>("", totalPrice);
        return p;
    }

    private static int countSKUs(String skus, String skuToCount)
    {
        int count = 0;
        for (int i = 0; i < skus.length(); i++)
        {
            String sku = String.valueOf(skus.charAt(i));
            if (sku.equals(skuToCount))
            {
                count++;
            }
        }
        return count;
    }

    private static String sortSKUsDescendingPrice(HashMap<String, Integer> mapSKUsPrice, String skus)
    {
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(mapSKUsPrice.entrySet());

        entries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        ArrayList<Pair<String, Integer>> sortedPrices = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries)
        {
            sortedPrices.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        StringBuilder sbSortedSKUs = new StringBuilder();

        // Sort SKUs from input, according their price
        for (Pair<String, Integer> skuAndPrice : sortedPrices)
        {
            for (int i = 0; i < skus.length(); i++)
            {
                if (String.valueOf(skus.charAt(i)).equals(skuAndPrice.getValue0()))
                {
                    sbSortedSKUs.append(skuAndPrice.getValue0());
                }
            }
        }

        return sbSortedSKUs.toString();
    }
}



