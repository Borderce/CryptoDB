package cryptocurrency.ui;

import javax.swing.JFrame;

import cryptocurrency.services.DatabaseConnectionService;
import cryptocurrency.services.View;

public class WalletsView extends View {
	
	private DatabaseConnectionService d;

	public WalletsView(DatabaseConnectionService d){
		this.d = d;
	}

	@Override
	public JFrame createView() {
		// TODO Auto-generated method stub
		return null;
	}

}
