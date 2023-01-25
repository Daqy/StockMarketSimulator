import java.util.Random;

public class IncomeStock extends BlueChipStock {
    private double riskFactor;
    private double successFactor;
    private double incomeFactor = 0.01;
    
    public IncomeStock(String label, String ownedBy, double price, int shares) {
        super(label, ownedBy, price, shares, 0.1);
        setRiskFactor(99.99);
        setSuccessFactor(99.999);
    }
    public IncomeStock(String label, String ownedBy, double price, int shares, double dividend, double ratio) {
        super(label, ownedBy, price, shares, dividend, 0.1);
        setRiskFactor(99.99);
        setSuccessFactor(99.999);
    }

    public void updatePrice() {
        Random rand = new Random();
        double newPrice = getPrice();
        if (rand.nextDouble() > getSuccessFactor()) {
            newPrice = getPrice() * getRdnNumber(2, 5);
        } else if (rand.nextDouble() > getRiskFactor()) {
            newPrice = getPrice() * getRdnDouble(0.6);
        } else {
            newPrice = (rand.nextInt(1) > 0) ? getPrice() * getRdnDouble(0.99) : getPrice() / getRdnDouble(0.99);
        }

        setPrice(newPrice);
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
        Random rand = new Random();
        if (getDividend() > 100) {
            setDivdend((rand.nextInt(1)>0) ? (getDividend() + (EarningPerShare * payoutRatio) / 0.8) : (getDividend() - (EarningPerShare * payoutRatio) / 0.8) + getFactor());
        } else {
            setDivdend((getDividend() + (EarningPerShare * payoutRatio) / 0.8));
        }
    }
    public double getRiskFactor() {
        return riskFactor;
    }
    public double getSuccessFactor() {
        return successFactor;
    }
    public double getFactor() {
        return incomeFactor;
    }
    public void setRiskFactor(double value) {
        riskFactor = value;
    }
    public void setSuccessFactor(double value) {
        successFactor = value;
    }
}