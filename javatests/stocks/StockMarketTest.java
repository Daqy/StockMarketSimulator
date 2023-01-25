package stocks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StockMarketTest {
  private static final Stock GOOG_STOCK = Stock.newBuilder().setLabel("GOOG").setShares(1).build();
  private static final Stock GOOG_STOCK_WITH_100_SHARES = Stock.newBuilder().setLabel("GOOG").setShares(100).build();
  private final StockPortfolio userPortfolio = new PerNameStockPorfolio();
  private final StockPortfolio stockCollection = new PerNameStockPorfolio();

  @Test
  public void purchaseStock_stocksInPortfolioIncrease() {
    stockCollection.addStock(GOOG_STOCK_WITH_100_SHARES);
    StockMarket market = new StockMarket(stockCollection);

    market.purchaseStock(userPortfolio, GOOG_STOCK);

    Assert.assertEquals(GOOG_STOCK, userPortfolio.getStock("GOOG"));
    Assert.assertEquals(99, stockCollection.getStock("GOOG").getShares());
  }

  @Test
  public void sellStock_stocksInPortfolioDecrease() {
    stockCollection.addStock(GOOG_STOCK_WITH_100_SHARES);
    StockMarket market = new StockMarket(stockCollection);
    userPortfolio.addStock(GOOG_STOCK);

    market.sellStock(userPortfolio, GOOG_STOCK);

    Assert.assertEquals(0, userPortfolio.getStock("GOOG").getShares());
    Assert.assertEquals(101, stockCollection.getStock("GOOG").getShares());
  }
}
