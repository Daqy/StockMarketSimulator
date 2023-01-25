import java.util.Map;

public final class StockMarket {
  private final StockPortfolio marketCollection;

  public StockMarket(StockPortfolio marketCollection) {
    this.marketCollection = marketCollection;
  }

  public void purchaseStock(StockPortfolio stocks, Stock stock) {
    stocks.addStock(stock);
    marketCollection.removeStock(stock);
  }

  public void sellStock(StockPortfolio stocks, Stock stock) {
    stocks.removeStock(stock);
    marketCollection.addStock(stock);
  }

  public Map<String, Stock> getCollection() {
    return marketCollection.getCollection();
  }
}


