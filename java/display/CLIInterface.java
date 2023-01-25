package display;

import stocks.Stock;
import stocks.StockPortfolio;
import stocks.exceptions.userOptionOutOfRangeException;

import java.util.Map;
import java.util.Scanner;

public class CLIInterface implements ScreenInterface {
  public int getOptionFromMenu() {
    int userOption = intInput(displayMenu());
    if(1 < userOption && userOption > 2) {
      throw new userOptionOutOfRangeException(userOption, new int[] {1,3});
    }
    return userOption;
  }

  public void displayStockMarket(Map<String, Stock> marketCollection) {
    System.out.println("StockName: SharesAmount");
    for (Map.Entry<String, Stock> entry : marketCollection.entrySet()) {
      Stock stock = entry.getValue();
      String label = entry.getKey();

      System.out.println(label + ": " + stock.getShares());
      System.out.println("\tBuy Price: " + stock.getBuyPrice());
      System.out.println("\tSell Price: " + stock.getSellPrice());
      System.out.println("\tPrice Change: " + stock.getPercentageChange());
    }
  }

  public void displayAccount() {
    System.out.println("This page is currently Unavailable");
  }

  private int intInput(String message) {
    Scanner scanner = new Scanner(System.in);
    System.out.print(message);
    return scanner.nextInt();
  }

  private String displayMenu() {
    return "(1) View Stock Market\n(2) My Account\nEnter Where you want to navigate to: ";
  }
}
