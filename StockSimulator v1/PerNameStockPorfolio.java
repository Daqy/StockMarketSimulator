import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PerNameStockPorfolio implements StockPortfolio {
  private final Map<String, Stock> stocks = new HashMap<>();

  public void addStock(Stock stock) {
    String stockLabel = stock.getLabel();
    Stock newStock;

    if (stockExists(stockLabel)) {
      newStock = mergeStock(stockLabel, stock);
    } else {
      newStock = stock;
    }

    stocks.put(stockLabel, newStock);
  }

  public void removeStock(Stock stock) {
    String stockLabel = stock.getLabel();
    int sharesToRemove = stock.getShares();

    if (!stockExists(stockLabel)) {
      throw new NonExistantStockException(stockLabel);
    }

    if (!stockSharesDoNotSurpassThreshold(stockLabel, sharesToRemove)) {
      throw new RemoveTooMuchStockException(stockLabel, sharesToRemove);
    }

    stocks.put(stockLabel, substractStockShares(stockLabel, sharesToRemove));
  }

  private boolean stockExists(String label) {
    return stocks.containsKey(label);
  }

  private boolean stockSharesDoNotSurpassThreshold(String label, int shares) {
    return stocks.get(label).getShares()>=shares;
  }

  private Stock mergeStock(String label, Stock moreStock) {
    Stock currentStock = stocks.get(label);
    int mergedShares = currentStock.getShares() + moreStock.getShares();

    return Stock.newBuilder().setLabel(label).setShares(mergedShares).build();
  }

  private Stock substractStockShares(String label, int shares) {
    Stock currentStock = stocks.get(label);
    int differentiatedStocks = currentStock.getShares() - shares;
    return Stock.newBuilder().setLabel(label).setShares(differentiatedStocks).build();
  }

  public void updatePrice() {
    Random rand = new Random();

    for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
      Stock stock = entry.getValue();
      double newPrice = 0;
      if (rand.nextBoolean()) {
        int number = rand.nextInt(10);
        if (rand.nextBoolean()) {
          newPrice = stock.getBuyPrice() + number;
        } else {
          newPrice = stock.getBuyPrice() - number;
        }
        stock.updatePrice(newPrice, number);
      }
    }
  }

  public Stock getStock(String label) {
    return stocks.get(label);
  }

  public Map<String, Stock> getCollection() {
    return stocks;
  }
}
