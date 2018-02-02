package cryptocurrency.services;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import cryptocurrency.ui.GraphicsHelper;

public class DBManager {
	
	private DatabaseConnectionService d;
	private cryptocurrency.services.MenuListener mls;
	private GraphicsHelper helper;
	private JFrame mainFrame;
	
	public DBManager(){
		DatabaseConnectionService dbcs = new DatabaseConnectionService("Cryptocurrency", "golem.csse.rose-hulman.edu");
		dbcs.connect("byersrw", "Captain123");
		cryptocurrency.services.MenuListener mls = new cryptocurrency.services.MenuListener(this);
		this.mls = mls;
		this.d = dbcs;
		this.helper = new GraphicsHelper(dbcs);
	}
	
	public void initilize(){ // Creates the defaultview
//		JFrame mainFrame = helper.create("Currency");
//		this.mainFrame = mainFrame;
//		showFrame();

		JFrame mainFrame = helper.create("Login");
		this.mainFrame = mainFrame;
		this.mainFrame.setVisible(true);
	}
	
	public JMenuBar createMenu(){
		JMenuBar jmb = new JMenuBar();
		JMenu wallets = new JMenu("Wallets");
		JMenu holds = new JMenu("Holds");
		JMenu currency = new JMenu("Currency");

		wallets.addMenuListener(mls);
		holds.addMenuListener(mls);
		currency.addMenuListener(mls);
		jmb.add(holds);
		jmb.add(wallets);
		jmb.add(currency);
		
		return jmb;
	}
	
	public void refresh(){
		JFrame mainFrame = helper.create(mls.getSelection());
		if(this.mainFrame != null){
			this.mainFrame.show(false);

		}
		this.mainFrame = mainFrame;
		showFrame();
	}
	
	public void showFrame(){
		this.mainFrame.add(createMenu(), BorderLayout.NORTH);
		mainFrame.setVisible(true);
	}
	
	public void showSpecificFrame(JFrame f){
		this.mainFrame = f;
		this.mainFrame.add(createMenu(), BorderLayout.NORTH);
		mainFrame.setVisible(true);
	}
	
	public void getSpecificFrame(String s){
		showSpecificFrame(helper.create(s));
	}

}
