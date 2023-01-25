import java.util.Map;

public final class StockMarket {
  private final IStockPortfolio marketCollection;

  public StockMarket(IStockPortfolio marketCollection) {
    this.marketCollection = marketCollection;
  }

  public void purchaseStock(IStockPortfolio stocks, Stock stock) {
    stocks.addStock(stock);
    marketCollection.removeStock(stock);
  }

  public void sellStock(IStockPortfolio stocks, Stock stock) {
    stocks.removeStock(stock);
    marketCollection.addStock(stock);
  }

  public IStockPortfolio getPortfolio() {
    return marketCollection;
  }
}


