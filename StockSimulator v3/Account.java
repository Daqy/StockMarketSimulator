public class Account {
    private String username;
    private IStockPortfolio collection = new StockPortfolio();
    private double balance;
    private double incomeFromDividends;

    public Account(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public Account(String username, double balance, IStockPortfolio collection, double incomeFromDividends) {
        this.username = username;
        this.balance = balance;
        this.collection = collection;
        this.incomeFromDividends = incomeFromDividends;
    }

    public double getBalance() {
        return Math.floor(balance * 100) / 100;
    }

    public String getUsername() {
        return username;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public IStockPortfolio getPortfolio() {
        return collection;
    }

    public double getIncomeFromDividends() {
        return Math.floor(incomeFromDividends * 100) / 100;
    }

    public void setIncomeFromDividends(double value) {
        incomeFromDividends = value;
    }
}
