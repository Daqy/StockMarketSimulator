import java.lang.Thread;
import java.util.Random;

public class Main {
  // https://www.javatpoint.com/java-swing
  private static final Stock GOOG_STOCK_WITH_100_SHARES = Stock.newBuilder().setLabel("GOOG").setShares(100).setBuyPrice(100.00).build();
  private static final Stock AAPL_STOCK_WITH_100_SHARES = Stock.newBuilder().setLabel("AAPL").setShares(100).setBuyPrice(90.00).build();

  public static void main(String[] args) throws InterruptedException {
    final StockPortfolio stockCollection = new PerNameStockPorfolio();
    Random rand = new Random();

    stockCollection.addStock(GOOG_STOCK_WITH_100_SHARES);
    stockCollection.addStock(AAPL_STOCK_WITH_100_SHARES);

    StockMarket market = new StockMarket(stockCollection);
    ScreenInterface menu = new GUIinterface(stockCollection);

    menu.display();

    int option = menu.getOptionFromMenu();


  //   for (int i = 0; i < 4; i ++) {
  //     if (option == 1) {
  //       menu.displayStockMarket(market.getCollection());
  //     } else {
  //       menu.displayAccount();
  //     }
  //     stockCollection.updatePrice();
  //     System.out.println("\n One of the prices have changed.");
  //     Thread.sleep((int)((Math.random()*((4000-1000)+1000))+1000));
  //   }
  }
}
