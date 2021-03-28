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
import prepaidsystemexceptions.CreateSystemException;


public class ChargeMoneyWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton acceptButton;
	private JButton cancelButton;

	JPanel panelChargeMoneyWindow;

	JTextField cardNumberInputField;
	JPasswordField pinInputFieldChargeMoney;
	JTextPane errorFieldChargeMoneyWindow;
	JFormattedTextField amountInputFieldChargeMoney;
	
	PlainDocument document;

	public ChargeMoneyWindow(DataExchange data) {

		// Frame characteristics
		setTitle("CHARGE MONEY");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(350, 360);
		this.setResizable(false);
		this.setVisible(data.getGui());
		this.setLocationRelativeTo(null);

		// Panel characteristics
		panelChargeMoneyWindow = (JPanel) this.getContentPane();
		panelChargeMoneyWindow.setLayout(null);

		// Charge money
		JLabel windowTitle = new JLabel("CHARGE MONEY");
		panelChargeMoneyWindow.add(windowTitle);
		windowTitle.setFont(windowTitle.getFont().deriveFont(24.0F));
		Dimension sizeWindowTitle = windowTitle.getPreferredSize();
		windowTitle.setBounds(75, 30, sizeWindowTitle.width, sizeWindowTitle.height);

		// cardNumber label
		JLabel cardNumberLabel = GenericFunctions.createLabel("Card number", 40, 100);
		panelChargeMoneyWindow.add(cardNumberLabel);


		// cardNumber input field
		cardNumberInputField = new JTextField();
		cardNumberInputField.setBounds(140, 100, 180, 20);
		cardNumberInputField.setEditable(true);
		add(cardNumberInputField);

		// Pin label
		JLabel pinLabel = GenericFunctions.createLabel("PIN", 40, 150);
		panelChargeMoneyWindow.add(pinLabel);
		
		// Pin input field
		pinInputFieldChargeMoney = PrepaidSystem.preparePinField(140, 150, 100, 20);
		add(pinInputFieldChargeMoney);

		// Amount label
		JLabel amountLabel = GenericFunctions.createLabel("Amount", 40, 200);
		panelChargeMoneyWindow.add(amountLabel);

		// € label
		JLabel eLabel = GenericFunctions.createLabel("Amount", 250, 200);
		panelChargeMoneyWindow.add(eLabel);

		// Amount input field
		amountInputFieldChargeMoney = PrepaidSystem.prepareAmountInputField(140, 200, 100, 20);
		add(amountInputFieldChargeMoney);

		// Button to accept
		acceptButton =  GenericFunctions.createButton("Accept", 50, 250, 100, 40);
		panelChargeMoneyWindow.add(acceptButton);
		acceptButton.addActionListener(this);

		// Button to cancel
		cancelButton =  GenericFunctions.createButton("Cancel", 200, 250, 100, 40);
		panelChargeMoneyWindow.add(cancelButton);
		cancelButton.addActionListener(this);

		// Errors
		errorFieldChargeMoneyWindow = GenericFunctions.errorField(50, 300, 300, 40);
		add(errorFieldChargeMoneyWindow);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == acceptButton) {
			String errors = "";
			 try {
				String pin1String = String.valueOf(pinInputFieldChargeMoney.getPassword());
				DataExchange data = new DataExchange(cardNumberInputField.getText(), pin1String,
						amountInputFieldChargeMoney.getText().replace(",", ""), true, this);
				PrepaidSystem.chargeMoney(data);
			} catch (CreateSystemException | NumberFormatException  | NoSuchAlgorithmException | UnsupportedEncodingException e1) {
				errors=e1.getMessage();
			}
			
			errorFieldChargeMoneyWindow.setText(errors);
			if (errors.equals("")) {
				setVisible(false);
				dispose();
			}

		} else if (e.getSource() == cancelButton) {
			setVisible(false);
			dispose();
		}
	}
	

}