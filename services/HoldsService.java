package cryptocurrency.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class HoldsService implements Service {
	
	private DatabaseConnectionService dbService;

	public HoldsService(DatabaseConnectionService d){
		this.dbService = d;
	}

	@Override
	public String[][] getItems() {
		
		System.out.println("Returning all items for display in the Holds Table");

		String currencyDetails = "Select * From Holds";
		String rowQ = "Select Count(*) from Holds";

		try {

			PreparedStatement p2 = this.dbService.getConnection().prepareStatement(currencyDetails);
			PreparedStatement row = this.dbService.getConnection().prepareStatement(rowQ);

			ResultSet rs3 = p2.executeQuery();
			ResultSet numRows = row.executeQuery();

			numRows.next();
			int rowCount = numRows.getInt(1);

			String[][] items = parseResultSet(rs3, rowCount);

			return items;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new String[5][5];
	}

	private String[][] parseResultSet(ResultSet rs, int rows) throws SQLException {

	int colCount = rs.getMetaData().getColumnCount();
	String[][] results = new String[rows][colCount];

	try {

		int currRow = 0;
		int currentCol = 0;
		while (rs.next()) {

			results[currRow][currentCol] = rs.getString("WalletID");
			currentCol++;
			results[currRow][currentCol] = rs.getString("CryptoID");
			currentCol++;
			results[currRow][currentCol] = rs.getString("Quantity") + " Units";
			currentCol++;
			currRow++;
			currentCol = 0;

		}
		return results;
	} catch (SQLException ex) {
		JOptionPane.showMessageDialog(null,
				"An error ocurred while retrieving Holds. See printed stack trace.");
		ex.printStackTrace();
		return new String[5][5];
	}
}

	@Override
	public boolean addItem(String[] s) {
		
		
		return false;
	}

	@Override
	public boolean deleteItem(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(String[] updates) {
		// TODO Auto-generated method stub
		return false;
	}

}
