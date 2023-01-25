import java.util.Map;
import javax.swing.*;

public class GUIinterface implements ScreenInterface {
    StockPortfolio stockCollection = new PerNameStockPorfolio();
    StockMarket market = new StockMarket(stockCollection);
    JFrame frame = new MainFrame("Stock Market Simulator", market);

    public GUIinterface(StockPortfolio marketCollection) {
        this.stockCollection = marketCollection;
    }

    public GUIinterface() {

    }

    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setSize(500,500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
    public int getOptionFromMenu() {
        return -1;
    }
    public void displayStockMarket(Map<String, Stock> marketCollection) {
        frame.repaint();
    }
    public void displayAccount() {
        System.out.println("doesn't work");
    }
}
