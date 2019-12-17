package c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Test {

	static int x=11;
	static ComboPooledDataSource datasource;
	static{
		datasource=new ComboPooledDataSource("mysql");
	}
	static void close(){
		datasource.close();
	}
	static Connection getConn() throws SQLException{
		return datasource.getConnection();
	}
	
	public static void main(String[] args)throws Exception {
		System.out.println(Test.x);
		try {
			//NewProxyConnection
			Connection con=Test.getConn();
			System.out.println(con.getClass());
			System.in.read();
			
		} finally {
			Test.close();
		}
	}
	
}
