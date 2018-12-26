package bank.system;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

public class MysqlConnect {
	
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/computer_system_security?serverTimezone=GMT%2B8";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
 
    public static void main(String[] args) {
    	getClientInformation();
    	System.out.println("管理员信息：");
    	getManagerInformation();
    	System.out.println("账单信息：");
    	getBillInformation();
    	System.out.println("删除账单");
    	deleteBill("33");
//    	System.out.println("插入账单信息");
//    	insertBill(333, "lgj", 800);
    }
    
    // 查询用户名对应的密码
    public static void getClientInformation() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, password, money FROM client_info";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int money = rs.getInt("money");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 用户名: " + name);
                System.out.print(", 用户密码: " + password);
                System.out.print(", 存款余额: " + money);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) 
                	conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    // 查询管理员用户对应的密码
    public static void getManagerInformation() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, password FROM manager_info";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 用户名: " + name);
                System.out.print(", 用户密码: " + password);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) 
                	conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    // 查询管理员用户对应的密码
    public static void getBillInformation() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id,client_id, name, money FROM bill_waiting_deal";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                int client_id = rs.getInt("client_id");
                String name = rs.getString("name");
                int money  = rs.getInt("money");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", client_id: " + client_id);
                System.out.print(", 用户名: " + name);
                System.out.print(", 操作金额: " + money);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) 
                	conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    // 删除操作账单
    public static void deleteBill(String delete_bill_id) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM bill_waiting_deal where id = " + delete_bill_id;
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
				System.out.println("删除成功!!!");
			} else {
				System.out.println("删除失败!!!");
			}
            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) 
                	conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    // 增加账单信息
    public static void insertBill(int client_id, String name, int money) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            String id = "555"; 
            sql = "insert into bill_waiting_deal(id, client_id, name, money) values(" + 
            		"'" + id + "', '" + client_id + "', '" + name +"', '" + money + "')";
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
				System.out.println("插入数据成功!!!");
			} else {
				System.out.println("插入数据失败!!!");
			}
            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) 
                	conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
}
