import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/*定时实现更新我要做题中作题倒计时的类*/
public class timer extends Thread {
    public JLabel l1;

    public  timer() {
    l1=new JLabel();
    }

    public JLabel getL1() {
        return l1;
    }

    public void setL1(JLabel l1) {
        this.l1 = l1;
    }

    public void run(){
    int time = 60*60;
    while (time > 0) {
        time--;
    try {
    Thread.sleep(1000);
    int hh = time / 60 / 60 % 60;
    int mm = time / 60 % 60;
    int ss = time % 60;
    String str= "还剩" + hh + "小时" + mm + "分钟" + ss + "秒"+"将自动交卷";
        System.out.println(str);
        getL1().setText(str);
        if(time==0)
            getL1().setText("");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}




}







