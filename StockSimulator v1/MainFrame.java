import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class MainFrame extends JFrame{
    public MainFrame(String title, StockMarket market) {
        super(title);

        setLayout(new GridBagLayout());

        JButton viewMarketButton = new JButton("view Market");
        JButton viewAccountButton = new JButton("View Account");
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.SOUTH;
        gc.weightx = .5;
        gc.weighty = .5;

        gc.gridx = 0;
        gc.gridy = 0;
        add(viewMarketButton, gc);

        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 0;
        gc.gridy = 1;
        add(viewAccountButton, gc);
        
        // c.add(textArea, BorderLayout.CENTER);
        // c.add(button, BorderLayout.SOUTH);
        // c.add(button2, BorderLayout.SOUTH);

        viewMarketButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenInterface menu = new GUIinterface();
                menu.displayStockMarket(market.getCollection());
            }
        });
        viewAccountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenInterface menu = new GUIinterface();
                menu.displayAccount();
            }
        });
    }
}
