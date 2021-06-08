import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {
    JLabel  form_no, add_details, religion, category, income, edu1, edu2, occupation, pan_no, aadhar_no, senior_citizen, existing_acc;
    JButton next;
    JRadioButton senior_y,senior_n,existing_acc_y,existing_acc_n;
    JComboBox religion_box,category_box,income_box,edu_box,occupation_box;
    JTextField pan_filed, aadhar_field,form_field;
    String get_formno;
    conn con = new conn();
    Signup2(String f_no){
        super("NEW ACCOUNT APPLICATION FORM");
        this.get_formno = f_no;
        form_no = new JLabel("Form no:");
        form_no.setFont(new Font("Raleway",Font.BOLD,12));

        add_details = new JLabel("Page 2: Additional Details");
        add_details.setFont(new Font("Raleway",Font.BOLD,22));

        religion = new JLabel("Religion");
        religion.setFont(new Font("Raleway",Font.BOLD,18));

        category = new JLabel("Category");
        category.setFont(new Font("Raleway",Font.BOLD,18));

        income = new JLabel("Income:");
        income.setFont(new Font("Raleway",Font.BOLD,18));

        edu1 = new JLabel("Educational");
        edu1.setFont(new Font("Raleway",Font.BOLD,18));
        edu2 = new JLabel("Qualification: ");
        edu2.setFont(new Font("Raleway",Font.BOLD,15));

        occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway",Font.BOLD,18));

        pan_no = new JLabel("Pan Card:");
        pan_no.setFont(new Font("Raleway",Font.BOLD,18));

        aadhar_no = new JLabel("Aadhar");
        aadhar_no.setFont(new Font("Raleway",Font.BOLD,18));

        senior_citizen = new JLabel("Senior Citizen");
        senior_citizen.setFont(new Font("Raleway",Font.BOLD,18));

        existing_acc = new JLabel("Existing Account");
        existing_acc.setFont(new Font("Raleway",Font.BOLD,18));

        String[] religion_arr = {"Hindu","Muslim","Sikh","Christan","Buddhist"};
        religion_box = new JComboBox(religion_arr);
        religion_box.setBackground(Color.white);
        religion_box.setFont(new Font("Raleway",Font.BOLD,14));

        String[] category_arr = {"General","OBC","SC","ST","Other"};
        category_box = new JComboBox(category_arr);
        category_box.setBackground(Color.white);
        category_box.setFont(new Font("Raleway",Font.BOLD,14));

        String[] income_arr = {"Null","<100000","<300000","<500000","Above 1000000"};
        income_box = new JComboBox(income_arr);
        income_box.setBackground(Color.white);
        income_box.setFont(new Font("Raleway",Font.BOLD,14));

        String[] education_arr = {"Non-Graduate","Graduate","Post-Graduate","Doctorate"};
        edu_box = new JComboBox(education_arr);
        edu_box.setBackground(Color.white);
        edu_box.setFont(new Font("Raleway",Font.BOLD,14));

        String[] occupation_arr =  {"Salaried","Self-Employed","Business","Student","Retired","Others"};
        occupation_box = new JComboBox(occupation_arr);
        occupation_box.setBackground(Color.white);
        occupation_box.setFont(new Font("Raleway",Font.BOLD,14));

        senior_y = new JRadioButton("Yes");
        senior_y.setFont(new Font("Raleway",Font.BOLD,14));
        senior_y.setBackground(Color.white);
        senior_n = new JRadioButton("No");
        senior_n.setFont(new Font("Raleway",Font.BOLD,14));
        senior_n.setBackground(Color.white);
        ButtonGroup senior_radio = new ButtonGroup();
        senior_radio.add(senior_y);
        senior_radio.add(senior_n);

        existing_acc_y = new JRadioButton("Yes");
        existing_acc_y.setFont(new Font("Raleway",Font.BOLD,14));
        existing_acc_y.setBackground(Color.white);
        existing_acc_n = new JRadioButton("No");
        existing_acc_n.setFont(new Font("Raleway",Font.BOLD,14));
        existing_acc_n.setBackground(Color.white);
        ButtonGroup existing_acc_radio = new ButtonGroup();
        existing_acc_radio.add(existing_acc_y);
        existing_acc_radio.add(existing_acc_n);

        next = new JButton("Next");
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBackground(Color.black);
        next.setForeground(Color.white);

        pan_filed = new JTextField();
        pan_filed.setFont(new Font("Raleway",Font.BOLD,14));

        aadhar_field = new JTextField();
        aadhar_field.setFont(new Font("Raleway",Font.BOLD,14));

        form_field = new JTextField();
        form_field.setFont(new Font("Raleway",Font.BOLD,13));

        setLayout(null);

        form_no.setBounds(700,10,60,30);
        add(form_no);

        form_field.setBounds(760,10,60,30);
        add(form_field);

        add_details.setBounds(280,30,600,40);
        add(add_details);

        religion.setBounds(100,120,100,30);
        add(religion);

        religion_box.setBounds(350,120,320,30);
        add(religion_box);

        category.setBounds(100,170,100,30);
        add(category);

        category_box.setBounds(350,170,320,30);
        add(category_box);

        income.setBounds(100,220,100,30);
        add(income);

        income_box.setBounds(350,220,320,30);
        add(income_box);

        edu1.setBounds(100,270,150,30);
        add(edu1);

        edu_box.setBounds(350,270,320,30);
        add(edu_box);

        edu2.setBounds(100,290,150,30);
        add(edu2);

        occupation.setBounds(100,340,150,30);
        add(occupation);

        occupation_box.setBounds(350,340,320,30);
        add(occupation_box);

        pan_no.setBounds(100,390,150,30);
        add(pan_no);

        pan_filed.setBounds(350,390,320,30);
        add(pan_filed);

        aadhar_no.setBounds (100,440,180,30);
        add(aadhar_no);

        aadhar_field.setBounds(350,440,320,30);
        add(aadhar_field);

        senior_citizen.setBounds(100,490,150,30);
        add(senior_citizen);

        senior_y.setBounds(350,490,100,30);
        add(senior_y);

        senior_n.setBounds(460,490,100,30);
        add(senior_n);

        existing_acc.setBounds(100,540,180,30);
        add(existing_acc);

        existing_acc_y.setBounds(350,540,100,30);
        add(existing_acc_y);

        existing_acc_n.setBounds(460,540,100,30);
        add(existing_acc_n);

        next.setBounds(570,600,100,30);
        add(next);

        next.addActionListener(this);

        getContentPane().setBackground(Color.white);
        setSize(850,850);
        setLocation(500,90);
        setVisible(true);
    }

    public boolean checkDetails(){
        if (pan_filed.getText().equals("")){
            return false;
        }
        if (aadhar_field.getText().equals("")){
            return false;
        }
        if (form_field.getText().equals("")){
            return false;
        }
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String religion_s = ((String) religion_box.getSelectedItem()).toUpperCase();
        String category_s = ((String) category_box.getSelectedItem()).toUpperCase();
        String income_s = ((String) income_box.getSelectedItem()).toUpperCase();
        String education_s = ((String) edu_box.getSelectedItem()).toUpperCase();
        String occupation_s = ((String) occupation_box.getSelectedItem()).toUpperCase();
        String pan_no_s = pan_filed.getText().toUpperCase();
        String aadhar_s = aadhar_field.getText().toUpperCase();
        String senior_citi_s = "";
        String exsisting_acc_s = "";

        if (senior_y.isSelected()){
            senior_citi_s="Yes";
        }
        else if(senior_n.isSelected()){
            senior_citi_s="No";
        }

        if (existing_acc_y.isSelected()){
            exsisting_acc_s="Yes";
        }
        else if (existing_acc_n.isSelected()){
            exsisting_acc_s="No";
        }

        try {
            if (form_field.getText().equals(get_formno)) {
                if (checkDetails() == false) {
                    JOptionPane.showMessageDialog(null, "Please Fill Aadhar and Pan  Number");
                } else {
                    String query = "insert into signup2 values('" + get_formno + "','" + religion_s + "','" + category_s + "','" + income_s + "','" + education_s + "','" + occupation_s + "','" + pan_no_s + "','" + aadhar_s + "','" + senior_citi_s + "','" + exsisting_acc_s + "')";
                    con.stmt.executeUpdate(query);
                    setVisible(false);
                    new Signup3(get_formno).setVisible(true);
                    System.out.println("Signup2 query executed successfully");
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect form number");
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        new Signup2("1055");
//    }
}
