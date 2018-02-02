package cryptocurrency.services;

import javax.swing.event.MenuEvent;

public class MenuListener implements javax.swing.event.MenuListener {
	
	String lastSelected;
	private DBManager db;
	
	public MenuListener(DBManager db){
		this.db = db;
	}

	@Override
	public void menuSelected(MenuEvent e) {
		int txtIndex = e.toString().indexOf("text");
		String name = e.toString().substring(txtIndex + 5, e.toString().length() - 2);
		lastSelected = name;
		db.refresh();
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getSelection(){
		return this.lastSelected;
	}

}
