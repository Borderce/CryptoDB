package cryptocurrency.services;

import java.util.ArrayList;

public interface Service {
	
	public String[][] getItems();
	public boolean addItem(String[] s);
	public boolean deleteItem(String s);
	public void refresh();
	public boolean update(String[] updates);
	

}
