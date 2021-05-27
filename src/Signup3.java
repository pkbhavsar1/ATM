import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import java.util.TimerTask;

public class Signup3 extends JFrame implements ActionListener {
    JLabel form_no,acc_details,acc_type,card_no,card_no_mess,hide_card_no,hide_card_no_mess,pin,pin_mess,hide_pin_no,serv_req;
    JTextField form_field;
    JRadioButton save_acc,fd_acc,curr_acc,rd_acc;
    JCheckBox atm,internet_bank,mobile_bank,email_alert,check_book,e_statement,declaration;
    JButton submit,cancel;
    String fno;
    conn con = new conn();

    Signup3(String from_no){
        super("NEW ACCOUNT APPLICATION FORM");
        this.fno = from_no;
//        l1
        acc_details = new JLabel("PAGE 3: ACCOUNT DETAILS");
        acc_details.setFont(new Font("Raleway",Font.BOLD,22));
//        l2
        acc_type = new JLabel("Account Type:");
        acc_type.setFont(new Font("Raleway",Font.BOLD,18));
//        l3
        card_no = new JLabel("Card Number:");
        card_no.setFont(new Font("Raleway",Font.BOLD,18));
//        l4
        hide_card_no = new JLabel("XXXX-XXXX-XXXX-4184");
        hide_card_no.setFont(new Font("Raleway",Font.BOLD,18));

//        l5
        card_no_mess = new JLabel("(Your 16-digit Card number)");
        card_no_mess.setFont(new Font("Raleway",Font.BOLD,12));

//        l6
        hide_card_no_mess = new JLabel("It would apper on ATM Card/Cheque Book and Statements");
        hide_card_no_mess.setFont(new Font("Raleway",Font.BOLD,12));

//      l7
        pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,18));

//        l8
        hide_pin_no = new JLabel("XXXX");
        hide_pin_no.setFont(new Font("Raleway",Font.BOLD,18));

//        l9
        pin_mess = new JLabel("(4-digit passowrd)");
        pin_mess.setFont(new Font("Raleway",Font.BOLD,12));

//        l10
        serv_req = new JLabel("Service Required");
        serv_req.setFont(new Font("Raleway",Font.BOLD,18));

//        l11
        form_no = new JLabel("Form No:");
        form_no.setFont(new Font("Raleway",Font.BOLD,14));

//        b1
        submit = new JButton("Submit");
        submit.setFont(new Font("Raleway",Font.BOLD,14));
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);

//        b2
        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Raleway",Font.BOLD,14));
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);

//        c1
        atm = new JCheckBox("ATM CARD");
        atm.setFont(new Font("Raleway",Font.BOLD,16));
        atm.setBackground(Color.white);

//        c2
        internet_bank = new JCheckBox("Internet Banking");
        internet_bank.setFont(new Font("Raleway",Font.BOLD,16));
        internet_bank.setBackground(Color.white);

//        c3
        mobile_bank = new JCheckBox("Mobile Banking");
        mobile_bank.setBackground(Color.white);
        mobile_bank.setFont(new Font("Raleway",Font.BOLD,16));

//        c4
        email_alert = new JCheckBox("Email Alerts");
        email_alert.setBackground(Color.white);
        email_alert.setFont(new Font("Raleway",Font.BOLD,16));

//        c5
        check_book = new JCheckBox("Cheque Book");
        check_book.setBackground(Color.white);
        check_book.setFont(new Font("Raleway",Font.BOLD,16));

//        c6
        e_statement = new JCheckBox("E-Statement");
        e_statement.setFont(new Font("Raleway",Font.BOLD,16));
        e_statement.setBackground(Color.white);

//        c7
        declaration = new JCheckBox("I hearby declare that the above entered details is correct to the best of the my knowledge.",true);
        declaration.setFont(new Font("Raleway",Font.BOLD,12));
        declaration.setBackground(Color.white);

//        r1
        save_acc = new JRadioButton("Saving Account");
        save_acc.setFont(new Font("Raleway",Font.BOLD,16));
        save_acc.setBackground(Color.white);

//        r2
        fd_acc = new JRadioButton("Fixed Deposit Account");
        fd_acc.setFont(new Font("Raleway",Font.BOLD,16));
        fd_acc.setBackground(Color.white);

//        r3
        curr_acc = new JRadioButton("Current Account");
        curr_acc.setFont(new Font("Raleway",Font.BOLD,16));
        curr_acc.setBackground(Color.white);

