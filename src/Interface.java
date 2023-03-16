import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/*主界面类，产生登录后用户的主界面，包括时钟界面，个人主页页面，我要做题界面，查看错题界面，成绩分析界面的大框架*/
public class Interface  implements ActionListener {
    public  String user;
    public JButton b1,b2,b3,b4,b5,b6;
    public JPanel p1,p2,p3,p4,p_background;
    public JLabel l1,l2,l3,l4;
    public JTextField t1,t2;
    public static JFrame J;
    public static Calendar c;
    public Interface(String user){
        this.user=user;

        J=new JFrame(user+"的主页");
        J.setTitle(user+"的主页");
        J.setSize(1000,800);
        J.setLocation(560, 50);
        J.setLayout(new BorderLayout());
        J.setDefaultCloseOperation(J.EXIT_ON_CLOSE);
        p1=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        p3.setLayout(new FlowLayout(1,0,0));
        p3.setSize(100,50);
        p4.setLayout(new FlowLayout(1,0,0));
        p4.setSize(100,50);
        p1.setSize(1000,100);
        p1.setLayout(new BorderLayout());
        p1.setBackground(Color.cyan);


      Image image= new ImageIcon("C:\\Users\\DOUKE\\IdeaProjects\\自动试题生成系统\\src\\img_2.png").getImage();
        p_background=new BackgroundPanel(image);
        p_background.setOpaque(false);
        p_background.setLayout(new BorderLayout());



        l1=new JLabel() ;



        p2=new JPanel();
        p2.setSize(1000,700);
        p2.setLayout(new FlowLayout());


        b1=new JButton("个人主页");
        b2=new JButton("我要做题");
        b3=new JButton("查看错题");
        b4=new JButton("成绩分析");
        Dimension preferredSize = new Dimension(245,50);
        b1.setPreferredSize(preferredSize );
        b2.setPreferredSize(preferredSize );
        b3.setPreferredSize(preferredSize );
        b4.setPreferredSize(preferredSize );
        p1.setOpaque(false);
        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);
        b3.setContentAreaFilled(false);
        b4.setContentAreaFilled(false);
        b1.setBorder(null);
        b2.setBorder(null);
        b3.setBorder(null);
        b4.setBorder(null);
        b1.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,23));
        b2.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,23));
        b3.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,23));
        b4.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,23));
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        p3.add(b1);
        p3.add(b2);
        p3.add(b3);
        p3.add(b4);
        p4.add(l1);
        p1.add(p4,BorderLayout.NORTH);
        p1.add(p3,BorderLayout.CENTER);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        J.add(p_background);
        p_background.add(p1,BorderLayout.BEFORE_FIRST_LINE);
        p_background.add(p2,BorderLayout.CENTER);
        J.setVisible(true);


        //头部时间表

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c = Calendar.getInstance();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    l1.setText("当前日期时间为："+formatter.format(c.getTime()));
                    l1.setFont(new Font("宋体",Font.PLAIN,24));
                    l1.setForeground(Color.red);
                   // l1.setOpaque(true);
                  //  l1.setBackground(Color.YELLOW);
                }
            }
        }).start();
       /* while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            l1.setText("当前日期时间为："+formatter.format(c.getTime()));
            l1.setFont(new Font("宋体",Font.PLAIN,24));
            l1.setForeground(Color.red);
            l1.setOpaque(true);
            l1.setBackground(Color.YELLOW);
        }*/
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            b1.setForeground(Color.MAGENTA);
            b2.setForeground(Color.black);
            b3.setForeground(Color.black);
            b4.setForeground(Color.black);
            p2.setVisible(false);
            p2.removeAll();
            p2.add(new Personal_Homepage(this.user));
            p2.setVisible(true);
        }
        else if(e.getSource()==b2){
            b1.setForeground(Color.black);
            b2.setForeground(Color.MAGENTA);
            b3.setForeground(Color.black);
            b4.setForeground(Color.black);
            p2.setVisible(false);
            p2.removeAll();
            p2.setLayout(new FlowLayout());
            p2.add(new Questions(this.user));
            p2.setVisible(true);
        }
        else if(e.getSource()==b3){
            b1.setForeground(Color.black);
            b2.setForeground(Color.black);
            b3.setForeground(Color.MAGENTA);
            b4.setForeground(Color.black);
            p2.setVisible(false);
            p2.removeAll();
            p2.add(new Wrong_Questions(this.user));
            p2.setVisible(true);
        }
        else if(e.getSource()==b4){

            b1.setForeground(Color.black);
            b2.setForeground(Color.black);
            b3.setForeground(Color.black);
            b4.setForeground(Color.MAGENTA);
            p2.setVisible(false);
            p2.removeAll();
            p2.add(new Grade_distribution(this.user));
            p2.setVisible(true);
        }
    }

   /*public static void main(String[] args) {
        new Interface("sdjd");
    }*/
}
