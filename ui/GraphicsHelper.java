package cryptocurrency.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import cryptocurrency.services.CurrencyView;
import cryptocurrency.services.DatabaseConnectionService;
import cryptocurrency.services.LoginView;
import cryptocurrency.services.View;

public class GraphicsHelper {
	
	private Map<String, View> views;
	private DatabaseConnectionService d;
	
	public GraphicsHelper(DatabaseConnectionService d){
		this.views = new HashMap<>();
		this.views.put("Currency", new CurrencyView(d));
		this.views.put("Holds", new HoldsView(d));
		this.views.put("Wallets", new WalletsView(d));
		this.views.put("Login", new LoginView(d));
		
	}

	public JFrame create(String string) {
		View desiredView = this.views.get(string);
		JFrame mainFrame = desiredView.createView();
		return mainFrame;
		
	}
	
//	public Frame getView(String s){
//		View desiredView = this.views.get(s);
//		
//	}

}
