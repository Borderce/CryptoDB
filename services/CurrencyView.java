package cryptocurrency.services;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutFocusTraversalPolicy;

public class CurrencyView extends View {

	private static Service service;
	private static String[] columnNames = {"Name", "CurrencyID", "QuantityAvaiable", "Price"};

	public CurrencyView(DatabaseConnectionService d) {
		CurrencyView.service = new CurrencyService(d);
	}

	public JFrame createView() {

		System.out.println("Building Currency View");

		JFrame mainFrame = new JFrame();
		
		String[][] results = service.getItems();
		
		JTable dataTable = new JTable(results, columnNames);

		

		// Create and delete currency buttons
		JButton deleteCurrencyButton = new JButton("Delete Currency");
		JButton addCurrencyButton = new JButton("Add Currency");
		JButton update = new JButton("Update");

		JPanel buttons = new JPanel();

		buttons.add(deleteCurrencyButton);
		buttons.add(addCurrencyButton);
		buttons.add(update);

		addCurrencyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add Currency");

				String name = JOptionPane.showInputDialog("Please enter in the name of the new cryptocurrency");
				String curID = JOptionPane.showInputDialog("Please enter in the CurrencyID of the new cryptocurrency");
				String qa = JOptionPane
						.showInputDialog("Please enter in the quantity available of the new cryptocurrency");
				String price = JOptionPane.showInputDialog("Please enter in the price of the new cryptocurrency");

				Service c = CurrencyView.service;
				String[] newCur = { name, curID, qa, price };
				
				c.addItem(newCur);
				String[][] results = c.getItems();

				JTable dataTable = new JTable(results, columnNames);
				mainFrame.add(dataTable, BorderLayout.CENTER);
				mainFrame.setVisible(true);

			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Updating Currency");

				String name = JOptionPane.showInputDialog(
						"Please input the new name of the currency or keep it the same" + "to not change it");
				String ID = JOptionPane.showInputDialog("Please input the currencyID");
				String Quantity = JOptionPane
						.showInputDialog("Please input a new Quantity or press enter to not update");
				String Price = JOptionPane.showInputDialog("Please input a new Price");

				Service c = CurrencyView.service;
				String[] values = { name, ID, Quantity, Price };
				c.update(values);
				String[][] results = c.getItems();
				JTable dataTable = new JTable(results, columnNames);
				
				mainFrame.add(dataTable, BorderLayout.CENTER);
				mainFrame.setVisible(true);

			}
		});

		deleteCurrencyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Deleting Currency");

				String curID = JOptionPane
						.showInputDialog("Please enter in the currencyID of the Currency you would like" + "to delete");

				Service c = CurrencyView.service;
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
