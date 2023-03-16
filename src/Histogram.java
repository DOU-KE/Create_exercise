
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;
/*
   柱状图类用于产生柱状图
*
*/
public class Histogram extends JFrame {
    public double Accuracy;
    //绘制柱形统计图
    public String tittle;
    public Histogram(double accuracy, String tittle){
        super();
        this.Accuracy=accuracy;
        this.tittle=tittle;
        setTitle(this.tittle);
        setBounds(100, 100, 600, 432);
        setResizable(false);
        //setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
   //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭所有窗口结束程序
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//关闭当前窗口
    }

    public double getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(double accuracy) {
        Accuracy = accuracy;
    }

    @Override
    public void paint(Graphics g){
        int Width = getWidth();
        int Height = getHeight();
        int leftMargin = 20;//柱形图左边界
        int topMargin = 50;//柱形图上边界
        Graphics2D g2 = (Graphics2D) g;
        int ruler = Height-topMargin-5;
        int rulerStep = ruler/10;//将当前的高度评分为10个单位
        g2.setColor(Color.WHITE);//绘制白色背景
        g2.fillRect(0, 0, Width, Height);//绘制矩形图
        g2.setColor(Color.LIGHT_GRAY);
        //g2.drawString("成绩分布柱形图",Width,topMargin);
        for(int i=0;i<=10;i++){//绘制灰色横线和百分比
            g2.drawString("    "+(100-10*i)+"%", 5, topMargin+rulerStep*i);//写下百分比
            g2.drawLine(5, topMargin+rulerStep*i, Width, topMargin+rulerStep*i);//绘制灰色横线
        }
        g2.setColor(Color.PINK);
       // for(int i=0;i<2;i++){//绘制柱形图
            int value = (int)((Height-topMargin)*getAccuracy()+10.5);//设置柱形百分比
            int step = 40;//设置每隔柱形图的水平间隔为40
            //绘制矩形
//			g2.drawRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);
            g2.fillRoundRect(leftMargin+step*4, Height-value, 40, value, 40, 10);
            //列出产品的编号
            g2.drawString("正确率"+(getAccuracy()*100+"%"), leftMargin+step*4, Height-value-5);

            value=(int)((Height-topMargin)*(1-getAccuracy())+6.5);
            step=80;
            g2.fillRoundRect(leftMargin+step*4, Height-value, 40, value, 40, 10);
            g2.drawString("错误率"+((1-getAccuracy())*100+"%"), leftMargin+step*4, Height-value-5);

       // }
    }



}
