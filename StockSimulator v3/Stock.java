public class Stock {
    private String label;
    private String ownedBy;
    private double price;
    private double dividend;
    private int shares;
    private double ratio;

    public Stock(String label, String ownedBy, double price, int shares, double ratio) {
        this.label = label;
        this.ownedBy = ownedBy;
        this.price = price;
        this.shares = shares;
        this.ratio = ratio;
    }
    public Stock(String label, String ownedBy, double price, int shares, double dividend, double ratio) {
        this.label = label;
        this.ownedBy = ownedBy;
        this.dividend = dividend;
        this.price = price;
        this.shares = shares;
        this.ratio = ratio;
    }
    public Stock() {};
    public String getLabel() {
        return label;
    }
    public String getOwner() {
        return ownedBy;
    }
    public double getRatio() {
        return ratio;
    }
    public double getPrice() {
        return Math.floor(price * 100) / 100;
    }
    public int getShares() {
        return shares;
    }
    public double getDividend() {
        return Math.floor(dividend * 100) / 100;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setShares(int ammount) {
        shares = ammount;
    }
    public void setDivdend(double value) {
        dividend = value;
    }
    public void updatePrice() {
        price = price / 0.99;
    }
    public void updateDividend(double EarningPerShare, double payoutRatio) {
        setDivdend(EarningPerShare * payoutRatio);
    }
}
