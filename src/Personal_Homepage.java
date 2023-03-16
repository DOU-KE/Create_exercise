import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
/*个人主页界面，主要有看自己当前所做所有题目的信息，以及修改密码，和导出指定日期试题的操作*/
public class Personal_Homepage extends JPanel implements ActionListener {
    String user;
    JPanel p1,p2,p3,p2_left,p2_right;
    JLabel l1,l2,l3,personal_information;
    JTextField t1,t2,t3;
    JButton b1,b2,b3,b4;
    JTextArea JT;
    JScrollPane JS;
    private JPasswordField password1,password2;
    int n;
  public Personal_Homepage(String user){

      this.user=user;
      setSize(1000,600);
      setLayout(new BorderLayout());
      //头部样式面板
      p1=new JPanel(new FlowLayout(1));
      p1.setSize(1000,50);
      personal_information=new JLabel("");
      JDBC_information();
      String P_I="尊敬的用户"+this.user+"你总计在本平台做"+getN()+"道题,正确率为:"+JDBC_information()*100+"%";
      personal_information.setText(P_I);
      personal_information.setFont(new Font("宋体",Font.PLAIN,24));
    //  personal_information.setForeground(Color.red);
     // personal_information.setOpaque(true);
     // personal_information.setBackground(Color.YELLOW);
      p1.add(personal_information);





      //中间修改密码面板
      p2=new JPanel(new BorderLayout());
      p2.setSize(1000,300);
      l1=new JLabel("当前密码");
     // l1.setLocation(100,100);
      l2=new JLabel("改后密码");
     // l2.setLocation(100,150);
      l3=new JLabel("确认密码");
     // l3.setLocation(100,200);
      t1=new JTextField(15);
      //t1.setLocation(150,100);
      password1=new JPasswordField(15);
      //password1.setLocation(150,150);
      password2=new JPasswordField(15);
     // password2.setLocation(150,200);
      t1.setEnabled(false);
      password2.setEnabled(false);
      password1.setEnabled(false);
      /*p2.add(l1);
      p2.add(t1);
      p2.add(l2);
      p2.add(password1);
      p2.add(l3);
      p2.add(password2);*/
      JT=new JTextArea(24,55);
      JS=new JScrollPane(JT);
      JS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      JS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      Jt_append();
     /* p2.add(JS);*/

      p2_left=new JPanel();
      p2_left.setLayout(new FlowLayout(1));

      p2_left.setSize(1000,50);
      p2_left.add(l1);
      p2_left.add(t1);
      p2_left.add(l2);
      p2_left.add(password1);
      p2_left.add(l3);
      p2_left.add(password2);
      //p2.add(p2_left);

      p2_right=new JPanel(new FlowLayout(1));
      p2_right.setSize(1000,240);
      p2_right.add(JS);

      p2.add(p2_right,BorderLayout.CENTER);
      p2.add(p2_left,BorderLayout.NORTH);

      //底部按钮触发面板

      p3=new JPanel(new FlowLayout(1));
      p3.setSize(500,100);
      t2=new JTextField(20);
      t2.setEnabled(false);
      b1=new JButton("修改密码");
      b2=new JButton("确认修改");
      b3=new JButton("导出试题");
      b4=new JButton("确认导出");
      b1.addActionListener(this);
      b2.addActionListener(this);
      b3.addActionListener(this);
      b4.addActionListener(this);
      b2.setEnabled(false);
      b4.setEnabled(false);
      p3.add(t2);
      p3.add(b1);
      p3.add(b2);
      p3.add(b3);
      p3.add(b4);

      b1.setContentAreaFilled(false);
      b2.setContentAreaFilled(false);
      b3.setContentAreaFilled(false);
      b4.setContentAreaFilled(false);

      b1.setOpaque(false);
      JS.setOpaque(false);
      JS.getViewport().setOpaque(false);
      JT.setOpaque(false);
      t1.setOpaque(false);
      t2.setOpaque(false);
      b2.setOpaque(false);
      b3.setOpaque(false);
      b4.setOpaque(false);
      password1.setOpaque(false);
      password2.setOpaque(false);
      p1.setOpaque(false);
      p2.setOpaque(false);
      p3.setOpaque(false);
      p2_right.setOpaque(false);
      p2_left.setOpaque(false);
      this.setOpaque(false);
     add(p1,BorderLayout.NORTH);
     add(p2,BorderLayout.CENTER);
     add(p3,BorderLayout.SOUTH);
     setVisible(true);
}

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            t1.setEnabled(true);
            password2.setEnabled(true);
            password1.setEnabled(true);
            b2.setEnabled(true);
        }
        else if(e.getSource()==b2){
           if(password_decode(password1).equals(password_decode(password2))){
               JDBC_Change_Password();
               System.out.println();
           }
           else{
               JOptionPane.showMessageDialog(this, "修改后的密码，两次输入的密码不一致，请检查后重新输入！", "系统提示", JOptionPane.ERROR_MESSAGE);
           }
           t1.setText("");
           password2.setText("");
           password1.setText("");
           t1.setEnabled(false);
           password1.setEnabled(false);
           password2.setEnabled(false);
           b2.setEnabled(false);

        }
        else if(e.getSource()==b3){
            t2.setEnabled(true);
            b4.setEnabled(true);
        }
        else if(e.getSource()==b4){

            JDBC_import(t2.getText());
            b4.setEnabled(false);
            t2.setEnabled(false);
            t2.setText("");
        }


    }


    //头部信息的数据库操作
    public double JDBC_information(){
        JDBC J=new JDBC();
        String sql="select * from question where sut_id='"+this.user+"'";
        double fle=0.0;
        double d=0.0;
        ResultSet r=J.Rs(sql);
        try {
            r.last();

            setN( r.getRow());
            r.first();
            while (r.next()){
                if((r.getString("answer_1").equals(r.getString("answer_2")))){
                    fle++;
                }
            }
            String str = String.format("%.2f",fle/(getN()+0.1-0.1));
            d=Double.parseDouble(str);
            J.col();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
    //中间部分获取当前用户所做过的所有的题，并写入文本框中
    public void Jt_append(){
        JDBC  J=new JDBC();
        String sql="select * from question where sut_id='"+this.user+"'";
        ResultSet r=J.Rs(sql);
        JT.setText("");
        try {

            int i=1;
            while (r.next()){
                    JT.append(i+". "+r.getString("formula")+" "+r.getString("answer_2")+"                                                 ---"+r.getString("time"));
                    JT.append("\n");
                    i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //密码文本框解码
    public String password_decode(JPasswordField password){

        char[] ps = password.getPassword();
        String str = "";
        for (int i = 0; i < ps.length; i++)
            str += ps[i];

      return str;
    }
    //修改密码的数据库操作
    public void JDBC_Change_Password()  {
      JDBC J=new JDBC();
      String sql="select * from student where user='"+this.user+"'";
      ResultSet r=J.Rs(sql);
        try {
           while (r.next()){
               if(r.getString("password").equals(t1.getText())){
                   String sql1="update student set password='" + password_decode(password1) + "'where user ='"+this.user+"'";
                   J.stmt.executeUpdate(sql1);
                   JOptionPane.showMessageDialog(this, "密码修改成功！", "系统提示", JOptionPane.ERROR_MESSAGE);
               }
               else{
                   JOptionPane.showMessageDialog(this, "输入的当前密码错误请检查后在输入！", "系统提示", JOptionPane.ERROR_MESSAGE);
               }
           }
            r.close();
           J.col();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //试题导入文档功能
    public  void JDBC_import(String time){

        String sql_test="select distinct time from question where sut_id='"+this.user+"'";
        String sql="select * from question where sut_id='"+this.user+"' and time='"+time+"'";

        int fle=0;
        try {
            JDBC J=new JDBC();
            ResultSet r1=J.Rs(sql_test);

            while (r1.next()){
                System.out.println(r1.getString("time"));
                if(r1.getString("time").equals(time)){
                    fle=1;

                    break;
                }

            }
            r1.close();
            J.col();
        }catch (SQLException e1) {
                e1.printStackTrace();
            }
            if(fle==1) {
                JDBC JJ=new JDBC();
                ResultSet r = JJ.Rs(sql);
                try {
                    FileDialog f = new FileDialog(Interface.J, "试题导入", 0);

                    f.setVisible(true);
                    String fileName = f.getDirectory() + f.getFile(); // 所选的文件
                    FileOutputStream fo = new FileOutputStream(fileName);
                    int i = 1;
                    while (r.next()) {
                        String s = i + ". " + r.getString("formula") + "\n";
                        byte[] b = s.getBytes();
                        fo.write(b);
                        i++;
                    }
                    JOptionPane.showMessageDialog(this, "用户"+this.user+"在"+time+"时间的试题已成功导入"+fileName+"中！", "系统提示", JOptionPane.ERROR_MESSAGE);
                    fo.close();
                    r.close();
                    JJ.col();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else{
                JOptionPane.showMessageDialog(this, "系统查询不到所输入时间的习题，请重新检查后，在输入正确时间！", "系统提示", JOptionPane.ERROR_MESSAGE);
            }


}

/*public static void main(String[] args) {
        JFrame J=new JFrame();
        J.setSize(1000,660);
        J.add(new Personal_Homepage("sdjd"));
        J.setVisible(true);
    }
    */

}
