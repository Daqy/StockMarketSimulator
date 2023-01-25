package stocks;

import java.util.HashMap;
import java.util.Map;

public class Stock {
  private final String label;
  private final int shares;
  private double buyPrice;
  private double sellPrice;
  private final Map<String, Double> priceChange = new HashMap<>();

  private Stock(StockBuilder stockBuilder) {
    label = stockBuilder.label;
    shares = stockBuilder.shares;
    buyPrice = stockBuilder.buyPrice;
    sellPrice = stockBuilder.buyPrice - (stockBuilder.buyPrice * 0.1);
    priceChange.put("currentPrice", stockBuilder.buyPrice);
  }

  public static StockBuilder newBuilder() {
    return new StockBuilder();
  }

  public static final class StockBuilder {
    private String label;
    private int shares;
    private double buyPrice;

    public StockBuilder setLabel(String label) {
      this.label = label;
      return this;
    }

    public StockBuilder setShares(int shares) {
      this.shares = shares;
      return this;
    }

    public StockBuilder setBuyPrice(double buyPrice) {
      this.buyPrice = buyPrice;
      return this;
    }

    public Stock build() {
      return new Stock(this);
    }
  }

  public String getPercentageChange() {
    if(hasPriceChange()) {
      return ((priceChange.get("beforePrice") > priceChange.get("currentPrice")) ? "-" : "+") +(priceChange.get("difference") / priceChange.get("beforePrice") * 100) + "%";
    }
    return "0%";
  }

  private boolean hasPriceChange() {
    return priceChange.containsKey("beforePrice");
  }

  public void updatePrice(double newPrice, double changeBy) {
    priceChange.put("beforePrice", buyPrice);
    priceChange.put("currentPrice", newPrice);
    priceChange.put("difference", changeBy);
    buyPrice = newPrice;
    sellPrice = buyPrice - (buyPrice * 0.1);
  }

  public String getLabel() {
    return label;
  }

  public int getShares() {
    return shares;
  }

  public double getBuyPrice() {
    return buyPrice;
  }

  public double getSellPrice() {
    return sellPrice;
  }

}
