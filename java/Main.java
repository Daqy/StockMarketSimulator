import display.CLIInterface;
import display.ScreenInterface;
import stocks.PerNameStockPorfolio;
import stocks.Stock;
import stocks.StockMarket;
import stocks.StockPortfolio;

public class Main {
  private static final Stock GOOG_STOCK_WITH_100_SHARES = Stock.newBuilder().setLabel("GOOG").setShares(100).setBuyPrice(100.00).build();
  private static final Stock AAPL_STOCK_WITH_100_SHARES = Stock.newBuilder().setLabel("AAPL").setShares(100).setBuyPrice(90.00).build();

  public static void main(String[] args) {
    final StockPortfolio stockCollection = new PerNameStockPorfolio();

    stockCollection.addStock(GOOG_STOCK_WITH_100_SHARES);
    stockCollection.addStock(AAPL_STOCK_WITH_100_SHARES);

    StockMarket market = new StockMarket(stockCollection);
    ScreenInterface menu = new CLIInterface();

    for (int i = 0; i < 4; i ++) {
      int option = menu.getOptionFromMenu();

      if (option == 1) {
        menu.displayStockMarket(market.getCollection());
      } else {
        menu.displayAccount();
      }
      stockCollection.updatePrice();
      System.out.println();
    }
  }
}
