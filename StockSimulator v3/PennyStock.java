import java.util.Random;

public class PennyStock extends Stock {
    private double riskFactor;
    private double successFactor;
    private double rewardFactor = 0.1;
    
    public PennyStock(String label, String ownedBy, double price, int shares) {
        super(label, ownedBy, price, shares, 0.8);
        setSuccessFactor(0.99);
        setRiskFactor(0.80);
    }

    public PennyStock(String label, String ownedBy, double price, int shares, double dividend, double ratio) {
        super(label, ownedBy, price, shares, dividend, 0.1);
        setSuccessFactor(0.99);
        setRiskFactor(0.80);
    }

    public void updatePrice() {
        Random rand = new Random();
        double newPrice = getPrice();
        if (rand.nextDouble() > getSuccessFactor()) {
            newPrice = getPrice() * getRdnNumber(2, 10);
        } else if (rand.nextDouble() > getRiskFactor()) {
            newPrice = getPrice() * rand.nextDouble();
        } else {
            newPrice = (rand.nextInt(1) > 0) ? getPrice() * getRdnDouble(0.9) : getPrice() / getRdnDouble(0.9);
        }

        setPrice(newPrice);
    }

    private double getRewardFactor() {
        return rewardFactor;
    }

    private int getRdnNumber(int min, int max) {
        Random rand = new Random();

        int number = -1;
        while (!(number > min)) {
            number = rand.nextInt(max);
        }

        return number;
    }

    private double getRdnDouble(double min) {
        Random rand = new Random();
        
        double number = -1;
        while (!(number > min)) {
            number = rand.nextDouble();
        }

        return number;
    }

    public void updateDividend(double EarningPerShare, double payoutRatio) {
        setDivdend(EarningPerShare * payoutRatio / getRiskFactor() + getRewardFactor());
    }
    public double getRiskFactor() {
        return riskFactor;
    }
    public double getSuccessFactor() {
        return successFactor;
    }
    public void setRiskFactor(double value) {
        riskFactor = value;
    }
    public void setSuccessFactor(double value) {
        successFactor = value;
    }
}