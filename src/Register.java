import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
public class Register extends JFrame{

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtName;
    private JTextField txtEmail;


    public static void main(String[] args) {
        /*JFrame frame = new JFrame("56160243");
        frame.setSize(250,250);
        frame.setLocation(300,300);
        frame.setVisible(true);*/
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Register form = new Register();
                form.setVisible(true);
            }
        });

    }
    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 454, 343);
        setTitle("ThaiCreate.Com Java GUI Tutorial");
        getContentPane().setLayout(null);

        // Header Title
        JLabel hRegister = new JLabel("Register Data");
        hRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
        hRegister.setHorizontalAlignment(SwingConstants.CENTER);
        hRegister.setBounds(121, 11, 132, 20);
        getContentPane().add(hRegister);

        // *** Header ***//
        JLabel hUsername = new JLabel("Username :");
        hUsername.setBounds(78, 52, 89, 14);
        getContentPane().add(hUsername);

        JLabel hPassword = new JLabel("Password :");
        hPassword.setBounds(78, 84, 89, 14);
        getContentPane().add(hPassword);

        JLabel hConfirmPassword = new JLabel("Confirm Password :");
        hConfirmPassword.setBounds(77, 113, 130, 14);
        getContentPane().add(hConfirmPassword);

        JLabel hName = new JLabel("Name :");
        hName.setBounds(78, 148, 89, 14);
        getContentPane().add(hName);

        JLabel hEmail = new JLabel("Email :");
        hEmail.setBounds(80, 176, 89, 14);
        getContentPane().add(hEmail);


        // CustomerID
        txtUsername = new JTextField("");
        txtUsername.setBounds(217, 47, 99, 20);
        getContentPane().add(txtUsername);

        // Password
        txtPassword = new JPasswordField();
        txtPassword.setBounds(217, 77, 102, 20);
        getContentPane().add(txtPassword);

        // Confirm Password
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(217, 112, 102, 20);
        getContentPane().add(txtConfirmPassword);

        // Name
        txtName = new JTextField("");
        txtName.setBounds(217, 140, 176, 20);
        getContentPane().add(txtName);

        // Email
        txtEmail = new JTextField("");
        txtEmail.setBounds(217, 172, 176, 20);
        getContentPane().add(txtEmail);

        // Save Button
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(RegisterData()) {
                    JOptionPane.showMessageDialog(null,
                            "Register Data Successfully");
                }
            }
        });
        btnSave.setBounds(161, 227, 89, 23);
        getContentPane().add(btnSave);
    }
    private Boolean RegisterData()
    {

        String strUsername = txtUsername.getText();
        String strPassword = new String(txtPassword.getPassword());
        String strConfirmPassword = new String(txtConfirmPassword.getPassword());
        String strName = txtName.getText();
        String strEmail = txtEmail.getText();

        if(strUsername.equals("")) // Username
        {
            JOptionPane.showMessageDialog(null,
                    "Please Input (Username)");
            txtUsername.requestFocusInWindow();
            return false;
        }
        if(strPassword.equals("")) // Password
        {
            JOptionPane.showMessageDialog(null,
                    "Please Input (Password)");
            txtPassword.requestFocusInWindow();
            return false;
        }

        if(strConfirmPassword.equals("")) // Confirm Password
        {
            JOptionPane.showMessageDialog(null,
                    "Please Input (Confirm Password)");
            txtConfirmPassword.requestFocusInWindow();
            return false;
        }
        if(!strPassword.equals(strConfirmPassword)) // Password math
        {
            JOptionPane.showMessageDialog(null,
                    "Please Input (Password Not Match!)");
            txtPassword.requestFocusInWindow();
            return false;
        }
        if(strName.equals("")) // Name
        {
            JOptionPane.showMessageDialog(null,
                    "Please Input (Name)");
            txtName.requestFocusInWindow();
            return false;
        }

        if(strEmail.equals("")) // Email
        {
            JOptionPane.showMessageDialog(null,
                    "Please Input (Email)");
            txtEmail.requestFocusInWindow();
            return false;
        }

        Connection connect = null;
        Statement s = null;
        Boolean status = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(""
                    + "jdbc:mysql://angsila.cs.buu.ac.th"
                    + "?user=cs57660121&password=00700700");

            s = connect.createStatement();

            // SQL Insert
            String sql = "INSERT INTO USER "
                    + "(Username,Password,Email,Name) "
                    + "VALUES ('" + strUsername + "','"
                    + strPassword + "','"
                    + strEmail + "'" + ",'"
                    + strName + "') ";
            s.execute(sql);


            // Reset Text Fields
            txtUsername.setText("");
            txtPassword.setText("");
            txtConfirmPassword.setText("");
            txtName.setText("");
            txtEmail.setText("");

            status  = true;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        try {
            if (s != null) {
                s.close();
                connect.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return status;

    }
}
