package cryptocurrency.services;

import java.awt.Dimension;

import javax.swing.JFrame;

public abstract class View {
	
	protected final Dimension frameSize = new Dimension(900,600);
	public abstract JFrame createView();
	
	

}
