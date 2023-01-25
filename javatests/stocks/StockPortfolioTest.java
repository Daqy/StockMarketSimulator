package stocks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import stocks.exceptions.RemoveTooMuchStockException;

@RunWith(JUnit4.class)
public final class StockPortfolioTest {
  private static final Stock GOOG_STOCK = Stock.newBuilder().setLabel("GOOG").setShares(1).build();
  private static final Stock APPL_STOCK = Stock.newBuilder().setLabel("APPL").setShares(1).build();

  private static final Stock GOOG_STOCK_WITH_5_SHARES = Stock.newBuilder().setLabel("GOOG").setShares(5).build();

  private final StockPortfolio stocks = new PerNameStockPorfolio();

  @Test
  public void addStock_exists() {
    stocks.addStock(GOOG_STOCK);

    Assert.assertEquals(GOOG_STOCK, stocks.getStock("GOOG"));
  }

  @Test
  public void addMultipleStock_getMultipleStocks() {
    stocks.addStock(GOOG_STOCK);
    stocks.addStock(APPL_STOCK);

    Assert.assertEquals(GOOG_STOCK, stocks.getStock("GOOG"));
    Assert.assertEquals(APPL_STOCK, stocks.getStock("APPL"));
  }

  @Test
  public void givenExistingGoogleStock_receiveStockShares_sharesIncrease() {
    stocks.addStock(GOOG_STOCK);

    stocks.addStock(GOOG_STOCK_WITH_5_SHARES);

    Assert.assertEquals(6, stocks.getStock("GOOG").getShares());
  }

  @Test
  public void givenExistingStock_removeStockShares_sharesDecrease() {
    stocks.addStock(GOOG_STOCK_WITH_5_SHARES);

    stocks.removeStock(GOOG_STOCK);

    Assert.assertEquals(4, stocks.getStock("GOOG").getShares());
  }
}
