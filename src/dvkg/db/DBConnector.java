package dvkg.db;

import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author dvking
 *
 */
public class DBConnector {
	
	private Properties property = new Properties();
	private ResultSet rs = null;
	private Statement st = null;
	private Connection conn = null;
	
	public DBConnector(String confFile){
		InputStream in = null;
		try {
			 in = new FileInputStream(confFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]:找不到配置文件:"+confFile);
			e.printStackTrace();
		}
		
		if(in != null){
			try {
				property.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("[ERROR]:无法加载配置文件:"+in.toString());
				e.printStackTrace();
			}
		}
	}
	
	public DBConnector(InputStream in){
		
		if(in != null){
			try {
				property.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("[ERROR]:无法加载配置文件:"+in.toString());
				e.printStackTrace();
			}
		}
	}
	
	public void initConnection(){
		
		String dbclass = (String) this.property.getProperty("dbclass");
		String dburl = (String) this.property.getProperty("dburl");
		String dbuser = (String) this.property.getProperty("dbuser");
		String dbpasswd = (String) this.property.getProperty("dbpasswd");
		try {
			Class.forName(dbclass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]:无法加载数据连接类库:"+dbclass);
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			st =  conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]:无法初始化数据库连接:dburl="+dburl 
								+ ";dbuser=" + dbuser + ";dbpasswd=" + dbpasswd);
			e.printStackTrace();
		}
		
	}
	
	public ResultSet executeQuery(String sqlCmd){
		rs = null;
		if(conn != null){
			try {
				//st = conn.createStatement();
				rs = st.executeQuery(sqlCmd);
				//st.close();
				//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return rs;
	}
	
	public int executeUpdate(String sqlCmd){
		int ret = 0;
		
		if(conn != null){
			try {
				//st = conn.createStatement();
				ret = st.executeUpdate(sqlCmd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return ret;
	}
	
	public void close(){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args){
		
		DBConnector mysql = new DBConnector(DBConnector.class.getResourceAsStream("mysql.properties"));
		String sqlcmd = "select * from user";
		
		mysql.initConnection();
		mysql.executeUpdate("insert into user values('test','test')");
		ResultSet rs = mysql.executeQuery(sqlcmd);
		
		
		try {
			while(rs.next()){
				System.out.println(rs.getString(1) + rs.getString(2));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mysql.close();
				
	}

}
