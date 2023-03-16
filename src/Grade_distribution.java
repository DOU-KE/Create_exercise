import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/*成绩分析模块，用于产提供查询成绩所必须的日期数据，通过输入相关日期可实现，跳转相关日期成绩的柱状图成绩分析*/

public class Grade_distribution extends JPanel implements ActionListener {
    String user;
    public JPanel p1, p2, p3, p4, p3_1, p3_2, p3_3, p3_4;
    public JButton b1, b2, b3, b4;
    public JTextField t1;
    public JTextArea Jt;
    public JLabel p3_l1, p3_l2, p4_l;


    public Grade_distribution(String user) {
        this.user = user;
        setSize(1000, 700);
        p1 = new JPanel();
        p1.setSize(1000, 600);
        p1.setLayout(new BorderLayout());
        //头部按钮面板
        p2 = new JPanel();
        p2.setSize(1000, 50);
        t1 = new JTextField(15);
        b1 = new JButton("进行查找");
        b1.addActionListener(this);
        b2 = new JButton("搜索查找");
        b2.addActionListener(this);
        b3 = new JButton("查看所有");
        b3.addActionListener(this);
        t1.setEnabled(false);
        b1.setEnabled(false);
        p2.add(t1);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        //中间树状图和查看列表面板
        p3 = new JPanel();
        p3.setSize(1000, 600);

        //中间面板中搜索查找参考面板的标题面板
        p3_1 = new JPanel(new FlowLayout(1));
        p3_1.setSize(1000, 50);
        // p3_1.setBounds(0,0,1000,50);
        p3_l1 = new JLabel("搜索参考日期");
        p3_l1.setFont(new Font("宋体", Font.PLAIN, 24));
        p3_1.add(p3_l1);
        //中间面板中搜索查询参考面板
        p3_2 = new JPanel();
        p3_2.setSize(1000, 250);
        //p3_2.setBounds(0,50,1000,250);
        Jt = new JTextArea(24, 55);
        JScrollPane JS = new JScrollPane(Jt);
        JS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        p3_2.add(JS);
        //中间面板的柱形图面板的标题面板
        p3_3 = new JPanel(new FlowLayout(1));
        p3_3.setSize(1000, 50);
        //p3_3.setBounds(0,300,1000,50);
        p3_l2 = new JLabel("成绩分布柱形图");
        p3_l2.setFont(new Font("宋体", Font.PLAIN, 24));
        p3_3.add(p3_l2);


        //中间面板的柱形图面板
        p3_4 = new JPanel();
        p3_4.setSize(1000, 250);
        //p3_4.setBounds(0,350,1000,50);
        //  p3_4.add(new Demo10(JDBC(),"所有综合成绩的柱状图"));

        p3.add(p3_1);
        p3.add(p3_2);
        //p3.add(p3_3);
        // p3.add(p3_4);

        Jt_append();

        //底部面板用于提供信息
        p4 = new JPanel();
        p4.setSize(1000, 50);
        p4_l = new JLabel("欢迎使用");
        p4_l.setFont(new Font("宋体", Font.PLAIN, 24));
        p4.add(p4_l);


        p1.add(p2, BorderLayout.BEFORE_FIRST_LINE);
        // p1.add(p3_1,BorderLayout.CENTER);
        // p1.add(p3_2,BorderLayout.AFTER_LINE_ENDS);
        p1.add(p3_1, BorderLayout.CENTER);
        p1.add(p3_2, BorderLayout.SOUTH);

        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);
        b3.setContentAreaFilled(false);
        JS.setOpaque(false);
        JS.getViewport().setOpaque(false);
        Jt.setOpaque(false);
        t1.setOpaque(false);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p3_1.setOpaque(false);
        p3_2.setOpaque(false);
        p3_3.setOpaque(false);
        p3_4.setOpaque(false);
        // p5.setOpaque(false);
        this.setOpaque(false);


        add(p1);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new Histogram(JDBC(t1.getText()), t1.getText() + "成绩柱状图");
            t1.setEnabled(false);
            b1.setEnabled(false);

        } else if (e.getSource() == b2) {
            b1.setEnabled(true);
            t1.setEnabled(true);
            t1.setText("");
        } else if (e.getSource() == b3) {
            new Histogram(JDBC(), "所有试题综合正确率的柱状图");
            //  new Demo10(JDBC(t1.getText()),t1.getText()+"成绩柱状图");
        }
    }

    public double JDBC(String time) {
        JDBC J = new JDBC();
        String sql = "select * from question where sut_id='" + this.user + "'";
        double fle = 0.0;
        double d = 0.0;

        ResultSet r = J.Rs(sql);

        try {
            while (r.next()) {
                if (r.getString("time").equals(time) && (r.getString("answer_1").equals(r.getString("answer_2")))) {
                    fle++;
                }
            }
            String str = String.format("%.2f", fle / 50.0);
            d = Double.parseDouble(str);
            J.col();
            r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;

    }

    public double JDBC() {
        JDBC J = new JDBC();
        String sql = "select * from question where sut_id='" + this.user + "'";
        double fle = 0.0;
        double d = 0.0;
        ResultSet r = J.Rs(sql);
        try {
            r.last();
            int k = r.getRow();
            r.first();
            while (r.next()) {
                if ((r.getString("answer_1").equals(r.getString("answer_2")))) {
                    fle++;
                }
            }
            String str = String.format("%.2f", fle / (k + 0.1 - 0.1));
            d = Double.parseDouble(str);
            J.col();
            r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public void Jt_append() {
        JDBC J = new JDBC();
        String sql = "select * from question where sut_id='" + this.user + "'";
        String time1 = "2022-01-02 16:56:13";
        ResultSet r = J.Rs(sql);
        try {
            r.last();
            int k = r.getRow();
            r.first();
            String time[] = new String[k / 50];
            int i = 1;
            while (r.next()) {
                if (r.getString("time").equals(time1)) {

                } else {
                    time1 = r.getString("time");
                    Jt.append(i + "." + r.getString("time"));
                    Jt.append("\n");
                    i++;
                }
            }
            J.col();
            r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
