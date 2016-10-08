/*Author: Zhechen DU
 Created: Oct 8, 2016
 this file is used for accessing h2 ram currency pool
 exchange data will be kept in here
 two table are used: Currency_pool and Currency_loc
 currency_loc is used to track each currency's location in currency pool
 curreny_poll is used to store user trade request, 
 each currency will have two list, one for current list, the other is old.
 The rest of the data will be stored in a back up database for sort and etc.
 */ 
import java.sql.*;
import org.h2.jdbcx.JdbcConnectionPool;


public class H2currenyPool {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:pool;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pass";// this need to be stored somewhere
    //trade id between three currencies
   

	public static void initialize() throws Exception {
    	int [] trade_id ={12,13, 21, 23, 31, 32};
        try {
            createpoolid(trade_id);
            createpool();
            //insertWithStatement();

            //insertWithPreparedStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	private static void createpool() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = getPoolConnection();
		Statement stmt = connection.createStatement();
		stmt.execute("CREATE TABLE Currency_pool(id int auto_increment primary key, trade_id int, pool_loc int,"
        		+ "pool_preloc int)" );
		
	}
	private static Connection getPoolConnection() throws SQLException {
		// TODO Auto-generated method stub
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            
        }

        JdbcConnectionPool dbConnection = JdbcConnectionPool .create(DB_CONNECTION, DB_USER, DB_PASSWORD);
        return dbConnection.getConnection();

	}
	private static void createpoolid(int [] trade_id) throws SQLException
	{
		//creating table
		Connection connection = getPoolConnection();
		Statement stmt = connection.createStatement();
		stmt.execute("CREATE TABLE Currency_loc(id int auto_increment primary key, trade_id int, pool_loc int,"
        		+ "pool_preloc int)" );
		for (int i=0; i<trade_id.length; i++)
		{

            stmt.execute("INSERT INTO Currency_loc( trade_id,pool_loc,pool_preloc)VALUES( "+trade_id[i]+",0,0)");            

		}
		ResultSet rs = stmt.executeQuery("select * from Currency_loc");
        System.out.println("H2 In-Memory Database inserted through Statement");
        while (rs.next()) {
            System.out.println("Id " + rs.getInt("id") + " trade_id " + rs.getInt("trade_id")
            +" pool_loc " + rs.getInt("pool_loc")+" pool_preloc " + rs.getInt("pool_preloc"));
        }
        stmt.close();
		connection.close();
	}

	private static void insertWithStatement() throws SQLException {
        Connection connection = getPoolConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
/*            stmt.execute("CREATE TABLE Currency_loc(id int auto_increment primary key, trade_id int, pool_loc int,"
        		+ "pool_preloc int)" );*/
            stmt.execute("INSERT INTO Currency_loc( trade_id,pool_loc,pool_preloc) VALUES(11,1,1000)");
            stmt.execute("INSERT INTO Currency_loc( trade_id,pool_loc,pool_preloc) VALUES(12,2000,3000)");
            stmt.execute("INSERT INTO Currency_loc( trade_id,pool_loc,pool_preloc) VALUES(11,3000,4000)");

/*            ResultSet rs = stmt.executeQuery("select * from Currency_loc");
            System.out.println("H2 In-Memory Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " trade_id " + rs.getInt("trade_id")
                +" pool_loc " + rs.getInt("pool_loc")+" pool_preloc " + rs.getInt("pool_preloc"));
            }*/

            //stmt.execute("DROP TABLE Currency_loc");
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
		// TODO Auto-generated method stub
		
	}

//	private static void insertWithPreparedStatement() throws SQLException {
//        Connection connection = getDBConnection();
//        PreparedStatement createPreparedStatement = null;
//        PreparedStatement insertPreparedStatement = null;
//        PreparedStatement selectPreparedStatement = null;
//
////        String CreateQuery = "CREATE TABLE Currency_loc(id int primary key, trade_id int, pool_loc int,"
////        		+ "pool_preloc int)" ;
//        String InsertQuery = "INSERT INTO Currency_loc" + "(id, trade_id,pool_loc,pool_preloc) values" + "(?,?,?,?)";
//        String SelectQuery = "select * from Currency_loc";
//
//        try {
//            connection.setAutoCommit(false);
//
///*            createPreparedStatement = connection.prepareStatement(CreateQuery);
//            createPreparedStatement.executeUpdate();
//            createPreparedStatement.close();*/
//
//            insertPreparedStatement = connection.prepareStatement(InsertQuery);
//            insertPreparedStatement.setInt(1, 1);
//            insertPreparedStatement.setInt(2, 1);
//            insertPreparedStatement.setInt(3, 1);
//            insertPreparedStatement.setInt(4, 1);
//            insertPreparedStatement.executeUpdate();
//            insertPreparedStatement.close();
//
//            selectPreparedStatement = connection.prepareStatement(SelectQuery);
//            ResultSet rs = selectPreparedStatement.executeQuery();
//            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
//            while (rs.next()) {
//                System.out.println("Id " + rs.getInt("id") + " trade_id " + rs.getInt("trade_id")
//                +" pool_loc " + rs.getInt("pool_loc")+" pool_preloc " + rs.getInt("pool_preloc"));
//            }
//            selectPreparedStatement.close();
//
//            connection.commit();
//        } catch (SQLException e) {
//            System.out.println("Exception Message " + e.getLocalizedMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
//		// TODO Auto-generated method stub
//		
//	}




}
