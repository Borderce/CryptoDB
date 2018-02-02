package cryptocurrency.services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CurrencyService implements Service {

	private DatabaseConnectionService dbService;

	public CurrencyService(DatabaseConnectionService d) {
		this.dbService = d;
	}

	@Override
	public String[][] getItems() { // Returns all items in the currency table
		System.out.println("Returning all items for display");

		String currencyDetails = "Select * From Currency";
		String rowQ = "Select Count(*) from Currency";

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

				results[currRow][currentCol] = rs.getString("Name");
				currentCol++;
				results[currRow][currentCol] = rs.getString("CurrencyID");
				currentCol++;
				results[currRow][currentCol] = rs.getString("QuantityAvailable") + " Units";
				currentCol++;
				results[currRow][currentCol] = "$" + rs.getString("Price");
				currRow++;
				currentCol = 0;

			}
			return results;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"An error ocurred while retrieving Currencies. See printed stack trace.");
			ex.printStackTrace();
			return new String[5][5];
		}
	}

	@Override
	public boolean addItem(String[] newRow) {
		String storedProcedureQuery = "Use Cryptocurrency Exec insert_new_currency" + "'" + newRow[0] + "'" + ", " + "'"
				+ newRow[1] + "'" + "," + newRow[2] + "," + newRow[3];

		try {
			PreparedStatement p = this.dbService.getConnection().prepareStatement(storedProcedureQuery);
			if (p.execute()) {
				System.out.println("Sucessfully added currnecy");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteItem(String s) {

		String deleteStmt = "Use Cryptocurrency Exec delete_currency " + s;

		try {
			PreparedStatement p = this.dbService.getConnection().prepareStatement(deleteStmt);
			p.execute();
			if (p.execute()) {
				System.out.println("Sucessfully Deleted currency");
			}
		} catch (SQLException e) {
			System.out.println("Currency was not deleted");
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void refresh() {

	}

	@Override
	public boolean update(String[] updates) {
		
		String update = "Use Cryptocurrency Exec update_currency_priceAndQuantityAvailable " + updates[0] + "," 
				+ updates[1] + "," + updates[2] + "," + updates[3];
		
		try {
			PreparedStatement p = this.dbService.getConnection().prepareStatement(update);
			p.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
