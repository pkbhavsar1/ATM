import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PinChange extends JFrame implements ActionListener {
    String pin,card_no;
    JPasswordField passwordField,passwordField2;
    JButton change,back;
    JLabel change_pin,new_pin,reenter_pin;
    conn con = new conn();
    PinChange(String pin, String card_no){
        this.pin = pin;
        this.card_no = card_no;
        change_pin = new JLabel("CHANGE YOUR PIN");
        change_pin.setFont(new Font("System", Font.BOLD, 35));

        new_pin = new JLabel("New PIN:");
        new_pin.setFont(new Font("System", Font.BOLD, 17));

        reenter_pin = new JLabel("Re-Enter New PIN:");
        reenter_pin.setFont(new Font("System", Font.BOLD, 17));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Raleway", Font.BOLD, 25));

        passwordField2 = new JPasswordField();
        passwordField2.setFont(new Font("Raleway", Font.BOLD, 25));

        change = new JButton("CHANGE");
        change.setFont(new Font("Raleway",Font.BOLD,25));
        change.setBackground(Color.black);
        change.setForeground(Color.white);

        back = new JButton("BACK");
        back.setFont(new Font("Raleway",Font.BOLD,25));
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        change.addActionListener(this);
        back.addActionListener(this);

        setLayout(null);

        change_pin.setBounds(100,130,800,35);
        add(change_pin);

        new_pin.setBounds(100,220,150,35);
        add(new_pin);

        reenter_pin.setBounds(100,280,200,35);
        add(reenter_pin);

        passwordField.setBounds(260,220,180,25);
        add(passwordField);

        passwordField2.setBounds(260,280,180,25);
        add(passwordField2);

        change.setBounds(100,388,150,35);
        add(change);

        back.setBounds(300,388,150,35);
        add(back);

        setSize(500,600);
        setLocation(500,100);
//        setUndecorated(true);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==change){
            String password1 = String.valueOf(passwordField.getPassword());
            String password2 = String.valueOf(passwordField2.getPassword());
                if (password1.equals(password2)){
                    try{
                        String query1 = "update login set pin='"+String.valueOf(passwordField.getPassword())+"' where pin='"+pin+"' and card_no='"+card_no+"'";
                        con.stmt.executeUpdate(query1);
                        System.out.println("Password Updated in Login Table");

                        String query2 = "update signup3 set pin='"+String.valueOf(passwordField.getPassword())+"' where pin='"+pin+"' and card_no='"+card_no+"'";
                        con.stmt.executeUpdate(query2);
                        System.out.println("Password updated in Signup3 table");
                    }
                    catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Password doesn't match");
                }
            }
        if (e.getSource()==back){
            setVisible(false);
            new MainPage(pin,card_no).setVisible(true);
        }
    }


    public static void main(String[] args) {
        new PinChange("922","5040936010563213");
    }
}
