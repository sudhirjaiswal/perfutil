import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class TestClass {

	public static void main(String[] args) {
		String env = "sit";
		String userId = "4915bd7b-ff9c-44d2-bba0-2df352fe04ca";   
//		String userId = "2edb023c07b22589e05310bd15ac99b6";
//		String userId = "2e3b2d2145c15e60e05310bd15aced66";
//		String userId ="35f1a035d5b93a30e05310bd15acb8f6";
		Connection connection = null;

		CallableStatement callableStatement = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (env.equalsIgnoreCase("awsstage")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@52.76.121.234:1521:tej12cdbstg", "tejrousr",
						"tejrousr");
			} else if (env.equalsIgnoreCase("sit")) {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.135.85.97:1521:tej12cdbtest", "tejrousr",
						"tejrousr");
			} else if (env.equalsIgnoreCase("preprod")) {
				connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@ec2-52-77-37-107.ap-southeast-1.compute.amazonaws.com:1521:tej12cdbppod",
						"tejrousr", "J10dr1v3#6102");
			}
			String plsql = "{CALL tejusrdata.sp_del_user_data( ? , ? , ? ) }";
			callableStatement = connection.prepareCall(plsql);
			callableStatement.setString(1, userId);
			callableStatement.registerOutParameter(2, Types.NUMERIC);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.execute();
			System.out.println("Result of Execution :: " + callableStatement.execute());
			System.out.println("Result of Execution :: " + callableStatement.getObject(2));
			System.out.println("Result of Execution :: " + callableStatement.getObject(3));
			callableStatement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
