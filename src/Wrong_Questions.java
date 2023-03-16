import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

/*查看错题类，用于实现用户查看错题，包括全部错题或者指定日期的错题*/
public class Wrong_Questions extends JPanel implements ActionListener {
    public String user;
    public JTextArea JT;
    public JPanel p1,p2,p3,p4;
    public JLabel l1,l2,l3;
    public JButton  b1,b2,b3;
    public JTextField t1;
    public JScrollPane JS;
    public String L1_str;
    public Wrong_Questions(String user){
        this.user=user;
        setLayout(new BorderLayout());
        setSize(990,600);
        //上方功能面板
        p2=new JPanel(new FlowLayout(1,10,10));
        p2.setSize(990,50);
        b1=new JButton("查看所有");
        b2=new JButton("搜索查询");
        b3=new JButton("搜索");
        t1=new JTextField(15);
        t1.setEnabled(false);
        b3.setEnabled(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        p2.add(t1);
        p2.add(b3);
        p2.add(b2);
        p2.add(b1);
        //中间错题显示区域面板
        p3=new JPanel();
        p3.setSize(990,500);
        JT=new JTextArea(35,55);
        //JT.setEnabled(false);
        //JT.setOpaque(false);
       // JT.setSize(980,480);
        JS=new JScrollPane(JT);
        JS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       // JS.setSize(990,500);
        p3.add(JS);

        //底部面板，用于给用用户提供操作信息；
        p4=new JPanel();
        p4.setSize(1000,50);
        l1=new JLabel();
        L1_str="搜索时只能通过输入日期来查询,且必须按照xxxx-xx-xx的格式输入，否则会报错";
        l1.setText(L1_str);
        l1.setFont(new Font("宋体",Font.PLAIN,22));
        l1.setForeground(new Color(255,215,0));
        l1.setOpaque(true);
        l1.setBackground(new Color(238,130,238));
        p4.add(l1);
        //主面板，用于放p2和p3和p4;
        p1=new JPanel();
        p1.setSize(1000,600);
        p1.setLayout(new BorderLayout());



        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);
        b3.setContentAreaFilled(false);
        JS.setOpaque(false);
        JS.getViewport().setOpaque(false);
        JT.setOpaque(false);
       // JT.setEnabled(true);
        t1.setOpaque(false);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
       // p5.setOpaque(false);
        this.setOpaque(false);

        p1.add(p2,BorderLayout.NORTH);
        p1.add(p3,BorderLayout.CENTER);
        p1.add(p4,BorderLayout.SOUTH);
        add(p1);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            JT.setText("");
            String data=t1.getText();
            String sql="select * from question where sut_id='"+this.user+"'";
            JDBC_Select(sql,data);

        }
        if(e.getSource()==b2){
            b3.setEnabled(true);
            t1.setEnabled(true);
        }
        if(e.getSource()==b3){
            JT.setText("");
            String sql="select * from question where sut_id='"+this.user+"'";
            JDBC_Select(sql,"");
        }
    }

    public void JDBC_Select(String sql,String data){
      JDBC J=new JDBC();
      int i=1;

     ResultSet r= J.Rs(sql);

        try {
            if(data.equals(""))
            while (r.next()){
                int fle=0;
                int fla=0;
                if(isNumeric2(r.getString("answer_1"))){
                    if(Double.parseDouble(r.getString("answer_1"))!=Double.parseDouble(r.getString("answer_2"))){
                        if(data.equals(""))
                        fle=1;
                        else{
                           if(r.getString("time").substring(0,10).equals(data)){
                               fle=1;
                           }
                        }
                    }

            }
                else{
                  if(data.equals("")){
                      fle=1;
                  }
                  else{
                      if(r.getString("time").substring(0,10).equals(data)){
                          fle=1;
                      }
                  }
                }
                if(fle==1){
                    String str="    "+i+".   "+r.getString("formula")+r.getString("answer_1")+"       正确答案："+r.getString("answer_2")+"        ------答题时间："+r.getString("time");
                    JT.append(str);
                    JT.append("\n");
                    i++;
                    fle=0;
                }
            }

        J.col();
            r.close();
        }catch (SQLException e) {
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
