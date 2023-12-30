import befaster.solutions.CHK.SpecialOffer;
import org.javatuples.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Test_CHK_R2 {
    public static void main(String[] args)
    {
        System.out.println(checkout("EEEEBBB"));
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

                //Map with special offers
                ArrayList<SpecialOffer> specialOffers = getSpecialOffers();

                HashMap<String, Integer> mapSKUsCounter = getMapSKUSCounter(skus);

                //Check for free items
                HashMap<String, Integer> filteredSKUs = checkForFreeItems(mapSKUsCounter, specialOffers);

                for (String key : filteredSKUs.keySet())
                {
                    System.out.println(key + " - " + filteredSKUs.get(key));
                }

                ArrayList<String> chargedSKUs = new ArrayList<>();

                //Calculate price
                for (String currentSku : mapSKUsCounter.keySet())
                {
                    int amount = mapSKUsCounter.get(currentSku);

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
                                    chargedSKUs.add(currentSku);
                                }
                                //Not enough items to apply special offer, calculate using the "normal" price
                                else
                                {
                                    int price = mapSKUsPrice.get(currentSku);
                                    totalPrice += price;
                                    amount--;
                                    chargedSKUs.add(currentSku);
                                }
                            }
                            //No special offers for this sku
                            else
                            {
                                //Apply normal price
                                int price = mapSKUsPrice.get(currentSku);
                                totalPrice += price;
                                amount--;
                                chargedSKUs.add(currentSku);
                            }
                        }
                        else
                        {
                            //Apply normal price
                            int price = mapSKUsPrice.get(currentSku);
                            totalPrice += price;
                            amount--;
                            chargedSKUs.add(currentSku);
                        }
                    }
                }
                System.out.println(chargedSKUs);
                return totalPrice;
            }
            else
            {
                return -1;
            }
        }
        return 0;
    }

    private static HashMap<String, Integer> checkForFreeItems(HashMap<String, Integer> mapSKUsCounter, ArrayList<SpecialOffer> specialOffers)
    {
        HashMap<String, Integer> mapSKUsCounterHelper = new HashMap<>(mapSKUsCounter);

        boolean tryAgain = false;
        while (true)
        {
            tryAgain = false;
            for (SpecialOffer specialOffer : specialOffers)
            {
                if (specialOffer.getFreeSKU() != null && !specialOffer.getFreeSKU().isEmpty())
                {
                    String requiredSKU = specialOffer.getRequiredSKU();
                    int requiredAmount = specialOffer.getAmountRequired();

                    //Check if we have the required amount of items to apply the offer
                    if (mapSKUsCounterHelper.containsKey(requiredSKU))
                    {
                        int currentAmountRequiredSKU = mapSKUsCounterHelper.get(requiredSKU);

                        if (currentAmountRequiredSKU >= requiredAmount)
                        {
                            String freeSKU = specialOffer.getFreeSKU();

                            if (mapSKUsCounterHelper.containsKey(freeSKU))
                            {
                                int currentAmountFreeSKU = mapSKUsCounterHelper.get(freeSKU);

                                int freeAmount = specialOffer.getFreeAmount();

                                //Apply discount
                                if (currentAmountFreeSKU >= freeAmount)
                                {
                                    currentAmountFreeSKU -= freeAmount;
                                    mapSKUsCounter.put(freeSKU, currentAmountFreeSKU);

                                    int sub = currentAmountRequiredSKU - requiredAmount;
                                    mapSKUsCounterHelper.put(requiredSKU, sub);

                                    tryAgain = true;
                                }
                            }
                        }
                    }
                }
            }

            if (!tryAgain)
            {
                break;
            }
        }

        return mapSKUsCounter;
    }

    private static SpecialOffer getBestSpecialOffer(String skuRequired, int amountOfItems, ArrayList<SpecialOffer> specialOffers)
    {
        float bestSingleItemPrice = Integer.MAX_VALUE;

        SpecialOffer bestSpecialOffer = null;

        for (SpecialOffer specialOffer : specialOffers)
        {
            if (skuRequired.equals(specialOffer.getRequiredSKU()))
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



