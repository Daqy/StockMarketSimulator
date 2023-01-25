package stocks.exceptions;

public class userOptionOutOfRangeException extends StockException {
  public userOptionOutOfRangeException(int userOption, int[] range) {
    super("the option the user you have selected: " + userOption + " is greater then the range: " + range[0] + "-" + range[1]);
  }
}
