import java.util.Scanner;

public class main
{
    public static void main(String[] args) {
        String _input = "";
        Display _interface = null;
        while (!(_input.toUpperCase().equals("CLI")) && !(_input.toUpperCase().equals("GUI")) && !(_input.toLowerCase().equals("exit"))) {
            _input = input("Do you want to open the CLI or GUI version of the Stock Market?");

            if (_input.toUpperCase().equals("CLI")) {
                _interface = new CLI();
            } else if (_input.toUpperCase().equals("GUI")) {
                _interface = new GUI();
            } else if (!(_input.toLowerCase().equals("exit"))) {
                System.out.println("please write CLI or GUI or Exit to quit.");
            }
        }
        if (!(_input.toLowerCase().equals("exit"))) {
            _interface.display();
        }
    }

    private static String input(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
}
