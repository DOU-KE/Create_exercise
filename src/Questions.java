import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.State.TERMINATED;
/*我要做题的界面，主要实现随机产生50道加减乘除算式，定时做题，自动批改错题，查看本次考试的错题以及答案*/
public class Questions extends JPanel implements ActionListener {
    final CyclicBarrier barrier=new CyclicBarrier(1);
    JButton b1,b2,b3,b4;
    JPanel p1,p2,p3,p4,p5;
    JLabel l1,l2;
    JPanel JP[]=new JPanel[50];
   // JTextField t1;
    JLabel l[]=new JLabel[50];
    JTextField t[]=new JTextField[50];
    String Formula[]=new String[50];
    String answer[]=new String[50];
    String answer1[]= new String[50];
    String user;
    public Questions(String user){

        this.user=user;
       setLayout(new BorderLayout());
       setSize(990,590);
        b1=new JButton("生成试题");
        b2=new JButton("开始做题");
        b3=new JButton("提交试题");
        b4=new JButton("查看答案");
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        p1=new JPanel();
        p1.setSize(1000,500);
        p1.setLayout(new GridLayout(10,10,15,10));
        p2=new JPanel();
        p2.setSize(1000,50);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p3=new JPanel();
        p3.setSize(1000,100);
        p3.setLayout(new BorderLayout());
        p4=new JPanel();
        p4.setSize(1000,50);
        p5=new JPanel();
        p5.setSize(1000,50);
        l1=new JLabel();
        String str="注意事项：\n   本次试题作答时间为60分钟，倒计时结束后将自动提交试题。做题速度快的同学可以提前提交试卷。\n  除法运算要四舍五入保留两位小数";
        l1.setText(str);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        p5.add(l1);
        p3.add(p5,BorderLayout.SOUTH);


        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p5.setOpaque(false);
        this.setOpaque(false);
        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);
        b3.setContentAreaFilled(false);
        b4.setContentAreaFilled(false);

        add(p2,BorderLayout.BEFORE_FIRST_LINE);
        add(p1,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
       setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            p1.setVisible(false);
            b2.setEnabled(true);
            b3.setEnabled(false);
            b4.setEnabled(false);
            p1.removeAll();

            for (int i = 0; i < 50; i++) {
                Equation E=new Equation();
                String str=E.a_s_m_d();
                Formula[i]=E.getStr();
                answer[i]=E.getAnswer();
                JP[i]=new JPanel();
                JP[i].setSize(110,50);
                JP[i].setOpaque(false);
                l[i]=new JLabel(str);
                t[i]=new JTextField(4);
                t[i].setEnabled(false);
                t[i].setOpaque(false);
                JP[i].add(l[i]);
                JP[i].add(t[i]);
                p1.add(JP[i]);
            }
            p1.setVisible(true);
            }

        else if(e.getSource()==b2){
            for (int i = 0; i < 50; i++) {
                t[i].setEnabled(true);
            }
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(true);
            b4.setEnabled(false);
            p3.setVisible(false);
            timer t1=new timer();

            p4.add(t1.getL1());
            p3.add(p4,BorderLayout.NORTH);
            p3.setVisible(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    t1.start();
                    try {
                        Thread.sleep(1000*60*60);
                        System.out.println("线程结束了");
                        t1.l1.setText("考试结束！");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    submit1(answer1);
                    JDBC_inster();
                    b1.setEnabled(true);
                    b2.setEnabled(false);
                    b3.setEnabled(false);
                    b4.setEnabled(true);
                }
            }).start();
            System.out.println(t1.getState());



        }
        else if(e.getSource()==b3){
            submit(answer1);


        }

        else if(e.getSource()==b4){
            for (int i = 0; i <50 ; i++) {
               /* if(isNumeric2(answer1[i])){
                    if(Double.parseDouble(answer1[i])!=Double.parseDouble(answer[i])){
                        l[i].setText(Formula[i]+answer[i]);
                    }
                }
                else{
                    l[i].setText(Formula[i]+answer[i]);
                }*/
                l[i].setText(Formula[i]+answer[i]);
                t[i].setText("我的:"+answer1[i]);
            }
        }
    }


//主动提交试题，需满足不能空题的条件
    public void submit(String answer1[]){
        double fls=0;
        double f=0;
        int ff=0;
        for (int i = 0; i < 50; i++) {
            if(t[i].getText().equals("")){
                f=1;
                JOptionPane.showMessageDialog(this, "请作答完毕后再提交试卷！！", "系统提示", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
        if(f==0){
            ff=1;
        }
        if(ff==1){

            for (int i = 0; i < 50; i++) {
                answer1[i]=t[i].getText();
                        //Double.parseDouble(t[i].getText());
                if(Double.parseDouble(answer1[i])!=Double.parseDouble(answer[i])){
                    l[i].setOpaque(true);
                    l[i].setBackground(Color.red);
                    fls++;
                }
                   }
            String str = String.format("%.2f", (50-fls)/50.0);
            JOptionPane.showMessageDialog(this, "本次测试正确率为："+str, "系统提示", JOptionPane.ERROR_MESSAGE);
            b1.setEnabled(true);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(true);
            JDBC_inster();
        }

    }

    public void submit1(String answer1[]){
        double fls=0;
        for (int i = 0; i < 50; i++) {
            if(t[i].getText().equals("")){
                answer1[i]="null";
                l[i].setOpaque(true);
                l[i].setBackground(Color.red);
                fls++;
            }
            else{
            answer1[i]=t[i].getText();
                    //Double.parseDouble(t[i].getText());
            if(Double.parseDouble(answer1[i])!=Double.parseDouble(answer[i])){
                l[i].setOpaque(true);
                l[i].setBackground(Color.red);
                fls++;
            }}
        }
        String str = String.format("%.2f", (50-fls)/50.0);

        JOptionPane.showMessageDialog(p1, "本次测试正确率为："+str, "系统提示", JOptionPane.ERROR_MESSAGE);
    }

    public void JDBC_inster(){
        Connection con1;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/examination?serverTimezone=GMT%2B8&useSSL=false", "root", "admin");
            stmt = con1.createStatement();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (int i = 0; i < 50; i++) {
                String sql="insert into question(formula,sut_id,time,answer_1,answer_2) values('"+Formula[i]+"','"+this.user+"','"+formatter.format(c.getTime())+"','"+answer1[i]+"','"+answer[i]+"')";
                stmt.executeUpdate(sql);
            }
        } catch (ClassNotFoundException f) {
        System.out.println("SQLException:" + f.getLocalizedMessage());
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric2(String str) {
        try {
            if (str.startsWith(".") || str.endsWith(".")){
                return false;
            }
            new BigDecimal(str);
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }

}
