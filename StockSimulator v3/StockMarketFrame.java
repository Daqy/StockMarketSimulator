import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class StockMarketFrame extends JFrame {
    JFrame frame;
    JFrame start;
    JPanel contentPanel;
    JPanel whitePanel;
    JPanel grayPanel;
    JPanel test;
    JPanel lightGrayPanel;
    JPanel twoPanelContainer;
    Account account;
    StockMarket market;
    JTextArea textArea;
    int counter;

    public StockMarketFrame()
    {
        try {
            account = newUserAccount();
            market = newStockMarket();
            start = new JFrame();
            start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            frame.setVisible(false);

            textArea = new JTextArea(5, 30);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
    
            contentPanel = new JPanel(new GridLayout(2,1));
    
            grayPanel = new JPanel();
            grayPanel.setBackground(Color.gray);
            generateGrayPanel();
    
            lightGrayPanel = new JPanel();
            lightGrayPanel.setBackground(Color.lightGray);
            generateLightGrayPanel();
    
            twoPanelContainer = new JPanel(new GridLayout(1,2));
            twoPanelContainer.add(grayPanel);
            twoPanelContainer.add(lightGrayPanel);
    
            JScrollPane scroller = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            contentPanel.add(twoPanelContainer);
            contentPanel.add(scroller);

            frame.setContentPane(contentPanel);
            frame.pack();
            frame.setSize(520, 500);

            test = new JPanel(new GridLayout(2,1));
            test.add(new JLabel("                                             Welcome to my basic GUI stock simulator                  "));

            ActionListener createNew = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    start.setVisible(false);
                    account = newUserAccount();
                    market = newStockMarket();
                    frame.setVisible(true);
                }
            };

            ActionListener loadPrev = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    start.setVisible(false);
                    IFile file = new IFile("oldAccount.txt");
                    account = file.getAccount();
                    market = file.getMarket();
                    frame.setVisible(true);
                    generateGrayPanel();
                    generateLightGrayPanel();
                    frame.revalidate();
                    frame.repaint();
                }
            };

            JButton newAccount = new JButton("Create new Account");
            newAccount.addActionListener(createNew);

            JButton loadAccount = new JButton("Load Account");
            loadAccount.addActionListener(loadPrev);

            JPanel twoPanel = new JPanel(new GridLayout(1,2));
            twoPanel.add(newAccount);
            twoPanel.add(loadAccount);

            test.add(twoPanel);

            start.setContentPane(test);
            start.pack();
            start.setSize(520, 500);
            start.setVisible(true);
    
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    Random rand = new Random();
                    market.getPortfolio().getStockCollection().forEach((key,value) -> {
                        if (rand.nextInt(3) == 1) {
                            double oldValue = value.getPrice();
                            value.updatePrice();
                            double newValue = value.getPrice();
                            if (oldValue>newValue) {
                                textArea.append(key + " Has decreased in price\n");
                            } else {
                                textArea.append(key + " Has increased in price\n");
                            }
                        }
                    });
                    account.getPortfolio().getStockCollection().forEach((key, value) -> {
                        value.setPrice(market.getPortfolio().getStock(key).getPrice());
                    });
                    generateGrayPanel();
                    generateLightGrayPanel();
                    frame.revalidate();
                    frame.repaint();
                }
            }, 0, 10000);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    counter = 0;
                    account.getPortfolio().getStockCollection().forEach((key,value) -> {if (value.getShares() == 0) {counter++;}});
                    if (counter == account.getPortfolio().getStockCollection().size()) {
                        account.setIncomeFromDividends(0);
                    }
                    account.setBalance(account.getBalance()+account.getIncomeFromDividends());
                    generateGrayPanel();
                    generateLightGrayPanel();
                    frame.revalidate();
                    frame.repaint();
                }
            }, 0, 20000);
            
        } catch (Exception e) {
            System.out.println("The Stock market has crashed, your simulation is now over");
        }
        
    }

    private void generateGrayPanel() {
        try {
            grayPanel.removeAll();
            grayPanel.add(new JLabel( "                " + this.account.getUsername() + "'(s) Stock Portfolio                "));
            grayPanel.add(new JLabel("Your Balance: " + this.account.getBalance()+"                                            "));
            grayPanel.add(new JLabel("Income from dividends: " + this.account.getIncomeFromDividends()+"                               "));
            account.getPortfolio().getStockCollection().forEach((key, value) -> {
                grayPanel.add(new JLabel('"' + key + '"' + ": " + value.getShares()+"                                                               "));
            });

            JButton saveExit = new JButton("Save & Exit");
            ActionListener save = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    IFile file = new IFile("oldAccount.txt");
                    file.write(account, market);
                    frame.dispose();
                }
            };
            saveExit.addActionListener(save);
            grayPanel.add(saveExit);
        } catch (Exception e) {
            System.out.println("The Stock market has crashed, your simulation is now over");
        }
    }

    private void generateLightGrayPanel() {
        try {
            lightGrayPanel.removeAll();
            lightGrayPanel.add(new JLabel("Market Stock Portfolio                   "));
    
            this.market.getPortfolio().getStockCollection().forEach((key, value) -> {
                lightGrayPanel.add(new JLabel('"' + key + '"' + ": " + value.getShares() + " : £" + value.getPrice(), SwingConstants.LEFT));
    
                ActionListener buy = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (value.getShares() > 0) {
                            if (value.getPrice() <= account.getBalance()) {
                                JButton button = (JButton)e.getSource();
                                textArea.append("1 " + button.getName() + " Stock was bought for £" + value.getPrice() + "\n");
                                
                                Stock tempStock = newStock(button.getName(), market.getPortfolio(), "user");
                                
                                account.getPortfolio().addStock(tempStock);
                                
                                value.updateDividend((value.getPrice()*0.1), value.getRatio());
    
                                Stock temp = market.getPortfolio().getStock(button.getName());
                                temp.setShares((temp.getShares()-1));
    
                                account.setIncomeFromDividends(account.getIncomeFromDividends() + (value.getDividend() * value.getShares()));
        
                                double costOfStock = temp.getPrice();
                                account.setBalance(account.getBalance() - costOfStock);
        
                                generateGrayPanel();
                                generateLightGrayPanel();
                                
                                frame.revalidate();
                                frame.repaint();
                            } else {
                                textArea.append("YOU DON'T HAVE ENOUGH FUNDS FOR THIS STOCK\n");
                            }
                        } else {
                            textArea.append("THERE IS NO MORE SHARES FOR THIS STOCK\n");
                        }
                    }
                };
                ActionListener sell = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (account.getPortfolio().getStock(key).getShares()>0) {
                            JButton button = (JButton)e.getSource();
                            textArea.append("1 " + button.getName() + " Stock was sold for £" + value.getPrice() + "\n");
        
                            Stock tempStock = newStock(button.getName(), account.getPortfolio(), "market");
                            try {
                                account.getPortfolio().removeStock(tempStock);
                            } catch (Exception a) {
                                textArea.append("You don't have enough stock");
                            }
        
                            double costOfStock = market.getPortfolio().getStock(button.getName()).getPrice();
                            account.setBalance(account.getBalance() + costOfStock);
        
                            market.getPortfolio().addStock(tempStock);
    
                            value.updateDividend((value.getPrice()*0.1), value.getRatio());
        
                            account.setIncomeFromDividends((account.getIncomeFromDividends() - (value.getDividend() * value.getShares())));
        
                            generateGrayPanel();
                            generateLightGrayPanel();
                            
                            frame.revalidate();
                            frame.repaint();
                        }
                    }
                };
    
                JButton buyButton = new JButton("Buy");
                buyButton.setName(key);
                buyButton.addActionListener(buy);
    
                JButton sellButton = new JButton("Sell");
                sellButton.setName(key);
                sellButton.addActionListener(sell);
    
                lightGrayPanel.add(buyButton);
                lightGrayPanel.add(sellButton);
            });
        } catch (Exception e) {
            System.out.println("The Stock market has crashed, your simulation is now over");
        }
        
    }

    public void setMarket(StockMarket market) {
        this.market = market;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private Stock newStock(String label, IStockPortfolio location, String name) {
        Stock stock = location.getStock(label);

        if (stock instanceof PennyStock) {
            return new PennyStock(label, name, stock.getPrice(), 1);
        }
        if (stock instanceof GrowthStock) {
            return new GrowthStock(label, name, stock.getPrice(), 1);
        }
        else {
            return new IncomeStock(label, name, stock.getPrice(), 1);
        }
        
    }

    private Account newUserAccount() {
        return new Account("Guest", 200);
    }

    private StockMarket newStockMarket() {
        IStockPortfolio market = new StockPortfolio();

        market.addStock(new GrowthStock("APPL", "market", 100, 70));
        market.addStock(new IncomeStock("GOOG", "market", 101, 50));
        market.addStock(new PennyStock("NEWB", "market", 1, 30));

        return new StockMarket(market);
    }

    //new line
    //add stuff to panels change vlaues of jlabels
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StockMarketFrame();
            }
        });
    }
}
