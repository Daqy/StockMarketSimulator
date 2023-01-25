import java.util.Map;

public interface ScreenInterface {
  void display();
  int getOptionFromMenu();
  void displayStockMarket(Map<String, Stock> marketCollection);
  void displayAccount();
}
