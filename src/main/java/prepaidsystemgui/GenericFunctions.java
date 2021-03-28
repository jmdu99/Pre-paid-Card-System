package prepaidsystemgui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class GenericFunctions {
	
	private GenericFunctions() {
		throw new UnsupportedOperationException("No needs instanciation");
	}
	
	public static JTextPane createOutputField(int x1, int y1, int x2, int y2) {
		JTextPane outputField = new JTextPane();
		outputField.setFont(new Font("Courier New", Font.PLAIN, 14));
		outputField.setBounds(x1, y1, x2, y2);
		outputField.setEditable(false);
		return outputField;
	}
	
	public static JButton createButton(String text, int x1, int y1, int x2, int y2) {
		JButton button = new JButton();
		button.setText(text);
		button.setBounds(x1, y1, x2, y2);
		return button;
	}
	
	public static JLabel createLabel(String text, int x1, int y1) {
		JLabel label = new JLabel(text);
		Dimension sizeAmountLabel = label.getPreferredSize();
		label.setBounds(x1, y1, sizeAmountLabel.width, sizeAmountLabel.height);
		return label;		
	}
	
	public static JTextPane errorField(int x1, int y1, int x2, int y2) {
		JTextPane errorField = new JTextPane();
		errorField.setBounds(x1, y1, x2, y2);
		errorField.setEditable(false);
		errorField.setOpaque(false);
		return errorField;		
	}
	
	public static JTextField createInputField(int x1, int y1, int x2, int y2) {
		JTextField inputField = new JTextField();
		inputField.setBounds(x1, y1, x2, y2);
		inputField.setEditable(true);
		return inputField;		
	}
}


