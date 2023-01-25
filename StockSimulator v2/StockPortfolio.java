import java.util.Map;

public interface StockPortfolio
{
  void addStock(Stock stock);
  Stock getStock(String label);
  Map<String, Stock> getStockCollection();
}