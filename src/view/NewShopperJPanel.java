package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ShopperController;
import model.Shopper;

/**
 * NewShopperJpanel allows you to create a new shopper
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Apr 23, 2022
 */
public class NewShopperJPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ShopperController sc;
	
	private JLabel nameLabel = new JLabel("Name:");
	private JTextField nameField = new JTextField();
	
	private JButton back = new JButton("Back");
	private JButton addShopper = new JButton("Add Shopper");
	
	//For editing
	private Shopper toEdit;
	private int mode = 1;
	
	private buttonListener b1 = new buttonListener();
	
	/**
	 * @param sc
	 */
	public NewShopperJPanel(ShopperController sc)
	{
		super();
		this.sc = sc;
		
		setLayout(null);
		
		nameLabel.setBounds(50, 50, 100, 25);
		
		nameField.setBounds(300, 50, 300, 25);
		
		back.setBounds(300, 500, 225, 75);
		addShopper.setBounds(650, 500, 225, 75);
		
		back.addActionListener(b1);
		addShopper.addActionListener(b1);
		
		add(nameLabel);
		add(nameField);
		add(back);
		add(addShopper);
	}
	
	/**
	 * Enable edit shopper mode
	 * @param toEdit
	 */
	public void editShopper(Shopper toEdit)
	{
		this.toEdit = toEdit;
		mode = 2;
		back.setText("Cancel");
		addShopper.setText("Save Changes");
		nameField.setText(toEdit.getName());
	}
	
	/**
	 * Reset add shopper mode
	 */
	private void reset()
	{
		nameField.setText("");
	}
	
	/**
	 * Reset edit order mode
	 */
	private void backtoAdd()
	{
		mode = 1;
		back.setText("Back");
		addShopper.setText("Add Shopper");
		nameField.setText("");
	}
	
	class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(back))
			{
				sc.getMc().changeSlide("shoppers", "Shoppers");
				if(mode == 2)
				{
					backtoAdd();
				}
			}
			else if(e.getSource().equals(addShopper))
			{
				if(nameField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(getParent(), "Please enter a name for shopper", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					if(mode == 2)
					{
						Shopper rep = new Shopper(nameField.getText(), toEdit.isClockedIn(), toEdit.getShiftStart(), toEdit.getShiftEnd(), toEdit.getOrder());
						sc.replaceShopper(toEdit, rep);
						sc.getMc().changeSlide("shoppers", "Shoppers");
						backtoAdd();
					}
					else
					{
						Shopper rep = new Shopper(nameField.getText());
						sc.addShopper(rep);
						reset();
					}
				}
			}
		}
		
	}
}
