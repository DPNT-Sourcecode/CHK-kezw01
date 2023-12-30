import org.javatuples.Pair;

import java.util.HashMap;

public class Test_CHK_R2 {
    public static void main(String[] args)
    {
        checkout("AAAABB");
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
                HashMap<String, Pair<Integer, Integer>> mapSKUsSpecialOffers = new HashMap<>();
                mapSKUsSpecialOffers.put("A", new Pair<>(3, 130));
                mapSKUsSpecialOffers.put("B", new Pair<>(2, 45));

                HashMap<String, Integer> mapSKUsCounter = getMapSKUSCounter(skus);

                //Calculate price
                for (String sku : mapSKUsCounter.keySet())
                {
                    int amount = mapSKUsCounter.get(sku);

                    //Check if there is a special offer for this sku
                    Pair<Integer, Integer> specialOffer = mapSKUsSpecialOffers.get(sku);

                    while (amount > 0)
                    {
                        if (specialOffer != null)
                        {
                            int offerAmount = specialOffer.getValue0();
                            int offerPrice = specialOffer.getValue1();

                            //Check if there is enough items to apply the special offer
                            if (amount >= offerAmount)
                            {
                                totalPrice += offerPrice;
                                amount -= offerAmount;
                            }
                            //Not enough items to apply special offer, calculate using the "normal" price
                            else
                            {
                                int price = mapSKUsPrice.get(sku);
                                totalPrice += price;
                                amount--;
                            }
                        }
                        //The is no special offer this sku, calculate using the "normal" price
                        else {
                            int price = mapSKUsPrice.get(sku);
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


