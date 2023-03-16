
/*
算式类用于随机产生100以内的加减乘除法
 */

import java.text.DecimalFormat;
import java.util.Random;

public class Equation {
    String str=null;
    String answer;
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String add_sub(){
        double result=0.0;
        char cc;
        Random r=new Random();
        int a=r.nextInt(100);
        int b=r.nextInt(100);
        int c=r.nextInt(2);
        if(c==0){
           cc='+';

           result=a+b;
        }
        else{
            cc='-';
            while (a-b<0){
                a=r.nextInt(100);
                b=r.nextInt(100);
            }
        result=a-b;}
        setStr(String.valueOf(a)+cc+String.valueOf(b)+"=");
        System.out.print(getStr());
        setAnswer((result+""));
        System.out.println(result);
        return getStr();
    }
    public String multiplication_division(){
        char cc;
        double result=0.00;
        Random r=new Random();
        int a=r.nextInt(100);
       int b=r.nextInt(100);
        //除数不能为零，如果为零重新生成
        int c=r.nextInt(2);
        if(c==0){
            cc='*';
            double a1=a;
            double b1=b;
            result=a*b;
        }
        else{
            cc='/';
            double a1=a;
            double b1=b;
            while (b==0)
                b=r.nextInt(100);
            result=(a1/b1);
        }
        String str1=String.valueOf(a)+cc+String.valueOf(b)+"=";
        System.out.print(str1);
        String str = String.format("%.2f", result);
       // Double d=Double.parseDouble(str);

        System.out.println(str);
        setStr(str1);
        setAnswer(str);
        return str1;
    }

    //产生加减乘除算式
    public String a_s_m_d(){
        Random r=new Random();

        int a=r.nextInt(2);
        if(a==0)
            return add_sub();
        else
            return multiplication_division();
    }

   /* public static void main(String[] args) {
        Equation e=new Equation();
        e.add_sub();
        e.multiplication_division();
    }*/

}
