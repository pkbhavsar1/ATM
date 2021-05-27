import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {
    JLabel  form_no,page_no,name,surname_name,dob,date,month,year,gender,email,martial,address,city,pin_code,state;
    JTextField name_field,surname_name_field,email_field,address_field,city_field,pin_code_field,state_field;
    JRadioButton male,female,married,unmarried,other;
    JComboBox date_box,month_box,year_box;
    JButton next;
    conn con = new conn();

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String getRan_form_no = "" + Math.abs(first4);

    Signup(){
        super("NEW ACCOUNT APPLICATION FORM");

        form_no = new JLabel("APPLICATION FORM NO. "+getRan_form_no);
        form_no.setFont(new Font("Raleway",Font.BOLD,38));

        page_no = new JLabel("Page 1: Personal Details");
        page_no.setFont(new Font("Raleway",Font.BOLD,22));

        name = new JLabel("Name: ");
        name.setFont(new Font("Raleway",Font.BOLD,20));

        surname_name = new JLabel("Surname Name: ");
        surname_name.setFont(new Font("Raleway",Font.BOLD,20));

        dob = new JLabel("Date Of Birth: ");
        dob.setFont(new Font("Raleway",Font.BOLD,20));

        gender = new JLabel("Gender: ");
        gender.setFont(new Font("Raleway",Font.BOLD,20));

        email = new JLabel("Email id: ");
        email.setFont(new Font("Raleway",Font.BOLD,20));

        martial = new JLabel("Martial Status");
        martial.setFont(new Font("Raleway",Font.BOLD,20));

        address = new JLabel("Address: ");
        address.setFont(new Font("Raleway",Font.BOLD,20));

        city = new JLabel("City: ");
        city.setFont(new Font("Raleway",Font.BOLD,20));

        pin_code = new JLabel("Pin Code: ");
        pin_code.setFont(new Font("Raleway",Font.BOLD,20));

        state = new JLabel("State: ");
        state.setFont(new Font("Raleway",Font.BOLD,20));

        date = new JLabel("Day:");
        date.setFont(new Font("Raleway",Font.BOLD,15));

        month = new JLabel("Month: ");
        month.setFont(new Font("Raleway",Font.BOLD,15));

        year = new JLabel("Year:");
        year.setFont(new Font("Raleway",Font.BOLD,15));

        name_field = new JTextField();
        name_field.setFont(new Font("Raleway",Font.BOLD,14));

        surname_name_field = new JTextField();
        surname_name_field.setFont(new Font("Raleway",Font.BOLD,14));

        email_field = new JTextField();
        email_field.setFont(new Font("Raleway",Font.BOLD,14));

        address_field = new JTextField();
        address_field.setFont(new Font("Raleway",Font.BOLD,14));

        city_field = new JTextField();
        city_field.setFont(new Font("Raleway",Font.BOLD,14));

        pin_code_field = new JTextField();
        pin_code_field.setFont(new Font("Raleway",Font.BOLD,14));

        state_field = new JTextField();
        state_field.setFont(new Font("Raleway",Font.BOLD,14));

        next = new JButton("Next");
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBackground(Color.black);
        next.setForeground(Color.white);

        male = new JRadioButton();
        male.setFont(new Font("Raleway",Font.BOLD,14));
        male.setBackground(Color.white);
        male.setText("Male");

        female = new JRadioButton();
        female.setFont(new Font("Raleway",Font.BOLD,14));
        female.setBackground(Color.white);
        female.setText("Female");

        ButtonGroup groupgender = new ButtonGroup();
        groupgender.add(male);
        groupgender.add(female);

        married = new JRadioButton();
        married.setFont(new Font("Raleway",Font.BOLD,14));
        married.setBackground(Color.white);
        married.setText("Married");

        unmarried = new JRadioButton();
        unmarried.setFont(new Font("Raleway",Font.BOLD,14));
        unmarried.setBackground(Color.white);
        unmarried.setText("Unmarried");

        other = new JRadioButton();
        other.setFont(new Font("Raleway",Font.BOLD,14));
        other.setBackground(Color.white);
        other.setText("Other");

        ButtonGroup groupstatus = new ButtonGroup();
        groupstatus.add(married);
        groupstatus.add(unmarried);
        groupstatus.add(other);

        String[] date_string = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
        date_box = new JComboBox(date_string);
        date_box.setBackground(Color.white);

        String[] month_string = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        month_box = new JComboBox(month_string);
        month_box.setBackground(Color.white);

        String[] year_string = {"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2003","2004"};
        year_box = new JComboBox(year_string);
        year_box.setBackground(Color.white);

        setLayout(null);
//        form_no,page_no,name,dad_name,dob,date,month,year,gender,email,martial,address,city,pin_code,state;
        form_no.setBounds(140,20,600,40);
        add(form_no);

        page_no.setBounds(290,80,600,30);
        add(page_no);

        name.setBounds(100,140,100,30);
        add(name);

        name_field.setBounds(300,140,400,30);
        add(name_field);

        surname_name.setBounds(100,190,200,30);
        add(surname_name);

        surname_name_field.setBounds(300,190,400,30);
        add(surname_name_field);

        dob.setBounds(100,240,200,30);
        add(dob);

        date.setBounds(300,240,40,30);
        add(date);
        date_box.setBounds(340,240,60,30);
        add(date_box);

        month.setBounds(410,240,60,30);
        add(month);
        month_box.setBounds(460,240,100,30);
        add(month_box);

        year.setBounds(570,240,40,30);
        add(year);
        year_box.setBounds(610,240,90,30);
        add(year_box);

        gender.setBounds(100,290,200,30);
        add(gender);

        male.setBounds(300,290,60,30);
        add(male);

        female.setBounds(450,290,90,30);
        add(female);

        email.setBounds(100,340,200,30);
        add(email);

        email_field.setBounds(300,340,400,30);
        add(email_field);

        martial.setBounds(100,390,200,30);
        add(martial);

        married.setBounds(300,390,100,30);
        add(married);

        unmarried.setBounds(450,390,100,30);
        add(unmarried);

        other.setBounds(635,390,100,30);
        add(other);



        address.setBounds(100,440,200,30);
        add(address);

        address_field.setBounds(300,440,400,30);
        add(address_field);

        city.setBounds(100,490,200,30);
        add(city);

        city_field.setBounds(300,490,400,30);
        add(city_field);

        pin_code.setBounds(100,540,200,30);
        add(pin_code);

        pin_code_field.setBounds(300,540,400,30);
        add(pin_code_field);

        state.setBounds(100,590,200,30);
        add(state);

        state_field.setBounds(300,590,400,30);
        add(state_field);

        next.setBounds(620,660,80,30);
        add(next);

        next.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        next.addActionListener(this);
        setSize(850,800);
        setLocation(500,120);
        setVisible(true);
    }
    public boolean checkDetails(){
        if(getRan_form_no==null){
            return false;
        }
        if (name_field.getText().equals("")){
            return false;
        }
        if (email_field.getText().equals("")){
            return false;
        }
        if (pin_code_field.getText().equals("")){
            return false;
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String form_no_s = getRan_form_no;
        String name_s = name_field.getText().toUpperCase();
        String surname_name_s = surname_name_field.getText().toUpperCase();
        String dob_s = ((String) date_box.getSelectedItem()+"/"+month_box.getSelectedItem()+"/"+year_box.getSelectedItem()).toUpperCase();
        String email_s = email_field.getText();
        String address_s = address_field.getText().toUpperCase();
        String city_s = city_field.getText().toUpperCase();
        String pincode_s = pin_code_field.getText();
        String state_s = state_field.getText().toUpperCase();
        String gender_s = null;
        String status_s = null;
//        Gender Selection
        if (male.isSelected()){
            gender_s = "Male".toUpperCase();
        }
        else if(female.isSelected()){
            gender_s="Female".toUpperCase();
        }
        else if(other.isSelected()){
            gender_s = "Other".toUpperCase();
        }
//        Martial Status Selection
        if (married.isSelected()){
            status_s="Married".toUpperCase();
        }
        else if (unmarried.isSelected()){
            status_s = "Unmarried".toUpperCase();
        }

//        Required all the Fields
        try {
            if (e.getSource() == next) {
                if (checkDetails()==false) {
                    JOptionPane.showMessageDialog(null, "Please Fill Requied Fields");
                }
                else {
                    String query = "insert into signup values('" + form_no_s + "','" + name_s + "','" + surname_name_s + "','" + dob_s + "','" + gender_s + "','" + email_s + "','" + status_s + "','" + address_s + "','" + city_s + "','" + pincode_s + "','" + state_s + "')";
                    con.stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Please remember your form number");
                    setVisible(false);
                    new Signup2(form_no_s).setVisible(true);
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

//    public static void main(String[] args) {
//        new Signup();
//    }
}
