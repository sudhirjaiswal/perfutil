 import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ClearDataQueryNew {
	public static void main(String[] args) {
		String env = "preprod";
		String userId = "4b0ebe7e-3e1a-4385-a057-e76d83905f40";   
		Connection connection = null;

		CallableStatement callableStatement = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (env.equalsIgnoreCase("preprod")) {
				connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@pp-usm-dbserver-shard-00.pp-jiocloud.com:2512:tjscpp00",
						"tejappusr", "tejappusr");
			} else if (env.equalsIgnoreCase("sit")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@usm-dbserver-shard.sit-jiocloud.com:1521:tejsecdbsit", "tejrousr",
						"tejrousr");
			} 
			String plsql = "{CALL tejaqusr.sp_trash_user( ? ) }";
			callableStatement = connection.prepareCall(plsql);
			callableStatement.setString(1, userId);
			callableStatement.execute();
			System.out.println("Result of Execution :: " + callableStatement.execute());
			callableStatement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
