package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        int totalPrice = 0;

        HashMap<String, Integer> mapSKUsPrice = new HashMap<String, Integer>();
        mapSKUsPrice.put("A", 50);
        mapSKUsPrice.put("B", 30);
        mapSKUsPrice.put("C", 20);
        mapSKUsPrice.put("D", 15);



        for (int i = 0; i < skus.length(); i++)
        {
            char sku = skus.charAt(i);

        }

        return totalPrice;
    }
}



