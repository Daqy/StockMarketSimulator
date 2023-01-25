import java.util.Map;

interface IStockPortfolio {
    void addStock(Stock stock);
    void removeStock(Stock stock);
    Stock getStock(String label);
    Map<String, Stock> getStockCollection();
}
