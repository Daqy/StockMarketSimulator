import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class CLI implements Display {
    private StockMarket market;
    private Account account;

    public CLI(){}
    public void display() {
        welcomeMessage();
        account = accountSetup();
        market = generateBasicStockMarket();
        UserOption();
    }

    private static String input(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }

    private static double parse(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            System.out.println("thats not a number. Exitting the program");
        }
        return -1;
    }

    private void welcomeMessage() {
        printSeperator();
        System.out.println("Welcome to my Basic CLI stock simulator");
        printSeperator();
        System.out.println();
    }

    private void printSeperator() {
        System.out.print("=");
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
        }
        System.out.println("=");
    }

    private Account accountSetup() {
        printSeperator();
        System.out.println("Lets start by making an account");
        String name = input("Enter your name: ");
        double budget = parse(input("Enter your investment budget: "));
        return new Account(name, budget);
    }

    private StockMarket generateBasicStockMarket() {
        IStockPortfolio market = new StockPortfolio();

        market.addStock(new GrowthStock("APPL", "market", 100, 70));
        market.addStock(new IncomeStock("GOOG", "market", 101, 50));
        market.addStock(new PennyStock("NEWB", "market", 1, 30));

        return new StockMarket(market);
    }

    private void UserOption() {
        Map<String, String> commands = new HashMap<>();
        String _input = "";

        commands.put("Buy", "buy <name> <amount>");
        commands.put("Sell", "sell <name> <amount>");
        commands.put("View Stock", "view profile | view market");
        commands.put("Exit", "exit | e");

        while (!(_input.equals("exit")) && !(_input.equals("e"))) {
            displayCommands(commands);
            _input = input("Enter a command: ");
            while(!correctCommand(_input)) {
                displayCommands(commands);
                _input = input("Enter a command: ");
            }
            String[] splitInput = _input.split(" ");

            if (_input.contains("buy")) {
                String command = splitInput[1].toUpperCase();
                int amount = Integer.parseInt(splitInput[2]);

                if (findStock(command, amount, market.getPortfolio()) == 1) {
                    Stock tempStock = newStock(command, amount, market.getPortfolio(), "user");
                    account.getPortfolio().addStock(tempStock);
                    Stock temp = market.getPortfolio().getStock(command);
                    temp.setShares((temp.getShares()-amount));

                    double costOfStock = temp.getPrice() * amount;
                    account.setBalance(account.getBalance() - costOfStock);
                } else {
                    System.out.println("This stock is not in the market");
                }
            } else if (_input.contains("sell")) {
                String command = splitInput[1].toUpperCase();
                int amount = Integer.parseInt(splitInput[2]);

                if (findStock(command, amount, account.getPortfolio()) == 1) {
                    Stock tempStock = newStock(command, amount, account.getPortfolio(), "market");
                    account.getPortfolio().removeStock(tempStock);

                    double costOfStock = market.getPortfolio().getStock(command).getPrice() * amount;
                    account.setBalance(account.getBalance() + costOfStock);
                } else {
                    System.out.println("You do not have this in your portfolio");
                }
            } else if (_input.contains("view")) {
                if (splitInput[1].equals("market")) {
                    market.getPortfolio().getStockCollection().forEach((key,value) -> System.out.println('"' + key + '"' + ": " + value.getShares() + " : £"+ value.getPrice()));
                } else {
                    System.out.println("Account balance: " + account.getBalance());
                    account.getPortfolio().getStockCollection().forEach((key,value) -> System.out.println('"' + key + '"' + ": " + value.getShares() + " : £"+ value.getPrice()));
                }
            } else if (_input.equals("exit") || _input.equals("e")) {
                System.out.println("GoodBye");
            } else {
                System.out.println("incorrect Input");
            }
        }
    }

    private void displayCommands(Map<String, String> commands) {
        printSeperator();
        System.out.println("Here is a list of commands");
        commands.forEach((key, value) -> System.out.println(key + ": (" + value + ")"));
        System.out.println();
    }

    private boolean correctCommand(String input) {
        if (input.contains("buy")) {
            String[] test = input.split(" ");
            if (test.length == 3) {
                try {
                    Integer.parseInt(test[2]);
                } catch (Exception e) {
                    return false;
                }
                if (nameInMarketPortfolio(test[1])) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (input.contains("sell")) {
            String[] test = input.split(" ");
            if (test.length == 3) {
                try {
                    Double.parseDouble(test[2]);
                } catch (Exception e) {
                    return false;
                }
                if (nameInUserPortfolio(test[1])) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (input.contains("view")) {
            String[] test = input.split(" ");
            if (test.length == 2) {
                if (test[1].equals("market") || test[1].equals("profile")) {
                    return true;
                } 
                return false;
            }
        }
        if (input.contains("exit") || input.contains("e")) {
            return true;
        }
        return false;
    }

    private boolean nameInMarketPortfolio(String name) {
        return (market.getPortfolio().getStock(name) != null);
    }

    private boolean nameInUserPortfolio(String name) {
        return (account.getPortfolio().getStock(name) != null);
    }

    private Stock newStock(String label, int amount, IStockPortfolio location, String name) {
        Stock stock = location.getStock(label);

        if (stock instanceof PennyStock) {
            return new PennyStock(label, name, stock.getPrice(), amount);
        }
        if (stock instanceof GrowthStock) {
            return new GrowthStock(label, name, stock.getPrice(), amount);
        }
        if (stock instanceof IncomeStock) {
            return new IncomeStock(label, name, stock.getPrice(), amount);
        }
        return new Stock();
    }

    private int findStock(String label, int amount, IStockPortfolio location) {
        Stock stock = location.getStock(label);
        if (stock != null) {
            return 1;
        }
        return -1;
    }
}
