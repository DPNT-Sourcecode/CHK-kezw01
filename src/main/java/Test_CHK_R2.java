import befaster.solutions.CHK.SpecialOffer;
import org.javatuples.Pair;

import java.util.ArrayList;
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
                HashMap<String, ArrayList<SpecialOffer>> mapSKUsSpecialOffers = getMapSKUsSpecialOffers();

                for (String sku : mapSKUsSpecialOffers.keySet()){
                    System.out.println(mapSKUsSpecialOffers.get(sku).toString());
                }

                HashMap<String, Integer> mapSKUsCounter = getMapSKUSCounter(skus);

                //Calculate price
                for (String sku : mapSKUsCounter.keySet())
                {
                    int amount = mapSKUsCounter.get(sku);

                    //Check if there is a special offer for this sku
                    ArrayList<SpecialOffer> specialOffers = mapSKUsSpecialOffers.get(sku);

                    while (amount > 0)
                    {
                        if (!specialOffers.isEmpty())
                        {
                            for (SpecialOffer specialOffer : specialOffers)
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

    private static HashMap<String, ArrayList<SpecialOffer>> getMapSKUsSpecialOffers()
    {
        HashMap<String, ArrayList<SpecialOffer>> mapSKUsSpecialOffers = new HashMap<>();

        //A
        ArrayList<SpecialOffer> listOffersA = new ArrayList<>();

        SpecialOffer offerA1 = new SpecialOffer();
        offerA1.setAmount(3);
        offerA1.setPrice(130);

        SpecialOffer offerA2 = new SpecialOffer();
        offerA2.setAmount(5);
        offerA2.setPrice(200);

        listOffersA.add(offerA1);
        listOffersA.add(offerA2);

        mapSKUsSpecialOffers.put("A", listOffersA);

        //B
        ArrayList<SpecialOffer> listOffersB = new ArrayList<>();

        SpecialOffer offerB1 = new SpecialOffer();
        offerB1.setAmount(2);
        offerB1.setPrice(45);

        listOffersB.add(offerB1);

        mapSKUsSpecialOffers.put("B", listOffersB);

        //E
        ArrayList<SpecialOffer> listOffersE = new ArrayList<>();

        SpecialOffer offerE1 = new SpecialOffer();
        offerE1.setAmount(2);
        offerE1.setSkuFreeAmount(new Pair<>("B", 1));

        listOffersE.add(offerE1);

        mapSKUsSpecialOffers.put("E", listOffersE);

        return mapSKUsSpecialOffers;
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




