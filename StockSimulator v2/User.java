import java.util.Map;

public class User
{
  private StockPortfolio userStock = new userStock();
  private double balance;

  public User() {
    balance = 100;
  }

  public Map<String, Stock> getStockCollection() {
    return userStock.getStockCollection();
  }

  public double getBalance() {
    return balance;
  }
}
