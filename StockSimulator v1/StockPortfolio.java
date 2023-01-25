 

import java.util.Map;

public interface StockPortfolio {
  void addStock(Stock stock);
  void removeStock(Stock stock);
  Stock getStock(String label);
  Map<String, Stock> getCollection();
  void updatePrice();
}
