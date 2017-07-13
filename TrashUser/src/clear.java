import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.SynchronousQueue;


public class clear {
   public static void main (String args[]) throws ClassNotFoundException, SQLException{
       clear clearAcc = new clear();
       clearAcc.clearData("sit","2a5c62ab-336c-42e9-a91b-16ad288e9822");
       System.out.println();
       System.out.println("Data Cleared");
   }
    public void clearData(String env, String userId) throws SQLException, ClassNotFoundException {
        
        Connection connection = null;

          CallableStatement callableStatement = null;

          try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           if (env.equalsIgnoreCase("preprod")) {
            connection = DriverManager.getConnection(
              "jdbc:oracle:thin:@pp-usm-dbserver-shard-00.pp-jiocloud.com:2512:tjscpp00", "tejappusr",
              "tejappusr");
           } else if (env.equalsIgnoreCase("sit")) {
            connection = DriverManager.getConnection(
              "jdbc:oracle:thin:@usm-dbserver-shard.sit-jiocloud.com:1521:tejsecdbsit", "tejappusr",
              "tejappusr");
           } // tejrousr
           String plsql = "{CALL tejaqusr.sp_trash_user(?,?,?,?,?,?) }";
           callableStatement = connection.prepareCall(plsql);
           callableStatement.setInt(1, 0);
           callableStatement.setString(2, userId);
           callableStatement.setString(3, null);
           callableStatement.setString(4, "T");
           callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
           callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
           callableStatement.execute();
           System.out.println("Result of Execution :: " + callableStatement.execute());
           callableStatement.close();
           connection.close();
          } catch (Exception e) {
           System.out.println(e.getMessage());
          }
    }
}