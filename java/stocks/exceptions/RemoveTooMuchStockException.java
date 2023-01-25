package stocks.exceptions;

public class RemoveTooMuchStockException extends StockException {

  public RemoveTooMuchStockException(String label, int sharesToRemove) {
    super("Stock: " + label + " does not have enough shares to remove " + sharesToRemove +" shares.");
    System.out.println("test");
  }
}
