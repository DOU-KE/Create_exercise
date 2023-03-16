import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.Global.undefined;
/**/
public class test {

   /* public static void main(String[] args) {
        JFrame J=new JFrame();
        J.setLayout(new FlowLayout(1));
        J.setSize(1000,500);
        JTextField t1=new JTextField(15);
        J.add(t1);
        J.setVisible(true);
        FileDialog f=new FileDialog(J,"实验",0);
        f.setVisible(true);
        String fileName = f.getDirectory() + f.getFile(); // 所选的文件

        try {

            FileInputStream src = new FileInputStream(fileName);
            FileOutputStream fo=new FileOutputStream(fileName);
          // JDBC(fo);

        } catch (IOException e) {

        }

       *//* Scanner in=new Scanner(System.in);

        String str=in.next();
        System.out.println(str.substring(0,10));
        String str[]=new String[10];
        for (int i = 0; i < 10; i++) {
            str[i]=in.next();
            System.out.println(isNumeric2(str[i]));

        }*//*
    }
    public static void JDBC(String time){
        JDBC J=new JDBC();
        String sql="select * from question where sut_id='sdjd' and time='"+time+"'";
        ResultSet r=J.Rs(sql);

        try {FileDialog f=new FileDialog(this,"试题导入",0);
            f.setVisible(true);
            String fileName = f.getDirectory() + f.getFile(); // 所选的文件
            FileOutputStream fo=new FileOutputStream(fileName);
            int i=1;
           while (r.next()){
               String s=i+". "+r.getString("formula")+"\n";
               byte []b=s.getBytes();
               fo.write(b);
           }
           fo.close();
           r.close();
           J.col();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
   /* public static boolean isNumeric2(String str) {
        try {
            if (str.startsWith(".") || str.endsWith(".")){
                return false;
            }
            new BigDecimal(str);
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }*/
}
