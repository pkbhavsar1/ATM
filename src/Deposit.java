import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Deposit extends JFrame implements ActionListener {
    JLabel heading,heading2;
    JTextField amount;
    JButton deposit,back,exit;
    String card_no;
    String pin;
    conn con = new conn();
    Deposit(String card_no,String pin){
        this.card_no = card_no;
        this.pin = pin;

        heading = new JLabel("ENTER THE AMOUNT");
        heading.setFont(new Font("Raleway", Font.BOLD, 35));
        heading.setBounds(100, 100, 400, 30);
        add(heading);

        heading2 = new JLabel("YOU WANT TO DEPOSIT");
        heading2.setFont(new Font("Raleway", Font.BOLD, 35));
        heading2.setBounds(80, 140, 480, 30);
        add(heading2);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 20));
        amount.setBounds(100, 220, 350, 30);
        add(amount);

        deposit = new JButton("DEPOSIT");
        deposit.setFont(new Font("Raleway", Font.BOLD, 20));
        deposit.setBounds(100, 280, 160, 50);
        deposit.setForeground(Color.white);
        deposit.setBackground(Color.black);
        add(deposit);

        back = new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD, 20));
        back.setBounds(295, 280, 160, 50);
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        add(back);

        exit = new JButton("EXIT");
        exit.setFont(new Font("Raleway", Font.BOLD, 20));
        exit.setBounds(180, 400, 160, 50);
        exit.setForeground(Color.white);
        exit.setBackground(Color.black);
        add(exit);

        deposit.addActionListener(this);
        back.addActionListener(this);
        exit.addActionListener(this);

        setLayout(null);
        setLocation(350, 10);
        setSize(600, 750);
        setBackground(Color.white);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deposit){
            String query;
            String update_query;
            int curr_balance = 0;
            long balance = Long.parseLong(amount.getText());
            String get_pin = JOptionPane.showInputDialog("Enter Your PIN");
            if (pin.equals(get_pin)){
                query = "select balance from login where card_no='"+card_no+"'";
                try {
                    ResultSet resultSet = con.stmt.executeQuery(query);
                    if (resultSet.next()){
                        curr_balance = resultSet.getInt(1);
                        System.out.println("Curr Balance: "+curr_balance);
                    }
                    balance += curr_balance;
                    System.out.println("Balance:"+balance);
                    update_query = "update login set balance='"+balance+"' where card_no='"+card_no+"'";
                    con.stmt.executeUpdate(update_query);
                    JOptionPane.showMessageDialog(null,"Balance Updated Successfully");
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Your PIN is incorrect");
            }
        }
        else if(e.getSource()==back){
            setVisible(false);
            new MainPage(pin,card_no).setVisible(true);
        }
        else if (e.getSource()==exit){
            setVisible(false);
            new Login().setVisible(true);
        }
    }
//
//    public static void main(String[] args) {
//        new Deposit("5040936010563213","922");
//    }
}
