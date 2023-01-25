import java.util.Arrays;

public class Stock
{
  private final String[] collection;
  private final String stockLabel;

  public Stock(String label, int collectionSize) {
    stockLabel = label;
    collection = new String[collectionSize];
    Arrays.fill(collection, "market");
  }

  public Stock(String label) {
    stockLabel = label;
    collection = new String[100];
    Arrays.fill(collection, "market");
  }

  public int getNumberOfStock() {
    int counter = 0;

    if (checkForAvaliability()) {
      for (String value : collection) {
        if (value.equals("market")) counter++;
      }      
    } 
    return counter;
  }

  public void buyStock(String name, int amount) {
    for (int index = 0; index < amount; index++) {
      if (collection[index].equals(name)) {
        amount++;
      } else {
        collection[index] = name;
      }
    }
  }

  public void sellStock(String name, int amount) {
    int stopLoop = 0;
    for (int index = amount; index > stopLoop; index--) {
      if (collection[index].equals("market")) {
        stopLoop--;
      } else {
        collection[index] = "market";
      }
    }
  }

  public String getStockName() {
    return stockLabel;
  }

  private boolean checkForAvaliability() {
    return findIndex("market") > 0 ? true : false;
  }

  private int findIndex(String word) {
    for (String value : collection) {
      if (value.equals(word)) {
        return 1;
      }
    }
    return -1;
  }
}
