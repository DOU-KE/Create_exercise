import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/*登录、注册界面用于用户进行登录注册等操作*/

class Login extends JFrame implements ActionListener {
    private JButton b1,b2,b3,b4,b5;
    private JTextField t1;
    private JLabel l1, l2;
    public JPanel p_background;
    private JPasswordField JPW;
    private int massage = 0;

    public Login() {
        setTitle("自动试题生成");
        setSize(240, 200);
        setLocation(1400, 240);
       setLayout(new FlowLayout(1,7,18));

        setResizable(false);

        l1 = new JLabel("用户姓名");
        t1 = new JTextField(10);
        l2 = new JLabel("用户口令");
        JPW= new JPasswordField(10);
        b1 = new JButton("登录");
       // b1.setSize(67,30);
        b2 = new JButton("退出");
       // b2.setSize(67,30);
        b3=new JButton("注册");
        b4=new JButton("返回");
        b5=new JButton("注册");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Interface("df");
            }
        });
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b1.addActionListener(this);
        add(l1);
        add(t1);
        add(l2);
        add(JPW);
        add(b1);
        add(b2);
        add(b3);
        /*Image image= new ImageIcon("C:\\Users\\DOUKE\\IdeaProjects\\自动试题生成系统\\src\\img_2.png").getImage();
        p_background=new BackgroundPanel(image);
        p_background.setOpaque(false);
        p_background.setLayout(new FlowLayout(1,7,18));
        p_background.add(l1);
        p_background.add(t1);
        p_background.add(l2);
        p_background.add(JPW);
        p_background.add(b1);
        p_background.add(b2);
        p_background.add(b3);
        add(p_background);*/

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
       // new Interface("djvn");
       // Interface("edf");

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b2) {
           System.exit(0);
        }
        else if (e.getSource() == b1) {

                Connection con;
                Statement stmt;
                ResultSet rs;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException f) {
                    System.out.println("SQLException:" + f.getLocalizedMessage());
                }  try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examination?serverTimezone=GMT%2B8&useSSL=false", "root", "admin");
                stmt = con.createStatement();

                    rs = stmt.executeQuery("select * from student");
                    while (rs.next()) {
                        String str1 = rs.getString("user");
                        String str2 = rs.getString("password");
                        char[] ps = JPW.getPassword();
                        String str3 = "";
                        for (int i = 0; i < ps.length; i++)
                            str3 += ps[i];
                        if ((t1.getText().equals(str1)) && (str3.equals(str2))) {
                            massage = 1;
                            JOptionPane.showMessageDialog(this, "登陆成功！", "系统提示", JOptionPane.ERROR_MESSAGE);


                            break;
                        }
                    }


                    rs.close();
                    stmt.close();
                    con.close();
                } catch (SQLException f) {
                    System.out.println(f);
                }
            if(massage==0){
                JOptionPane.showMessageDialog(this, "你输入的账户或密码有误,请重新输入后登录！", "系统提示", JOptionPane.ERROR_MESSAGE);
            }
            else{
                this.setVisible(false);

                new Interface(t1.getText());

                this.setVisible(true);
                dispose();
            }
            }

         else if (e.getSource() == b3) {
                t1.setText("");
                JPW.setText("");
                this.setVisible(false);
                this.remove(b1);
                this.remove(b2);
                this.remove(b3);
                this.add(b5);
                this.add(b4);
                this.setVisible(true);
        }
         if(e.getSource()==b4){
             t1.setText("");
             JPW.setText("");
             this.setVisible(false);
             this.remove(b4);
             this.remove(b5);
             this.add(b1);
             this.add(b2);
             this.add(b3);
             this.setVisible(true);
         }
         if(e.getSource()==b5){


             Connection con1;
             Statement stmt;
             ResultSet rs;
             try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
             } catch (ClassNotFoundException f) {
                 System.out.println("SQLException:" + f.getLocalizedMessage());
             }
             try {
                 con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/examination?serverTimezone=GMT%2B8&useSSL=false", "root", "admin");
                 stmt = con1.createStatement();
                 rs = stmt.executeQuery("select * from student");
                 while (rs.next()) {
                     String str1 = rs.getString("user");
                     String str2 = rs.getString("password");
                     char[] ps = JPW.getPassword();
                     String str3 = "";
                     for (int i = 0; i < ps.length; i++)
                         str3 += ps[i];
                     if ((t1.getText().equals(str1)) && (str3.equals(str2))) {
                         massage = 1;
                         JOptionPane.showMessageDialog(this, "此账号已被注册！", "系统提示", JOptionPane.ERROR_MESSAGE);
                      //   new Interface(t1.getText());
                      //   this.setVisible(false);
                         break;
                     }
                 }
                 if (massage == 0) {
                     //  JOptionPane.showMessageDialog(this, "你输入的账户或密码有误，或登录身份选择错误，请重新输入或选择身份后登录！", "系统提示", JOptionPane.ERROR_MESSAGE);
                     char[] ps = JPW.getPassword();
                     String str3 = "";
                     for (int i = 0; i < ps.length; i++)
                         str3 += ps[i];
                     String sql="insert into student (user,password) values('"+t1.getText()+"','"+str3+"')";
                     stmt.executeUpdate(sql);
                     JOptionPane.showMessageDialog(this, "账号注册成功！！", "系统提示", JOptionPane.ERROR_MESSAGE);

                 }
                 con1.close();
                 rs.close();
                 stmt.close();
             } catch (SQLException f) {
                 System.out.println(f);
             }
             t1.setText("");
             JPW.setText("");
         }
         }


    public static void main(String args []){
        new Login();
    }
    public void Interface(String str){
        new Interface(str);
    }

}
