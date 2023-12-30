import befaster.solutions.CHK.SpecialOffer;
import org.javatuples.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Test_CHK_R2 {
    public static void main(String[] args)
    {
        System.out.println(checkout("ABDD"));
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
                ArrayList<String> chargedSKUs = new ArrayList<>();

                //Calculate price
                for (String currentSku : mapSKUsCounter.keySet())
                {
                    int amount = mapSKUsCounter.get(currentSku);

                    while (amount > 0)
                    {
                        if (!specialOffers.isEmpty())
                        {
                            for (SpecialOffer specialOffer : specialOffers)
                            {
                                if (specialOffer.getSkuRequired().equals(currentSku))
                                {

                                }
                            }
                        }
                        else
                        {
                            int price = mapSKUsPrice.get(currentSku);
                            totalPrice += price;
                            amount--;

                        }

                        if (specialOffersCurrentSKU != null && !specialOffersCurrentSKU.isEmpty())
                        {
                            SpecialOffer bestSpecialOffer = getBestSpecialOffer(amount, specialOffersCurrentSKU);
                            //Check which offer is the best

                            if (bestSpecialOffer != null)
                            {
                                if (bestSpecialOffer.getSkuFreeAmount() != null)
                                {
                                    String skuFree = bestSpecialOffer.getSkuFreeAmount().getValue0();
                                    int amountSkuFree = bestSpecialOffer.getSkuFreeAmount().getValue1();

                                    //Check SKUs list to see if there is
                                    if ()
                                }
                                else
                                {
                                    int offerAmount = bestSpecialOffer.getAmount();
                                    int offerPrice = bestSpecialOffer.getPrice();

                                    //Check if there is enough items to apply the special offer
                                    if (amount >= offerAmount)
                                    {
                                        totalPrice += offerPrice;
                                        amount -= offerAmount;
                                        chargedSKUs.append(sku);
                                    }
                                    //Not enough items to apply special offer, calculate using the "normal" price
                                    else
                                    {
                                        int price = mapSKUsPrice.get(sku);
                                        totalPrice += price;
                                        amount--;
                                        chargedSKUs.append(sku);
                                    }
                                }
                            }
                            //The is no special offer this sku, calculate using the "normal" price
                            else {
                                int price = mapSKUsPrice.get(sku);
                                totalPrice += price;
                                amount--;
                                chargedSKUs.append(sku);
                            }
                        }
                        else
                        {

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

    private static SpecialOffer getBestSpecialOffer(int amountOfItems, ArrayList<SpecialOffer> specialOffers)
    {
        float bestSingleItemPrice = Integer.MAX_VALUE;

        SpecialOffer bestSpecialOffer = null;

        for (SpecialOffer specialOffer : specialOffers)
        {
            int offerAmount = specialOffer.getAmount();
            int offerPrice = specialOffer.getPrice();

            if (specialOffer.getSkuFreeAmount() != null)
            {
                //Free item
                bestSpecialOffer = specialOffer;
            }
            else
            {
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



