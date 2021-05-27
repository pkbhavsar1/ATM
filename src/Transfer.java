import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer extends JFrame implements ActionListener {
    String card_no,pin;

    JLabel head,from,to,amount;
    JTextField from_field,to_field,amount_field;
    JButton transfer,back;

    conn con = new conn();
    Transfer(String card_no,String pin){
        this.card_no = card_no;
        this.pin = pin;

        head = new JLabel("Transfer Money");
        head.setFont(new Font("Raleway",Font.BOLD,40));
        head.setBounds(150,50,300,50);
        add(head);

        from = new JLabel("FROM: ");
        from.setFont(new Font("Raleway",Font.BOLD,20));
        from.setBounds(70,200,200,50);
        add(from);

        from_field = new JTextField();
        from_field.setFont(new Font("Raleway",Font.BOLD,20));
        from_field.setBounds(180,200,350,40);
        add(from_field);

        to = new JLabel("TO: ");
        to.setFont(new Font("Raleway",Font.BOLD,20));
        to.setBounds(70,270,200,50);
        add(to);

        to_field = new JTextField();
        to_field.setFont(new Font("Raleway",Font.BOLD,20));
        to_field.setBounds(180,270,350,40);
        add(to_field);

        amount = new JLabel("AMOUNT: ");
        amount.setFont(new Font("Raleway",Font.BOLD,20));
        amount.setBounds(70,340,200,50);
        add(amount);

        amount_field = new JTextField();
        amount_field.setFont(new Font("Raleway",Font.BOLD,20));
        amount_field.setBounds(180,340,350,40);
        add(amount_field);

        transfer = new JButton("TRANSFER");
        transfer.setForeground(Color.white);
        transfer.setBackground(Color.black);
        transfer.setFont(new Font("Raleway",Font.BOLD,20));
        transfer.setBounds(70,450,200,50);
        transfer.addActionListener(this);
        add(transfer);

        back = new JButton("BACK");
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setFont(new Font("Raleway",Font.BOLD,20));
        back.setBounds(330,450,200,50);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setLocation(500,100);
        setSize(600,700);
        setVisible(true);
    }
    public boolean checkField(){
        if(from_field.getText().equals("")){
            return false;
        }
        if (to_field.getText().equals("")){
            return false;
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String query,to_update_query,from_update_query;
        String to_card = to_field.getText();
        String from_card = from_field.getText();
        String amount = amount_field.getText();
        if (e.getSource()==transfer){
            if (checkField()){
                try {
                    long from_before_update=0,from_after_update=0;
                    query = "select balance from login where card_no='" + from_card + "'";
                    ResultSet from_bal = con.stmt.executeQuery(query);
                    if (from_bal.next()){
                        from_before_update = from_bal.getLong(1);
                    }
                    from_after_update = from_before_update-Long.parseLong(amount);
                    if(from_after_update>0) {
                        to_update_query = "update login set balance='" + from_after_update + "' where card_no='" + from_card + "'";
                        con.stmt.executeUpdate(to_update_query);

                        try {
                            long to_before_update=0,to_after_update=0;
                            query = "select balance from login where card_no='" + to_card + "'";
                            ResultSet to_bal = con.stmt.executeQuery(query);
                            if (to_bal.next()){
                                to_before_update = to_bal.getLong(1);
                            }
                            to_after_update = to_before_update + Long.parseLong(amount);
                            to_update_query = "update login set balance='"+to_after_update+"' where card_no='"+to_card+"'";
                            con.stmt.executeUpdate(to_update_query);
                            JOptionPane.showMessageDialog(null,"Balance Update successfully");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    else {
                        setVisible(false);
                        new MainPage(pin,card_no).setVisible(true);
                        JOptionPane.showMessageDialog(null,"Insufficient Funds");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            else {
                JOptionPane.showMessageDialog(null,"Please fill the required fields");
            }

        }
        else if (e.getSource()==back){
            setVisible(false);
            new MainPage(pin,card_no).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Transfer("","");
    }
}
