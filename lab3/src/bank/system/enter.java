package bank.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class enter {

	public static void main(String[] args) {
		loginPage();
	}
	
	/**
	 * 用户登录
	 * */
	private static void loginPage() {
		JFrame frame = new JFrame("银行系统设计——用户登录");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 创建面板
		JPanel panel = new JPanel();
		panel.setLayout(null);    // 面板布局
		
		//创建 标签 & 输入框 & 按钮
		JLabel userLabel = new JLabel("User:");
		JLabel passwordLabel = new JLabel("Password:");
		JTextField userNameText = new JTextField(20);       
		JTextField userPasswordText = new JTextField(20);
		JButton loginButton = new JButton("登录");
		
		// 设置标签的大小和位置
		userLabel.setBounds(10, 20, 80, 25);
		userNameText.setBounds(100, 20, 165, 25);
		passwordLabel.setBounds(10, 50, 80, 25);
		userPasswordText.setBounds(100, 50, 165, 25);
		loginButton.setBounds(10, 80, 80, 25);
		
		// 设置面板内容
		panel.add(userLabel);
		panel.add(userNameText);
		panel.add(passwordLabel);
		panel.add(userPasswordText);
		panel.add(loginButton);
		
		// 将面板加入到窗口中
		frame.add(panel);
		
		// 按钮的监听事件
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (userNameText.getText().equals("lgj") && userPasswordText.getText().equals("lgjpass")) {
					clientService();
					frame.dispose();
				} else {
					frame.dispose();
					loginFailedPage();
				}
			}
		});
		// 设置窗口可见
		frame.setVisible(true);
	} 
	
	private static void loginFailedPage() {
		JFrame frame = new JFrame("用户登录失败");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 创建面板
		JPanel panel = new JPanel();
		panel.setLayout(null);    // 面板布局
				
		// 创建 标签 & 按钮
		JLabel messageLabel = new JLabel("不存在该用户或密码错误");
		JButton loginAgainButton = new JButton("重新登录");
		
		// 设置标签和按钮的大小和位置
		messageLabel.setBounds(60, 40, 165, 30);
		loginAgainButton.setBounds(60, 80, 150, 30);
		
		// 加入面板中
		panel.add(messageLabel);
		panel.add(loginAgainButton);
		
		loginAgainButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳到登录界面
				loginPage();
				frame.dispose();
			}
		});
		
		// 将面板加入窗口中
		frame.add(panel);
		// 设置窗口可见
		frame.setVisible(true);
	}
	
	private static void clientService() {
		JFrame frame = new JFrame("客户服务");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 创建面板
		JPanel panel = new JPanel();
		panel.setLayout(null);    // 面板布局
		
		//创建 选项按钮
		JButton depositButton = new JButton("存款");
		JButton withdrawButton = new JButton("取款");
		JButton queryButton = new JButton("查询余额");
		JButton returnButton = new JButton("注销登录");
		
		// 设置标签和按钮的大小和位置
		depositButton.setBounds(60, 80, 165, 30);
		withdrawButton.setBounds(60, 120, 165, 30);
		queryButton.setBounds(60, 160, 165, 30);
		returnButton.setBounds(60, 200, 165, 30);
		
		// 加入面板中
		panel.add(depositButton);
		panel.add(withdrawButton);
		panel.add(queryButton);
		panel.add(returnButton);
		
		// 设置 存款 按钮监听事件
		depositButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关掉当前界面
				frame.dispose();
				// 跳转到存款页面
				depositPage();
			}
		});
		
		// 设置 取款 按钮监听事件
		withdrawButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关掉当前界面
				frame.dispose();
				// 跳转到取款页面
				withdrawPage();
			}
		});
		
		// 设置 查询 按钮监听事件
		queryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关掉当前界面
				frame.dispose();
				// 跳转到查询界面
				queryPage();
			}
		});
		
		// 设置 返回 按钮监听事件
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 关掉当前界面
				frame.dispose();
				// 跳转到登录界面
				loginPage();
			}
		});
		
		// 将面板加入窗口中
		frame.add(panel);
		// 设置窗口可见
		frame.setVisible(true);
	}
	
	// 存款页面
	private static void depositPage() {
		JFrame frame = new JFrame("存款页面");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 创建面板
		JPanel panel = new JPanel();
		panel.setLayout(null);    // 面板布局
		
		// 创建 标签 & 输入框 & 按钮
		JLabel depositRemindLabel = new JLabel("存款金额：");
		JTextField moneyNumberText = new JTextField(30);
		JButton ensureButton = new JButton("确定");
		JButton returnButton = new JButton("返回");
		
		// 设置标签和按钮的大小和位置
		depositRemindLabel.setBounds(60, 40, 75, 30);
		moneyNumberText.setBounds(135, 40, 75, 30);
		ensureButton.setBounds(60, 80, 150, 30);
		returnButton.setBounds(60, 120, 150, 30);
		
		// 加入面板中
		panel.add(depositRemindLabel);
		panel.add(moneyNumberText);
		panel.add(ensureButton);
		panel.add(returnButton);
		
		ensureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到管理员
				loginPage();
			}
		});
		
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到客户服务界面
				frame.dispose();
				clientService();
			}
		});
		
		// 将面板加入窗口中
		frame.add(panel);
		// 设置窗口可见
		frame.setVisible(true);
	}
	
	// 取款页面
	private static void withdrawPage() {
		JFrame frame = new JFrame("取款页面");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 创建面板
		JPanel panel = new JPanel();
		panel.setLayout(null);    // 面板布局
		
		// 创建 标签 & 输入框 & 按钮
		JLabel withdrawRemindLabel = new JLabel("存款金额：");
		JTextField moneyNumberText = new JTextField(30);
		JButton ensureButton = new JButton("确定");
		JButton returnButton = new JButton("返回");
		
		// 设置标签和按钮的大小和位置
		withdrawRemindLabel.setBounds(60, 40, 75, 30);
		moneyNumberText.setBounds(135, 40, 75, 30);
		ensureButton.setBounds(60, 80, 150, 30);
		returnButton.setBounds(60, 120, 150, 30);
		
		// 加入面板中
		panel.add(withdrawRemindLabel);
		panel.add(moneyNumberText);
		panel.add(ensureButton);
		panel.add(returnButton);
		
		ensureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到管理员
				loginPage();
			}
		});
		
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到客户服务界面
				frame.dispose();
				clientService();
			}
		});
		
		// 将面板加入窗口中
		frame.add(panel);
		// 设置窗口可见
		frame.setVisible(true);
	}
	
	// 查询页面
	private static void queryPage() {
		JFrame frame = new JFrame("查询页面");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 创建面板
		JPanel panel = new JPanel();
		panel.setLayout(null);    // 面板布局
		
		// 创建 标签 & 输入框 & 按钮
		JLabel withdrawRemindLabel = new JLabel("余额：");
		JLabel waitingEnsuredLabel = new JLabel("待确认金额：");
		JButton ensureButton = new JButton("确定");
		JButton returnButton = new JButton("返回");
		
		// 设置标签和按钮的大小和位置
		withdrawRemindLabel.setBounds(60, 40, 75, 30);
		waitingEnsuredLabel.setBounds(60, 40, 75, 30);
		ensureButton.setBounds(60, 120, 150, 30);
		returnButton.setBounds(60, 160, 150, 30);
		
		// 加入面板中
		panel.add(withdrawRemindLabel);
		panel.add(waitingEnsuredLabel);
		panel.add(ensureButton);
		panel.add(returnButton);
		
		ensureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到管理员
				loginPage();
			}
		});
		
		// 将面板加入窗口中
		frame.add(panel);
		// 设置窗口可见
		frame.setVisible(true);
	}
}
