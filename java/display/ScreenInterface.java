package display;

import stocks.Stock;

import java.util.Map;

public interface ScreenInterface {
  int getOptionFromMenu();
  void displayStockMarket(Map<String, Stock> marketCollection);
  void displayAccount();
}
