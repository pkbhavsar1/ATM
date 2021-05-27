import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    JLabel welcome,card_no,pin;
    JTextField card_no_field;
    JPasswordField password_field;
    JButton sign_in,clear,sign_up;
    conn con = new conn();
    Login(){
        super("AUTOMATED TELLER MACHINE");
//      Setting Label
        welcome = new JLabel("WELCOME TO ATM");
        welcome.setFont(new Font("Osward",Font.BOLD,38));

        card_no = new JLabel("Card No:");
        card_no.setFont(new Font("Raleway",Font.BOLD,28));

        pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,28));

        card_no_field = new JTextField(15);

        password_field = new JPasswordField(15);

        sign_in = new JButton("Sign in");
        sign_in.setBackground(Color.black);
        sign_in.setForeground(Color.white);

        clear = new JButton("Clear");
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);

        sign_up = new JButton("Sign up");
        sign_up.setBackground(Color.black);
        sign_up.setForeground(Color.white);

        setLayout(null);
        welcome.setBounds(175,50,450,200);
        add(welcome);

        card_no.setBounds(125,150,375,200);
        add(card_no);

        pin.setBounds(125,225,375,200);
        add(pin);

        card_no_field.setFont(new Font("Arial",Font.BOLD,14));
        card_no_field.setBounds(300,235,230,30);
        add(card_no_field);

        password_field.setFont(new Font("Arial",Font.BOLD,14));
        password_field.setBounds(300,310,230,30);
        add(password_field);

        sign_in.setFont(new Font("Arial",Font.BOLD,14));
        sign_in.setBounds(300,400,100,30);
        add(sign_in);

        clear.setFont(new Font("Arial",Font.BOLD,14));
        clear.setBounds(430,400,100,30);
        add(clear);

        sign_up.setFont(new Font("Arial",Font.BOLD,14));
        sign_up.setBounds(300,450,230,30);
        add(sign_up);

        sign_in.addActionListener(this::actionPerformed);
        clear.addActionListener(this::actionPerformed);
        sign_up.addActionListener(this::actionPerformed);

        getContentPane().setBackground(Color.white);

        setSize(750,750);
        setLocation(500,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String card_no_s = card_no_field.getText();
        String pin_s = String.valueOf(password_field.getPassword());
        if (e.getSource()==sign_in){
            String[] card_pin = null;
            String query = "select card_no, pin from login where card_no='"+card_no_s+"' and '"+pin_s+"'";
            try {
                ResultSet resultSet = con.stmt.executeQuery(query);
                if (resultSet.next()){
                    String card_d= String.valueOf(resultSet.getLong(1));
                    String pin_d = String.valueOf(resultSet.getInt(2));
                    System.out.println(card_d + "\n" +pin_d);
                    System.out.println(card_no_s + "\n" +pin_s);
                    if (card_d.equals(card_no_s) && pin_d.equals(pin_s)){
                        String verify_query = "select verified from login where card_no='"+card_no_s+"'";
                        ResultSet verfy_res = con.stmt.executeQuery(verify_query);
                        if (verfy_res.next()){
                            String verified=verfy_res.getString(1);
                            if (verified.equals("Yes")){
                                setVisible(false);
                                new MainPage(pin_s,card_no_s).setVisible(true);
                            }
                            else {
                                setVisible(false);
                                new Login().setVisible(true);
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Failed");
                    }
//
                }
                else {
                    JOptionPane.showMessageDialog(null,"Failed to get data");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        else if(e.getSource()==sign_up){
            setVisible(false);
            new Signup().setVisible(true);
        }
        else if(e.getSource()==clear){
            card_no_field.setText("");
            password_field.setText("");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
