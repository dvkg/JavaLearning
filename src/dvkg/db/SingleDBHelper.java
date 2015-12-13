package dvkg.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * @author dvking
 *
 */
public class SingleDBHelper {
	
	private static SingleDBHelper inst = new SingleDBHelper();
	
	private static Logger logger = null;
	
	private Map<String,DBConnector> connPool = new HashMap<String, DBConnector>();
	
	private Properties property = new Properties();
	
	private SingleDBHelper(){
		logger = LoggerFactory.getLogger(dvkg.db.SingleDBHelper.class);
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		String dbconfig = null;
		try {
			//dbconfig = SingleDBHelper.class.getClassLoader().getResource("").getPath() + "config/dbconfig.properties"; 
			dbconfig = new String("config/dbconfig.properties");
			property.load(new FileInputStream(dbconfig));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]:找不到配置文件:"
								+ dbconfig);
			e.printStackTrace();
		}
		
		Set keys = property.keySet();
		Iterator<Object> itr = keys.iterator();
		while(itr.hasNext()){
			String dbtype = (String)itr.next();
			/*DBConnector dbconn = new DBConnector(SingleDBHelper.class.getResource("/").getPath() 
												 + "config/" + (String)property.getProperty(dbtype));*/
			DBConnector dbconn = new DBConnector("config/" + (String)property.getProperty(dbtype));
			connPool.put(dbtype, dbconn);
		}
		
		/*this.mysql = new DBConnector(SingleDBHelper.class.getResourceAsStream((String)property.getProperty("mysql")));
		this.oracle = new DBConnector(SingleDBHelper.class.getResourceAsStream((String)property.getProperty("oracle")));
		this.sqlserver = new DBConnector(SingleDBHelper.class.getResourceAsStream((String)property.getProperty("sqlserver")));
		*/
	}
	
	public static SingleDBHelper getInstence(){
		return inst;
	}
	
	private DBConnector getDBConnector(String db){
		DBConnector conn = null;
		/*switch(db){
			case "mysql" : conn = this.mysql; break;
			case "oracle" : conn = this.oracle; break;
			case "sqlserver" : conn = this.sqlserver; break;
			default:break;
		}*/
		conn = connPool.get(db);
		return conn;
	}
	
	public void initConnection(String db){
		this.getDBConnector(db).initConnection();
	}
	
	public void close(String db){
		this.getDBConnector(db).close();
	}
	
	public ResultSet executeQuery(String db, String sqlcmd){
		ResultSet rs = null;
		
		rs = this.getDBConnector(db).executeQuery(sqlcmd);
		return rs;
	}
	
	public int executeUpdate(String db, String sqlcmd){
		
		return this.getDBConnector(db).executeUpdate(sqlcmd);
	}
	
	public static void main(String [] args){
		
		SingleDBHelper.getInstence().initConnection("mysql");
		ResultSet rs = SingleDBHelper.getInstence().executeQuery("mysql", "select * from user");
		try {
			while(rs.next()){
				System.out.println(rs.getString(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SingleDBHelper.getInstence().close("mysql");
	}
	
}
