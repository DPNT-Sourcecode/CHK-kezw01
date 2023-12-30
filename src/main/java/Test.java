import java.util.HashMap;

public class Test {
    public static void main(String[] args)
    {
        System.out.println("hello");
        checkout("ABBAACCD");

    }

    public static Integer checkout(String skus) {
        int totalPrice = 0;

        //Price for each SKU
        HashMap<String, Integer> mapSKUsPrice = new HashMap<String, Integer>();
        mapSKUsPrice.put("A", 50);
        mapSKUsPrice.put("B", 30);
        mapSKUsPrice.put("C", 20);
        mapSKUsPrice.put("D", 15);

        HashMap<String, Integer> mapSKUsCounter = new HashMap<String, Integer>();

        for (int i = 0; i < skus.length(); i++)
        {
            char sku = skus.charAt(i);

            if (mapSKUsCounter.get(sku) != null)
            {

            }
            else {

            }
        }

        return totalPrice;
    }
}

