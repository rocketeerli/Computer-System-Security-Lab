package bank.system;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import bank.util.*;

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
    	System.out.println("插入用户信息");
    	Client client = new Client();
    	client.setUserName("lalala");
    	client.setUserPassword("lalala");
    	client.setDeposit(0);
    	insertClient(client);
    	System.out.println("插入管理员信息");
    	Administrator administrator = new Administrator();
    	administrator.setUserName("heihei");
    	administrator.setUserPassword("heihei");
    	insertManager(administrator);
    }
    
    // 查询用户名对应的密码
    public static void getClientInformation() {
    	Information.clients = new ArrayList<>();
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
                long id  = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int money = rs.getInt("money");
                	
                Client client = new Client();
                client.setDeposit(money);
                client.setId(id);
                client.setUserName(name);
                client.setUserPassword(password);
                // 存储数据
                Information.clients.add(client);
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
    	Information.administrators = new ArrayList<>();
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
                long id  = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
    
                Administrator administrator = new Administrator();
                administrator.setId(id);
                administrator.setUserName(name);
                administrator.setUserPassword(password);
                // 存储数据
                Information.administrators.add(administrator);
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
    
    // 查询未处理的账单信息
    public static void getBillInformation() {
    	Information.bills = new ArrayList<>();
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
    
                Bill bill = new Bill();
                bill.setId(id);
                bill.setClient_id(client_id);
                bill.setName(name);
                bill.setMoney(money);
                // 存储数据
                Information.bills.add(bill);
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
    public static int insertBill(Bill bill) {
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
            Random rand = new Random();
            int id = rand.nextInt(100000)+ 1; 
            sql = "insert into bill_waiting_deal(id, client_id, name, money) values(" + 
            		"'" + id + "', '" + bill.getClient_id() + "', '" + bill.getName() +"', '" + bill.getMoney() + "')";
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
				System.out.println("插入数据成功!!!");
				stmt.close();
	            conn.close();
				return id;
			} else {
				System.out.println("插入数据失败!!!");
			}
            // 完成后关闭
            stmt.close();
            conn.close();
            return 0;
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
        return 0;
    }
    
    // 更改数据库的存款信息
    public static void updateDeposit(long client_id, long deposit) {
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
            sql = "update client_info set money = '" + deposit + "' where id = '" + client_id + "'";
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
				System.out.println("更新存款成功!!!");
			} else {
				System.out.println("更新存款失败!!!");
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
    
    // 增加用户信息
    public static int insertClient(Client client) {
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
            Random rand = new Random();
            int id = rand.nextInt(100000)+ 1; 
            sql = "insert into client_info(id, name, password, money) values(" + 
            	"'" + id + "', '" + client.getUserName() + "', '" + client.getUserPassword() +"', '" + client.getDeposit() + "')";
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
				System.out.println("插入数据成功!!!");
				stmt.close();
	            conn.close();
				return id;
			} else {
				System.out.println("插入数据失败!!!");
			}
            // 完成后关闭
            stmt.close();
            conn.close();
            return 0;
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
        return 0;
    }
    
    // 增加管理员信息
    public static int insertManager(Administrator administrator) {
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
            Random rand = new Random();
            int id = rand.nextInt(100000)+ 1; 
            sql = "insert into manager_info(id, name, password) values(" + 
            	"'" + id + "', '" + administrator.getUserName() + "', '" + administrator.getUserPassword() + "')";
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
				System.out.println("插入数据成功!!!");
				stmt.close();
	            conn.close();
				return id;
			} else {
				System.out.println("插入数据失败!!!");
			}
            // 完成后关闭
            stmt.close();
            conn.close();
            return 0;
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
        return 0;
    }
    
}
