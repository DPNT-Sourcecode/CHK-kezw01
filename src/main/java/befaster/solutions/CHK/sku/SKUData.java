package befaster.solutions.CHK.sku;

public class DataSKU {
    private String sku;
    private int price;
    private int counter;

    public DataSKU(String sku, int price, int counter) {
        this.sku = sku;
        this.price = price;
        this.counter = counter;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "DataSKU{" +
                "sku='" + sku + '\'' +
                ", price=" + price +
                ", counter=" + counter +
                '}';
    }
}
