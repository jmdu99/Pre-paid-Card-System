package prepaidsystemgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.PlainDocument;

import controller.DataExchange;
import prepaidsystem.PrepaidSystem;
import prepaidsystemexceptions.CreateNumberFormatException;
import prepaidsystemexceptions.CreateSystemException;


public class ChangePinWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton acceptButton;
	private JButton cancelButton;

	JPanel panelChangePinWindow;

	JTextField cardNumberInputField;
	JPasswordField pinInputFieldChangePin;
	JTextPane errorFieldBuyChangePinWindow;
	JPasswordField newPinInputField;
	
	PlainDocument document;

	public ChangePinWindow(DataExchange data) {

		// Frame characteristics
		setTitle("CHANGE PIN");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(350, 360);
		this.setResizable(false);
		this.setVisible(data.getGui());
		this.setLocationRelativeTo(null);

		// Panel characteristics
		panelChangePinWindow = (JPanel) this.getContentPane();
		panelChangePinWindow.setLayout(null);

		// Change pin
		JLabel windowTitle = new JLabel("CHANGE PIN");
		panelChangePinWindow.add(windowTitle);
		windowTitle.setFont(windowTitle.getFont().deriveFont(24.0F));
		Dimension sizeWindowTitle = windowTitle.getPreferredSize();
		windowTitle.setBounds(100, 30, sizeWindowTitle.width, sizeWindowTitle.height);

		// cardNumber label
		JLabel cardNumberLabel = GenericFunctions.createLabel("Card number", 40, 100);
		panelChangePinWindow.add(cardNumberLabel);


		// cardNumber input field
		cardNumberInputField = GenericFunctions.createInputField(140, 100, 180, 20);
		add(cardNumberInputField);

		// Old Pin label
		JLabel pinLabel = GenericFunctions.createLabel("Old PIN", 40, 150);
		panelChangePinWindow.add(pinLabel);


		// Old Pin input field
		pinInputFieldChangePin = PrepaidSystem.preparePinField(140, 150, 100, 20);
		add(pinInputFieldChangePin);
		
		// New Pin label
		JLabel repeatPinLabel = GenericFunctions.createLabel("New PIN", 40, 200);
		panelChangePinWindow.add(repeatPinLabel);

		// New Pin input field
		newPinInputField = PrepaidSystem.preparePinField(140, 200, 100, 20);
		add(newPinInputField);

		// Button to accept
		acceptButton =  GenericFunctions.createButton("Accept", 50, 250, 100, 40);
		panelChangePinWindow.add(acceptButton);
		acceptButton.addActionListener(this);

		// Button to finish
		cancelButton =  GenericFunctions.createButton("Finish", 200, 250, 100, 40);
		panelChangePinWindow.add(cancelButton);
		cancelButton.addActionListener(this);

		// Errors
		errorFieldBuyChangePinWindow = GenericFunctions.errorField(50, 300, 300, 40);
		add(errorFieldBuyChangePinWindow);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
         String errors="";
		if (e.getSource() == acceptButton) {
			try {
				String pin1String = String.valueOf(pinInputFieldChangePin.getPassword());
				String pin2String = String.valueOf(newPinInputField.getPassword());
				DataExchange data = new DataExchange(cardNumberInputField.getText(), pin1String, pin2String);
				PrepaidSystem.changePin(data);
				errorFieldBuyChangePinWindow.setText("PIN changed correctly");
			} catch (CreateSystemException | NoSuchAlgorithmException | UnsupportedEncodingException | NumberFormatException | CreateNumberFormatException e1) {
				errors=e1.getMessage();
			    errorFieldBuyChangePinWindow.setText(errors);
			} 
			
		} else if (e.getSource() == cancelButton) {
			setVisible(false);
			dispose();
		}
	}
}