package cryptocurrency.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

import cryptocurrency.services.DBManager;
import cryptocurrency.services.DatabaseConnectionService;


public class UIMain {

	public static void main(String[] args) throws SQLException {
		
//		String url = "jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=Cryptocurrency;user=byersrw";
//
//		DatabaseConnectionService dbcs = new DatabaseConnectionService("Cryptocurrency", "golem.csse.rose-hulman.edu");
//		dbcs.connect("byersrw", "Captain123");
//		
//		GraphicsHelper helper = new GraphicsHelper(dbcs);
//		JFrame mainFrame = helper.create("currency");
//
//		mainFrame.setVisible(true);
		
		DBManager mgnr = new DBManager();
		mgnr.initilize();
//		
	}
}
		
//
//		CurrencyView curView = new CurrencyView();
//		Frame mainFrame = helper.defaultView(dbcs);// Builds an initial frame for the database
//		mainFrame.setVisible(true);
//		System.out.println("MainFrame Built");
//
//	}

//}
//jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=Cryptocurrency;user=byersrw URL
//jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=SodaBasebyersrw28;user=SodaBaseUserbyersrw28;password={Password123} URL