//        r4
        rd_acc = new JRadioButton("Recurring Deposit Account");
        rd_acc.setFont(new Font("Raleway",Font.BOLD,16));
        rd_acc.setBackground(Color.white);

        ButtonGroup acc_type_group = new ButtonGroup();
        acc_type_group.add(save_acc);
        acc_type_group.add(fd_acc);
        acc_type_group.add(curr_acc);
        acc_type_group.add(rd_acc);

//        t1
        form_field = new JTextField();
        form_field.setFont(new Font("Raleway",Font.BOLD,12));

        setLayout(null);


        form_no.setBounds(700,10,70,30);
        add(form_no);

        form_field.setBounds(770,10,40,30);
        add(form_field);
//        l1
        acc_details.setBounds(280,50,400,40);
        add(acc_details);
//        l2
        acc_type.setBounds(100,140,200,30);
        add(acc_type);

        save_acc.setBounds(100,180,150,30);
        add(save_acc);

        fd_acc.setBounds(350,180,300,30);
        add(fd_acc);

        curr_acc.setBounds(100,220,250,30);
        add(curr_acc);

        rd_acc.setBounds(350,220,250,30);
        add(rd_acc);

        card_no.setBounds(100,300,200,30);
        add(card_no);

        hide_card_no.setBounds(330,300,250,30);
        add(hide_card_no);

        card_no_mess.setBounds(100,330,200,20);
        add(card_no_mess);

        hide_card_no_mess.setBounds(330,330,500,20);
        add(hide_card_no_mess);

        pin.setBounds(100,370,200,30);
        add(pin);

        hide_pin_no.setBounds(330,370,200,30);
        add(hide_pin_no);

        pin_mess.setBounds(100,400,200,20);
        add(pin_mess);

        serv_req.setBounds(100,450,200,30);
        add(serv_req);

        atm.setBounds(100,500,200,30);
        add(atm);

        internet_bank.setBounds(350,500,200,30);
        add(internet_bank);

        mobile_bank.setBounds(100,550,200,30);
        add(mobile_bank);

        email_alert.setBounds(350,550,200,30);
        add(email_alert);

        check_book.setBounds(100,600,200,30);
        add(check_book);

        e_statement.setBounds(350,600,200,30);
        add(e_statement);

        declaration.setBounds(100,680,600,20);
        add(declaration);

        submit.setBounds(300,720,100,30);
        add(submit);

        cancel.setBounds(420,720,100,30);
        add(cancel);

        getContentPane().setBackground(Color.white);
        setSize(850,850);
        setLocation(500,90);
        setVisible(true);

        submit.addActionListener(this);
        cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String service_req_s=null;
        String acc_type_s = null;
        if (save_acc.isSelected()) acc_type_s = "Saving Account";
        else if(fd_acc.isSelected()) acc_type_s = "Fixed Deposit Account";
        else if(curr_acc.isSelected()) acc_type_s = "Current Account";
        else if(rd_acc.isSelected()) acc_type_s = "Recurring Deposit Account";

        Random ran = new Random();
        long rand_card = (ran.nextLong()%90000000L)+5040936000000000L;
        String rand_card_s = Long.toString(Math.abs(rand_card));

        long rand_pin = (ran.nextLong()%9000L)+1000L;
        long rand_pin_s = Math.abs(rand_pin);

        if (atm.isSelected()) service_req_s += "ATM Card ";
        if (internet_bank.isSelected()) service_req_s += "Internet Banking ";
        if (mobile_bank.isSelected()) service_req_s += "Mobile Banking ";
        if (email_alert.isSelected()) service_req_s += "Email Alerts ";
        if (check_book.isSelected()) service_req_s += "Cheque Book ";
        if (e_statement.isSelected()) service_req_s += "E-Statement ";

        try {
            if (e.getSource()==submit){
                if (form_field.getText().equals(fno) && acc_type_s!=null && service_req_s!=null){
                    String query1 = "insert into signup3 values('"+fno+"','"+acc_type_s+"','"+rand_card_s+"','"+rand_pin_s+"','"+service_req_s+"')";
                    con.stmt.executeUpdate(query1);
                    System.out.println("Query 1 Executed Successfully");
                    String query2 = "insert into login(card_no, pin) values('"+rand_card_s+"','"+rand_pin_s+"')";
                    con.stmt.executeUpdate(query2);
                    System.out.println("Query 2 Executed Successfully");
                    setVisible(false);
                    new SendEmail(fno,rand_card_s);
                    JOptionPane.showMessageDialog(null,"Card Number: '"+rand_card_s+"'\n Pin:'"+rand_pin_s+"'");

                }
                else {
                    JOptionPane.showMessageDialog(null,"Field the required fields");
                }
            }
            if (e.getSource()==cancel){
                System.exit(0);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup3("9147");
    }
}
