import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;

public class IFile {
    String location;
    private String message = "";
    private String lmessage = "";

    public IFile(String location) {
        this.location = location;
    }

    public Account getAccount() {
        String data = read().split("&-&")[0];
        String[] splitInfo = data.split("-");
        String[] collection = splitInfo[2].split("#");

        IStockPortfolio temp = new StockPortfolio();
        for (int i = 0; i < collection.length; i++) {
            String[] tempStock = collection[i].split("/");
            if (tempStock.length > 1) {
                if (tempStock[6].equals("incomestock")) {
                    temp.addStock(new IncomeStock(tempStock[0], tempStock[1], Double.parseDouble(tempStock[2]), Integer.parseInt(tempStock[3]), Double.parseDouble(tempStock[4]), Double.parseDouble(tempStock[5])));
                } else if (tempStock[6].equals("growthstock")) {
                    temp.addStock(new GrowthStock(tempStock[0], tempStock[1], Double.parseDouble(tempStock[2]), Integer.parseInt(tempStock[3]), Double.parseDouble(tempStock[4]), Double.parseDouble(tempStock[5])));
                } else {
                    temp.addStock(new PennyStock(tempStock[0], tempStock[1], Double.parseDouble(tempStock[2]), Integer.parseInt(tempStock[3]), Double.parseDouble(tempStock[4]), Double.parseDouble(tempStock[5])));
                }
            }
        }
        return new Account(splitInfo[0], Double.parseDouble(splitInfo[1]), temp, Double.parseDouble(splitInfo[3]));
    }

    public StockMarket getMarket() {
        String data = read().split("&-&")[1];
        String[] a = data.split("#");
        IStockPortfolio temp = new StockPortfolio();
        for (int i = 0; i < a.length; i++) {
            if (a[i].split("").length > 1) {
                String[] tempStock = a[i].split("/");
                if (tempStock[tempStock.length-1].equals("incomestock")) {
                    temp.addStock(new IncomeStock(tempStock[0], tempStock[1], Double.parseDouble(tempStock[2]), Integer.parseInt(tempStock[3]), Double.parseDouble(tempStock[4]), Double.parseDouble(tempStock[5])));
                } else if (tempStock[tempStock.length-1].equals("growthstock")) {
                    temp.addStock(new GrowthStock(tempStock[0], tempStock[1], Double.parseDouble(tempStock[2]), Integer.parseInt(tempStock[3]), Double.parseDouble(tempStock[4]), Double.parseDouble(tempStock[5])));
                } else {
                    temp.addStock(new PennyStock(tempStock[0], tempStock[1], Double.parseDouble(tempStock[2]), Integer.parseInt(tempStock[3]), Double.parseDouble(tempStock[4]), Double.parseDouble(tempStock[5])));
                }
            }
        }
        return new StockMarket(temp);
    }

    private String read() {
        String data = "-1";
        try {
            File myObj = new File(location);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
            }
            myReader.close();
        } catch (IOException  e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    public void write(Account account, StockMarket stockMarket) {
        Map<String, Stock> market = stockMarket.getPortfolio().getStockCollection();
        message = "";
        lmessage = "";
        try {
            FileWriter myWriter = new FileWriter(location);
            market.forEach((key,value) -> message+=(""+value.getLabel() + "/" + value.getOwner() + "/" + value.getPrice() + "/" + value.getShares() + "/" + value.getDividend() + "/" + value.getRatio() + "/" + ((value instanceof PennyStock) ? "pennystock" :  (value instanceof GrowthStock) ? "growthstock" :  (value instanceof IncomeStock) ? "incomestock" : "something went wrong")) + "#");
            account.getPortfolio().getStockCollection().forEach((key,value) -> lmessage+=(""+value.getLabel() + "/" + value.getOwner() + "/" + value.getPrice() + "/" + value.getShares() + "/" + value.getDividend() + "/" + value.getRatio() + "/" + ((value instanceof PennyStock) ? "pennystock" :  (value instanceof GrowthStock) ? "growthstock" :  (value instanceof IncomeStock) ? "incomestock" : "something went wrong")) + "#");
            myWriter.write(account.getUsername() + "-" + account.getBalance() + "-" + lmessage + "-" + account.getIncomeFromDividends() + "&-&"+message);
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
