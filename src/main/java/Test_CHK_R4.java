import befaster.solutions.CHK.SpecialOffer;
import org.javatuples.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Test_CHK_R4 {
    public static void main(String[] args)
    {
        System.out.println(checkout("FF"));
    }

    public static Integer checkout(String skus) {
        if (!skus.isEmpty())
        {
            if (skus.matches("[A-Z]+"))
            {
                int totalPrice = 0;

                //Map with the price for each SKU
                HashMap<String, Integer> mapSKUsPrice = new HashMap<>();
                mapSKUsPrice.put("A", 50);
                mapSKUsPrice.put("B", 30);
                mapSKUsPrice.put("C", 20);
                mapSKUsPrice.put("D", 15);
                mapSKUsPrice.put("E", 40);
                mapSKUsPrice.put("F", 10);

                //Map with special offers
                ArrayList<SpecialOffer> specialOffers = getSpecialOffers();

                HashMap<String, Integer> mapSKUsCounter = getMapSKUSCounter(skus);

                //Check for free items
                HashMap<String, Integer> filteredSKUs = checkForFreeItems(mapSKUsCounter, specialOffers);

                //Calculate price
                for (String currentSku : filteredSKUs.keySet())
                {
                    int amount = filteredSKUs.get(currentSku);

                    while (amount > 0)
                    {
                        if (!specialOffers.isEmpty())
                        {
                            SpecialOffer bestSpecialOffer = getBestSpecialOffer(currentSku, amount, specialOffers);

                            //Special offer found
                            if (bestSpecialOffer != null)
                            {
                                int offerAmount = bestSpecialOffer.getAmountRequired();
                                int offerPrice = bestSpecialOffer.getTotalPrice();

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

    private static HashMap<String, Integer> checkForFreeItems(HashMap<String, Integer> mapCurrentAmountSKUs, ArrayList<SpecialOffer> specialOffers)
    {
        HashMap<String, Integer> mapFilteredAmountSKUs = new HashMap<>(mapCurrentAmountSKUs);

        boolean tryToApplyOfferAgain = false;
        while (true)
        {
            tryToApplyOfferAgain = false;
            for (SpecialOffer specialOffer : specialOffers)
            {
                if (specialOffer.getFreeSKU() != null && !specialOffer.getFreeSKU().isEmpty())
                {
                    String requiredSKU = specialOffer.getRequiredSKU();
                    int requiredAmount = specialOffer.getAmountRequired();

                    //Check if we have the required amount of items to apply the offer
                    if (mapFilteredAmountSKUs.containsKey(requiredSKU))
                    {
                        int currentAmountRequiredSKU = mapFilteredAmountSKUs.get(requiredSKU);

                        String freeSKU = specialOffer.getFreeSKU();

                        //Required SKU is different from the free SKU
                        if (!requiredSKU.equals(freeSKU))
                        {
                            if (currentAmountRequiredSKU >= requiredAmount)
                            {
                                if (mapFilteredAmountSKUs.containsKey(freeSKU))
                                {
                                    int currentAmountFreeSKU = mapCurrentAmountSKUs.get(freeSKU);

                                    int freeAmount = specialOffer.getFreeAmount();

                                    //Apply discount
                                    if (currentAmountFreeSKU >= freeAmount)
                                    {
                                        currentAmountFreeSKU -= freeAmount;
                                        mapCurrentAmountSKUs.put(freeSKU, currentAmountFreeSKU);

                                        int sub = currentAmountRequiredSKU - requiredAmount;
                                        mapFilteredAmountSKUs.put(requiredSKU, sub);

                                        tryToApplyOfferAgain = true;
                                    }
                                }
                            }
                        }
                        //Required and free are the same
                        else
                        {
                            if (currentAmountRequiredSKU > requiredAmount)
                            {
                                if (mapFilteredAmountSKUs.containsKey(freeSKU))
                                {
                                    int currentAmountFreeSKU = mapCurrentAmountSKUs.get(freeSKU);

                                    int freeAmount = specialOffer.getFreeAmount();

                                    //Apply discount
                                    if (currentAmountFreeSKU >= freeAmount)
                                    {
                                        currentAmountFreeSKU -= freeAmount;
                                        mapCurrentAmountSKUs.put(freeSKU, currentAmountFreeSKU);

                                        int sub = currentAmountRequiredSKU - requiredAmount;
                                        mapFilteredAmountSKUs.put(requiredSKU, sub);

                                        tryToApplyOfferAgain = true;
                                    }
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

        return mapCurrentAmountSKUs;
    }

    private static SpecialOffer getBestSpecialOffer(String skuRequired, int amountOfItems, ArrayList<SpecialOffer> specialOffers)
    {
        float bestSingleItemPrice = Integer.MAX_VALUE;

        SpecialOffer bestSpecialOffer = null;

        for (SpecialOffer specialOffer : specialOffers)
        {
            if (skuRequired.equals(specialOffer.getRequiredSKU()) && specialOffer.getFreeSKU() == null)
            {
                int offerAmount = specialOffer.getAmountRequired();
                int offerPrice = specialOffer.getTotalPrice();

                float singleItemPrice = (float) offerPrice / offerAmount;

                if (amountOfItems >= offerAmount && singleItemPrice < bestSingleItemPrice)
                {
                    bestSingleItemPrice = singleItemPrice;
                    bestSpecialOffer = specialOffer;
                }
            }
        }

        return bestSpecialOffer;
    }

    private static ArrayList<SpecialOffer> getSpecialOffers()
    {
        ArrayList<SpecialOffer> offers = new ArrayList<>();

        offers.add(new SpecialOffer("A", 3, 130));
        offers.add(new SpecialOffer("A", 5, 200));
        offers.add(new SpecialOffer("B", 2, 45));
        offers.add(new SpecialOffer("E", 2, "B",1));
        offers.add(new SpecialOffer("F", 2, "F",1));

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
}

