import java.util.Map;
import java.util.HashMap;

public class userStock implements StockPortfolio {
  private final Map<String, Stock> StockCollection = new HashMap<>();

  public userStock() {};

  public void addStock(Stock stock) {
    String stockLabel = stock.getStockName();

    if (!stockExists(stockLabel)) {
      StockCollection.put(stockLabel, stock);
    }
  }

  private boolean stockExists(String label) {
    return StockCollection.containsKey(label);
  }

  public Stock getStock(String label) {
    return StockCollection.get(label);
  }

  public Map<String, Stock> getStockCollection() {
      return StockCollection;
  }
}