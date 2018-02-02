package cryptocurrency.services;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cryptocurrency.ui.GraphicsHelper;

public class LoginView extends View {

	private DatabaseConnectionService d;

	public LoginView(DatabaseConnectionService d) {
		this.d = d;
	}

	

	@Override
	public JFrame createView() {
		
		JButton login = new JButton("Login");
		JButton register = new JButton("Register");
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sucessfully Logged in!");
				DBManager db = new DBManager();
				db.getSpecificFrame("Currency");
				
			}
		});
		
		
		JFrame mainFrame = new JFrame("Cryptocurrency Login");
		JLabel loginName;
		JTextField username;
		JLabel password;
		JTextField passwordEntry;
	
		
		
		JPanel loginParams = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        loginName = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        loginParams.add(loginName, cs);
        
        username = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        loginParams.add(username, cs);
        
        password = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        loginParams.add(password, cs);
 
        passwordEntry = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        loginParams.add(passwordEntry, cs);
        loginParams.setBorder(new LineBorder(Color.GRAY));
        
        JPanel buttons = new JPanel();
        buttons.add(login);
        buttons.add(register);
 
        mainFrame.add(loginParams, BorderLayout.CENTER);
        mainFrame.add(buttons, BorderLayout.PAGE_END);
 
        mainFrame.pack();
        mainFrame.setResizable(false);
		
        mainFrame.setLayout(new FlowLayout());
		mainFrame.setSize(new Dimension(300, 150));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return mainFrame;
	}

}
