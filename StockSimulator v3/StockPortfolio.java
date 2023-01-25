import java.util.Map;
import java.util.HashMap;

public class StockPortfolio implements IStockPortfolio {
    private final Map<String, Stock> StockCollection = new HashMap<>();

    public StockPortfolio() {}

    public void addStock(Stock stock) {
        String stockLabel = stock.getLabel();
        Stock newStock;

        if (stockExists(stockLabel)) {
            newStock = mergeStock(stockLabel, stock);
        } else {
            newStock = stock;
        }

        StockCollection.put(stockLabel, newStock);
    }
    private boolean stockExists(String label) {
        return StockCollection.containsKey(label);
    }
    public void removeStock(Stock stock) {
        String stockLabel = stock.getLabel();
        int sharesToRemove = stock.getShares();
    
        if (!stockExists(stockLabel)) {
          throw new NonExistantStockException(stockLabel);
        }
    
        if (!stockSharesDoNotSurpassThreshold(stockLabel, sharesToRemove)) {
          throw new RemoveTooMuchStockException(stockLabel, sharesToRemove);
        }
    
        StockCollection.put(stockLabel, substractStockShares(stockLabel, sharesToRemove));
    }
    private Stock mergeStock(String label, Stock moreStock) {
        Stock currentStock = StockCollection.get(label);
        int mergedShares = currentStock.getShares() + moreStock.getShares();
        double currentPrice = currentStock.getPrice();

        if (currentStock instanceof PennyStock) {
            return new PennyStock(currentStock.getLabel(), "user", currentPrice, mergedShares);
        }
        if (currentStock instanceof GrowthStock) {
            return new GrowthStock(currentStock.getLabel(), "user", currentPrice, mergedShares);
        }
        else {
            return new IncomeStock(currentStock.getLabel(), "user", currentPrice, mergedShares);
        }
    }

    private Stock substractStockShares(String label, int shares) {
        Stock currentStock = StockCollection.get(label);
        int differentiatedStocks = currentStock.getShares() - shares;
        double currentPrice = currentStock.getPrice();

        if (currentStock instanceof PennyStock) {
            return new PennyStock(currentStock.getLabel(), "user", currentPrice, differentiatedStocks);
        }
        if (currentStock instanceof GrowthStock) {
            return new GrowthStock(currentStock.getLabel(), "user", currentPrice, differentiatedStocks);
        }
        else {
            return new IncomeStock(currentStock.getLabel(), "user", currentPrice, differentiatedStocks);
        }
    }

    private boolean stockSharesDoNotSurpassThreshold(String label, int shares) {
        return StockCollection.get(label).getShares()>=shares;
    }

    public Stock getStock(String label) {
        return StockCollection.get(label);
    }
    public Map<String, Stock> getStockCollection() {
        return StockCollection;
    }
}
