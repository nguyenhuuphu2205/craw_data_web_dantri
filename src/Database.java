import com.sun.org.apache.regexp.internal.RE;

import java.io.PrintWriter;
import java.sql.*;
/**
 * Created by trananh bacninh on 12-Mar-18.
 */
public class Database {
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tktdtt?useUnicode=yes&characterEncoding=UTF-8", "root", "12345678");
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
    public void insert(TinTuc tinTuc){
        Connection connection = this.getConnection();
        String sql = String.format("INSERT INTO tintuc (chude,url,title,content) values ('%s','%s','%s','%s')",tinTuc.getChuDe(),tinTuc.getUrl(),tinTuc.getTitle(),tinTuc.getContent());
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();

        }

    }
    //Xuất dữ liệu database ra file json
    public void writeDataToFileJson(){
        Connection connection = this.getConnection();
        try{
            String sql = "select * from tintuc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("doccument.json");
           // out.write("[");

            while (rs.next()){
                TinTuc tinTuc = new TinTuc();
                tinTuc.setId(rs.getString(1));
                tinTuc.setChuDe(rs.getString(2));
                tinTuc.setUrl(rs.getString(3));
                tinTuc.setTitle(rs.getString(4));
                tinTuc.setContent(rs.getString(5));
                System.out.println(tinTuc.toJson());
                out.write(tinTuc.toJson());
                out.write(",");
                out.write("\n");

            }
            //out.write("]");
            out.flush();
            out.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Hàm xuất dữ liệu trong database ra file xml
    public void writeDataToFileXML(){
        Connection connection = this.getConnection();
        try{
            String sql = "select * from tintuc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("doccument.xml");
             out.write("<add>\n");

            while (rs.next()){
                TinTuc tinTuc = new TinTuc();
                tinTuc.setId(rs.getString(1));
                tinTuc.setChuDe(rs.getString(2));
                tinTuc.setUrl(rs.getString(3));
                tinTuc.setTitle(rs.getString(4));
                tinTuc.setContent(rs.getString(5));
                System.out.println(tinTuc.toXML());
                out.write(tinTuc.toXML());
                out.write("\n");

            }
            out.write("</add>");
            out.flush();
            out.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Database db = new Database();
        db.writeDataToFileXML(); // xuất dữ liệu ra file xml



    }
}
//    public boolean insert(String chude,String url,String title)
//
//}
