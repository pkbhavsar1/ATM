//import oracle.net.ns.SessionAtts;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SendEmail extends JFrame implements ActionListener {
    String message = null;
    String subject = "Profile Verification Mail";
    String to = null;
    String from = "ab727668620@gmail.com";
    String form_no;
    String card_no;
    long otp;
    conn con = new conn();
    static Timer timer = new Timer();

    JLabel verification,request,otp_l;
    JTextField otp_field;
    JButton verify,cancle;


    SendEmail(String form_no, String card_no){
        super("Verification");
        this.card_no = card_no;
        verification = new JLabel("VERIFICATION PAGE");
        verification.setForeground(Color.black);
        verification.setFont(new Font("Raleway",Font.BOLD,25));
        verification.setBounds(130,5,250,30);
        add(verification);

        otp_l = new JLabel("OTP: ");
        otp_l.setForeground(Color.black);
        otp_l.setFont(new Font("Raleway",Font.BOLD,20));
        otp_l.setBounds(100,80,100,30);
        add(otp_l);

        otp_field = new JTextField();
        otp_field.setFont(new Font("Raleway",Font.BOLD,15));
        otp_field.setBounds(160,85,150,20);
        add(otp_field);

        request = new JLabel("(After 2 minutes new OTP send automatically once)");
        request.setFont(new Font("Raleway",Font.BOLD,8));
        request.setBounds(110,120,200,15);
        add(request);

        verify = new JButton("Verify");
        verify.setFont(new Font("Raleway",Font.BOLD,15));
        verify.setBackground(Color.black);
        verify.setForeground(Color.white);
        verify.setBounds(100,180,100,30);
        add(verify);

        cancle = new JButton("Cancel");
        cancle.setFont(new Font("Raleway",Font.BOLD,15));
        cancle.setBackground(Color.black);
        cancle.setForeground(Color.white);
        cancle.setBounds(250,180,100,30);
        add(cancle);

        verify.addActionListener(this);
        cancle.addActionListener(this);

        setLayout(null);
        setSize(500,300);
        setLocation(100,100);
        setVisible(true);
        this.form_no=form_no;
        getSendTo();
        getMessage();
        sendOtpMail(message,subject,to,from);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null,"New OTP Send");
                sendOtpMail(message,subject,to,from);
                System.out.println("Email sent once again");
                timer.cancel();
            }
        };
        timer.schedule(task,300000,1000);
        System.out.println("Successfully Delivered");
    }

    public void getSendTo(){
        String query = "select email_d from signup where form_no ='"+form_no+"'";
        try {
            ResultSet resultSet = con.stmt.executeQuery(query);
            while (resultSet.next()) {
                to = resultSet.getString(1);
            }
            System.out.println("To: "+to);
        } catch (SQLException throwables) {
            System.out.println("SendEmail Query Unhandled");
            throwables.printStackTrace();
        }
    }
    public void getMessage(){
        String query = "select name from signup where form_no = '"+form_no+"'";
        String name = null;
        Random random = new Random();
        otp = (long) (Math.random()*(999999 - 100000 + 1)+100000);
        try {
            ResultSet resultSet = con.stmt.executeQuery(query);
            while (resultSet.next()){
                name = resultSet.getString(1);
            }
            message = "Dear '"+name+"' your OTP is '"+otp+"' \n PLEASE DO NOT SHARE WITH ANYONE ";
            System.out.println("Message: "+message);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //    this method is responsible for sending mail without attachment
    public void sendOtpMail(String message, String subject, String to, String from){
//        variable for Gmail
        String host = "smtp.gmail.com";

//        get system properties
        Properties properties = new Properties();
        System.out.println("Properties: "+properties);

//        Setting the important information to properties object

//        host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

//        Step 1: to get the session object
        Session session;
        session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ab727668620@gmail.com","Golu@245701");
            }
        });

//        session.setDebug(true);

//        Step 2: Compose Message
        MimeMessage mimeMessage = new MimeMessage(session);

        try{
//            from Email
            mimeMessage.setFrom(from);

//            Adding Recipient,Header,Text to Mail
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.addHeaderLine(subject);
            mimeMessage.setText(message);

//            Step 3: Send Message using Transport Class
            Transport.send(mimeMessage);
            System.out.println("Sent Successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==verify){
            if (otp_field.getText().equals(Long.toString(otp))){
//                String query = "insert into login(verified) values(\"yes\") where card_no='"+card_no+"'";
                String query = "update login set verified=\"Yes\" where card_no='"+card_no+"'";
                try {
                    con.stmt.executeUpdate(query);
                } catch (SQLException throwables) {
                    System.out.println("Yes Execute Update failed");
                    throwables.printStackTrace();
//                    System.out.println("SendEmail Verify Query Fail");
                }

                setVisible(false);
                new Login().setVisible(true);
                JOptionPane.showMessageDialog(null,"Your Email-Id is successfully verified");
                timer.cancel();

            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect OTP \n Enter Valid OTP");
//                timer.cancel();
//                sendOtpMail(message,subject,to,from);
            }
        }
        else if (e.getSource()==cancle){
            String query = "update login set verified=\"No\" where card_no='"+card_no+"'";
            try {
                con.stmt.executeUpdate(query);
            } catch (SQLException throwables) {
                System.out.println("No Execute update failed");
                throwables.printStackTrace();
            }
            setVisible(false);
            new Login().setVisible(true);

            JOptionPane.showMessageDialog(null,"Your Email-Id is Not verified");
            timer.cancel();
        }
    }
//    public static void main(String[] args) {
////        System.out.println("Preparing to send message...");
//            new SendEmail("947","");
//
////        sendEmail.sendOtpMail(message,subject,to,from);
//    }


}
