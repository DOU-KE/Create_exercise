import java.sql.*;
/*数据库类用于实现数据库的连接，返回查询的结果集，关闭数据库等操作*/
public class JDBC {
    Connection con=null;
   Statement stmt=null;
    ResultSet rs=null;
    public JDBC(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException f) {
            System.out.println("SQLException:" + f.getLocalizedMessage());
        }

      try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examination?serverTimezone=GMT%2B8&useSSL=false", "root", "admin");
        stmt = con.createStatement();

    } catch (SQLException e) {
          e.printStackTrace();
      }

    }
    public static  ResultSet  Rs(String sql){
        JDBC j=new JDBC();
        try {
            j.rs=j.stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return j.rs;
    }
    public void modify(String sql){
        JDBC j=new JDBC();
        try {
            j.stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void col(){
        if(con==null){

        }
        else {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt==null){

        }
        else {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs==null){

        }
        else{
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
