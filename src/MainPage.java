import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPage extends JFrame implements ActionListener {
    JLabel option;
    JButton deposit, withdraw, transfer, mini_statement, pin_change, bal_enquiry,exit;
    String pin;
    String card_no;
    conn con = new conn();
    MainPage(String pin,String card_no){
        super("Main Page");
        this.pin = pin;
        this.card_no = card_no;
        option = new JLabel("Please select your option");
        option.setForeground(Color.black);
        option.setFont(new Font("Raleway",Font.BOLD,40));
        option.setBounds(150,100,550,100);
        add(option);

        deposit = new JButton("Deposit");
        deposit.setBackground(Color.black);
        deposit.setForeground(Color.white);
        deposit.setFont(new Font("Raleway",Font.BOLD,20));
        deposit.setBounds(100,300,200,50);
        add(deposit);

        withdraw = new JButton("Cash Withdrawal");
        withdraw.setBackground(Color.black);
        withdraw.setForeground(Color.white);
        withdraw.setFont(new Font("Raleway",Font.BOLD,20));
        withdraw.setBounds(470,300,200,50);
        add(withdraw);

        transfer = new JButton("Transfer");
        transfer.setBackground(Color.black);
        transfer.setForeground(Color.white);
        transfer.setFont(new Font("Raleway",Font.BOLD,20));
        transfer.setBounds(100,400,200,50);
        add(transfer);

        mini_statement = new JButton("Mini-Statement");
        mini_statement.setBackground(Color.black);
        mini_statement.setForeground(Color.white);
        mini_statement.setFont(new Font("Raleway",Font.BOLD,20));
        mini_statement.setBounds(470,400,200,50);
        add(mini_statement);

        pin_change = new JButton("Pin Change");
        pin_change.setBackground(Color.black);
        pin_change.setForeground(Color.white);
        pin_change.setFont(new Font("Raleway",Font.BOLD,20));
        pin_change.setBounds(100,500,200,50);
        add(pin_change);

        bal_enquiry = new JButton("Balance Enquiry");
        bal_enquiry.setBackground(Color.black);
        bal_enquiry.setForeground(Color.white);
        bal_enquiry.setFont(new Font("Raleway",Font.BOLD,20));
        bal_enquiry.setBounds(470,500,200,50);
        add(bal_enquiry);

        exit = new JButton("Exit");
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.setFont(new Font("Raleway",Font.BOLD,20));
        exit.setBounds(280,600,200,50);
        add(exit);

        setLayout(null);

        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        transfer.addActionListener(this);
        mini_statement.addActionListener(this);
        pin_change.addActionListener(this);
        bal_enquiry.addActionListener(this);
        exit.addActionListener(this);

        setSize(750,800);
        setLocation(100,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deposit){
            setVisible(false);
            new Deposit(card_no,pin).setVisible(true);
        }
        if (e.getSource()==withdraw){
            setVisible(false);
            new Withdraw(card_no,pin).setVisible(true);
        }
        if (e.getSource()==exit){
            setVisible(false);
            new Login().setVisible(true);
        }
        if (e.getSource()==pin_change){
            setVisible(false);
            new PinChange(pin,card_no).setVisible(true);
        }
        if(e.getSource()==bal_enquiry){
            String bal_enq_pin=JOptionPane.showInputDialog("Enter your pin");
            if (bal_enq_pin.equals(pin)) {
                String query = "select balance from login where card_no='" + card_no + "' and pin='" + bal_enq_pin + "'";
                try {
                    ResultSet resultSet = con.stmt.executeQuery(query);
                    if (resultSet.next()) {
                        String bal = resultSet.getString(1);
                        JOptionPane.showMessageDialog(null, "Your Current balance is: '" + bal + "'");
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong");
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect PIN: ");
            }
        }
//        if ()

    }

    public static void main(String[] args) {
        new MainPage("5676","5040936010563213");
    }
}
