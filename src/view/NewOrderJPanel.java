package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.OrderController;
import model.Customer;
import model.Order;

/**
 * NewOrderJPanel allows you to add new orders
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Apr 21, 2022
 */
public class NewOrderJPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private OrderController oc;
	
	private JLabel nameLabel = new JLabel("Customer Name:");
	private JLabel itemLabel = new JLabel("Items:");
	private JLabel arrLabel = new JLabel("Arrival Time:");
	
	private JTextField nameField = new JTextField();
	private JTextField itemField = new JTextField();
	private JTextField hourField = new JTextField();
	private JTextField minuteField = new JTextField();
	
	private JButton back = new JButton("Back");
	private JButton addOrder = new JButton("Add Order");
	
	//For editing
	private Order toEdit;
	private int mode = 1;
	
	private buttonListener b1 = new buttonListener();
	
	/**
	 * @param oc
	 */
	public NewOrderJPanel(OrderController oc)
	{
		super();
		this.oc = oc;
		
		setLayout(null);
		
		nameLabel.setBounds(50, 50, 100, 25);
		itemLabel.setBounds(50, 100, 100, 25);
		arrLabel.setBounds(50, 150, 100, 25);
		
		nameField.setBounds(300, 50, 300, 25);
		itemField.setBounds(300, 100, 300, 25);
		hourField.setBounds(300, 150, 150, 25);
		minuteField.setBounds(450, 150, 150, 25);
		
		back.setBounds(300, 500, 225, 75);
		addOrder.setBounds(650, 500, 225, 75);
		
		back.addActionListener(b1);
		addOrder.addActionListener(b1);
		
		add(nameLabel);
		add(itemLabel);
		add(arrLabel);
		add(nameField);
		add(itemField);
		add(hourField);
		add(minuteField);
		add(back);
		add(addOrder);
	}
	
	/**
	 * Enable edit order mode, this saves code so edit order JPanel doesn't have to be created
	 */
	public void editOrder(Order toEdit)
	{
		this.toEdit = toEdit;
		mode = 2;
		back.setText("Cancel");
		addOrder.setText("Save Changes");
		nameField.setText(toEdit.getCustomer().getName());
		itemField.setText(String.valueOf(toEdit.getItems()));
		hourField.setText(String.valueOf(toEdit.getPickupTime().getHour()));
		minuteField.setText(String.valueOf(toEdit.getPickupTime().getMinute()));
	}
	
	/**
	 * Reset add order mode
	 */
	private void reset()
	{
		nameField.setText("");
		itemField.setText("");
		hourField.setText("");
		minuteField.setText("");
	}
	
	/**
	 * Reset edit order mode
	 */
	private void backtoAdd()
	{
		mode = 1;
		back.setText("Back");
		addOrder.setText("Add Order");
		nameField.setText("");
		itemField.setText("");
		hourField.setText("");
		minuteField.setText("");
	}
	
	class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(back))
			{
				oc.getMc().changeSlide("orders", "Orders");
				if(mode == 2)
				{
					backtoAdd();
				}
			}
			else if(e.getSource().equals(addOrder))
			{
				if(nameField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(getParent(), "Please enter a name for customer.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(itemField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(getParent(), "Please enter amount of items for order.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(hourField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(getParent(), "Please enter hour of order.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(minuteField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(getParent(), "Please enter minute of order.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					try
					{
						int items = Integer.parseInt(itemField.getText());
						int hour = Integer.parseInt(hourField.getText());
						int minute = Integer.parseInt(minuteField.getText());
						
						if(items < 1)
						{
							JOptionPane.showMessageDialog(getParent(), "Order must have at least 1 item.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
						if(mode == 2)
						{
							Customer c1 = toEdit.getCustomer();
							//Customer c2 = new Customer(nameField.getText());
							Order rep = new Order(toEdit.getCustomer(), items, LocalTime.of(hour, minute));
							rep.setReady(toEdit.isReady());
							rep.setLate(toEdit.isLate());
							rep.setStatus(toEdit.getStatus());
							rep.setShopper(toEdit.getShopper());
							c1.setName(nameField.getText());
							c1.setOrder(rep);
							oc.replaceOrder(toEdit, rep);
							//oc.getMc().getC1().replaceCustomer(c1, c2);
							oc.getMc().changeSlide("orders", "Orders");
							backtoAdd();
						}
						else
						{
							Customer c1 = new Customer(nameField.getText());
							Order rep = new Order(c1, items, LocalTime.of(hour, minute));
							c1.setOrder(rep);
							oc.addOrder(rep);
							oc.getMc().getC1().addCustomer(c1);
							reset();
						}
					}
					catch(NumberFormatException n)
					{
						JOptionPane.showMessageDialog(getParent(), "Invalid value, do not enter letters into number fields.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					catch(DateTimeException d)
					{
						JOptionPane.showMessageDialog(getParent(), "Invalid time, please enter value between 0-23 for hour and 0-59 for minute. (24-Hour Time)", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		}
	}
}
