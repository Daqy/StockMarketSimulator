public class CLI {
  private StockPortfolio stockMarket = new StockMarket();
  private User user = new User();
  private Stock googStock = new Stock("GOOG");
  private Stock aaplStock = new Stock("AAPL");
  public CLI() {};

  public void display() {
    stockMarket.addStock(googStock);
    stockMarket.addStock(aaplStock);

    printPageSeperator();
    System.out.println("Welcome to my basic Stock Market Simulator");
    printPageSeperator();
    System.out.println();

    displayUserDetails();
    System.out.println();

    displayMarketDetails();
    System.out.println();
    
    displayUserOptions();
  }

  private void printPageSeperator() {
    System.out.print("=");
    for (int i = 0; i < 40; i++) {
      System.out.print("-");
    }
    System.out.println("=");
  }

  private void displayUserDetails() {
    System.out.print("=");
    for (int i = 0; i < 31; i++) {
      if (i == 15) {
        System.out.print("Your Stock");
      } else {
        System.out.print("-");
      }
    }
    System.out.println("=");

    if (user.getStockCollection().size() < 1) {
      System.out.println("Currently No Stock");
    } else {
      user.getStockCollection().forEach((key, value) -> System.out.println(key + ": " + value.getNumberOfStock()));
    }
    System.out.println("Balance: " + user.getBalance());
  }

  private void displayMarketDetails() {
    System.out.print("=");
    for (int i = 0; i < 29; i++) {
      if (i == 14) {
        System.out.print("Stock Market");
      } else {
        System.out.print("-");
      }
    }
    System.out.println("=");

    stockMarket.getStockCollection().forEach((key, value) -> System.out.println(key + ": " + value.getNumberOfStock()));
  }
  private void displayUserOptions() {
    System.out.print("=");
    for (int i = 0; i < 29; i++) {
      if (i == 14) {
        System.out.print("User Options");
      } else {
        System.out.print("-");
      }
    }
    System.out.println("=");
    
    stockMarket.getStockCollection().forEach((key, value) -> System.out.println("Buy " + key + " Stock: <number of stock>"));
    user.getStockCollection().forEach((key, value) -> System.out.println("Sell " + key + " Stock: <number of stock>"));
    System.out.println("Exit Program (e or exit)");
  }
}
