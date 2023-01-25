package stocks.exceptions;

public class NonExistantStockException extends StockException {
  public NonExistantStockException(String label) {
    super("Stock: " + label + " does not exist.");
  }
}
