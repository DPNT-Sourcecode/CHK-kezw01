package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (!skus.isEmpty()){

        }
        return 0;
    }



    private HashMap<String, Integer> getMapSKUSCounter(String skus)
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
