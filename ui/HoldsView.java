package cryptocurrency.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import cryptocurrency.services.CurrencyView;
import cryptocurrency.services.DatabaseConnectionService;
import cryptocurrency.services.HoldsService;
import cryptocurrency.services.Service;
import cryptocurrency.services.View;

public class HoldsView extends View {
	
	private static Service service;
	private static String[] columnNames = {"WalletID", "CryptoID", "Quantity"};
	
	public HoldsView(DatabaseConnectionService d){
		HoldsView.service = new HoldsService(d);
	}

	@Override
	public JFrame createView() {
		
		System.out.println("Building Holds View");

		JFrame mainFrame = new JFrame();
		
		String[][] results = service.getItems();
		
		JTable dataTable = new JTable(results, columnNames);

		
		// Create and delete currency buttons
		JButton deleteEntryButton = new JButton("Delete Entry");
		JButton addEntryButton = new JButton("Add Entry");
		JButton updateEntry = new JButton("Update Entry");

		JPanel buttons = new JPanel();

		buttons.add(deleteEntryButton);
		buttons.add(addEntryButton);
		buttons.add(updateEntry);

		addEntryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Adding Holds Entry");

				String WalletID = JOptionPane.showInputDialog("Please enter in the WalletID");
				String curID = JOptionPane.showInputDialog("Please enter in the CurrencyID of the new entry");
				String qa = JOptionPane
						.showInputDialog("Please enter in the quantity of the currency for this entry");

				Service c = HoldsView.service;
				String[] newEntry = { WalletID, curID, qa };
				
				c.addItem(newEntry);
				String[][] results = c.getItems();

				JTable dataTable = new JTable(results, columnNames);
				mainFrame.add(dataTable, BorderLayout.CENTER);
				mainFrame.setVisible(true);

			}
		});

		updateEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Updating Holds Entry");

				String name = JOptionPane.showInputDialog(
						"Please input the new name of the currency or keep it the same" + "to not change it");
				String ID = JOptionPane.showInputDialog("Please input the currencyID");
				String Quantity = JOptionPane
						.showInputDialog("Please input a new Quantity or press enter to not update");
				String Price = JOptionPane.showInputDialog("Please input a new Price");

				Service c = HoldsView.service;
				String[] values = { name, ID, Quantity, Price };
				c.update(values);
				String[][] results = c.getItems();
				JTable dataTable = new JTable(results, columnNames);
				
				mainFrame.add(dataTable, BorderLayout.CENTER);
				mainFrame.setVisible(true);

			}
		});

		deleteEntryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Deleting Holds Entry");

				String curID = JOptionPane
						.showInputDialog("Please enter in the currencyID of the Currency you would like" + "to delete");

				Service c = HoldsView.service;
				c.deleteItem(curID);
				
				String[][] results = c.getItems();

				JTable dataTable = new JTable(results, columnNames);
				mainFrame.add(dataTable, BorderLayout.CENTER);
				mainFrame.setVisible(true);

			}
		});

		// ***************Options to make it look nice later***********
		// JPanel toReturn = new JPanel();
		// GridLayout layout = new GridLayout(1,1);
		// layout.setHgap(600);
		// layout.s
		// toReturn.setLayout(layout);
		// toReturn.add(deleteCurrencyButton);
		// toReturn.add(addCurrencyButton);

		mainFrame.add(buttons, BorderLayout.SOUTH);
		mainFrame.add(dataTable, BorderLayout.CENTER);
		mainFrame.setSize(frameSize);
		mainFrame.setLocation(0, 0);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return mainFrame;

	
	}

}


