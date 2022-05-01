package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controller.CustomerController;
import model.Customer;
import model.Order;

/**
 * CustomerJPanel allows you to set status of customers and finish up the orders
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Apr 6, 2022
 */
public class CustomersJPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int buttonWidth = 175;
	private int buttonHeight = 225;
	private int buttonSpacing = 50;
	private int buttonTopSpacing = 50;
	
	private int selected = 1;
	
	private CustomerController cc;
	private DefaultListModel<Customer> aCustomers = new DefaultListModel<Customer>();
	
	private JButton cust1 = new JButton("Empty");
	private JButton cust2 = new JButton("Empty");
	private JButton cust3 = new JButton("Empty");
	private JButton cust4 = new JButton("Empty");
	private JButton cust5 = new JButton("Empty");
	private JButton addCustomer = new JButton("Confirm Arrival");
	private JButton completeOrder = new JButton("Order Complete");
	private JList<Customer> customerList = new JList<Customer>(aCustomers);
	private JScrollPane customerBar = new JScrollPane();
	private JLabel status = new JLabel("Space #1: Empty", SwingConstants.CENTER);
	private JPopupMenu listpopup = new JPopupMenu();
	private JMenuItem deletecustomer = new JMenuItem("Cancel Order");
	private JLabel itemStatus = new JLabel("Items: ");
	private JLabel arrivalStatus = new JLabel("Scheduled Arrival Time: ");
	
	private Font bfont = new Font("Arial", Font.PLAIN, 30);
	
	private buttonListener b1 = new buttonListener();
	private listListener l1 = new listListener();

	/**
	 * @param cc
	 */
	public CustomersJPanel(CustomerController cc)
	{
		super();
		this.cc = cc;
		
		setLayout(null);
		customerBar.setViewportView(customerList);
		customerList.setLayoutOrientation(JList.VERTICAL);
		
		cust1.setBounds(buttonSpacing, buttonTopSpacing, buttonWidth, buttonHeight);
		cust2.setBounds((buttonSpacing * 2) + buttonWidth, buttonTopSpacing, buttonWidth, buttonHeight);
		cust3.setBounds((buttonSpacing * 3) + (buttonWidth * 2), buttonTopSpacing, buttonWidth, buttonHeight);
		cust4.setBounds((buttonSpacing * 4) + (buttonWidth * 3), buttonTopSpacing, buttonWidth, buttonHeight);
		cust5.setBounds((buttonSpacing * 5) + (buttonWidth * 4), buttonTopSpacing, buttonWidth, buttonHeight);
		addCustomer.setBounds(625, 600, 225, 75);
		completeOrder.setBounds(900, 600, 225, 75);
		status.setBounds(625, 300, 500, 50);
		itemStatus.setBounds(625, 375, 500, 50);
		arrivalStatus.setBounds(625, 450, 500, 50);
		customerList.setBounds(50, 300, 500, 400);
		
		listpopup.add(deletecustomer);
		
		status.setFont(bfont);
		
		cust1.addActionListener(b1);
		cust2.addActionListener(b1);
		cust3.addActionListener(b1);
		cust4.addActionListener(b1);
		cust5.addActionListener(b1);
		addCustomer.addActionListener(b1);
		completeOrder.addActionListener(b1);
		deletecustomer.addActionListener(b1);
		customerList.addMouseListener(l1);
		this.addMouseListener(l1);
		
		add(status);
		add(cust1);
		add(cust2);
		add(cust3);
		add(cust4);
		add(cust5);
		add(addCustomer);
		add(completeOrder);
		add(customerList);
		add(listpopup);
		add(itemStatus);
		add(arrivalStatus);
	}
	
	public void updateCustomers()
	{
		aCustomers.removeAllElements();
		LinkedList<Customer> filCustomers = cc.getCustomerList();
		int size = 0;
		
		if(!filCustomers.isEmpty())
		{
			for(int i = 0; i < filCustomers.size(); i++)
			{
				if(filCustomers.get(i).isArrived() == false && filCustomers.get(i).getOrder() != null)
				{
					aCustomers.add(size, filCustomers.get(i));
					size++;
				}
			}
		}
	}
	
	public void statusUpdate(Order toUpdate)
	{
		String s1 = "Awaiting Shopper";
		//String s2 = "Shopper Gathering Groceries";
		String s3 = "Awaiting Pickup";
		String s4 = "Customer Waiting for Order";
		String s5 = "Order Late! Awaiting Shopper";
		
		if(toUpdate.getStatus().equals(s1))
		{
			toUpdate.setStatus(s5);
		}
		else if(toUpdate.getStatus().equals(s3))
		{
			toUpdate.setStatus(s4);
		}
	}
	
	class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Customer pickupLanes[] = cc.getSpaces();
			
			String name = "Empty";
			if(customerList.getSelectedValue() != null)
			{
				name = customerList.getSelectedValue().getName();
			}
			
			if(e.getSource().equals(cust1))
			{
				status.setText("Space #1: " + cust1.getText());
				if(pickupLanes[0] != null)
				{
					itemStatus.setText("Items: " + pickupLanes[0].getOrder().getItems());
					arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[0].getOrder().getPickupTime());
				}
				else
				{
					itemStatus.setText("Items: ");
					arrivalStatus.setText("Scheduled Arrival Time: ");
				}
				selected = 1;
			}
			else if(e.getSource().equals(cust2))
			{
				status.setText("Space #2: " + cust2.getText());
				if(pickupLanes[1] != null)
				{
					itemStatus.setText("Items: " + pickupLanes[1].getOrder().getItems());
					arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[1].getOrder().getPickupTime());
				}
				else
				{
					itemStatus.setText("Items: ");
					arrivalStatus.setText("Scheduled Arrival Time: ");
				}
				selected = 2;
			}
			else if(e.getSource().equals(cust3))
			{
				status.setText("Space #3: " + cust3.getText());
				if(pickupLanes[2] != null)
				{
					itemStatus.setText("Items: " + pickupLanes[2].getOrder().getItems());
					arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[2].getOrder().getPickupTime());
				}
				else
				{
					itemStatus.setText("Items: ");
					arrivalStatus.setText("Scheduled Arrival Time: ");
				}
				selected = 3;
			}
			else if(e.getSource().equals(cust4))
			{
				status.setText("Space #4: " + cust4.getText());
				if(pickupLanes[3] != null)
				{
					itemStatus.setText("Items: " + pickupLanes[3].getOrder().getItems());
					arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[3].getOrder().getPickupTime());
				}
				else
				{
					itemStatus.setText("Items: ");
					arrivalStatus.setText("Scheduled Arrival Time: ");
				}
				selected = 4;
			}
			else if(e.getSource().equals(cust5))
			{
				status.setText("Space #5: " + cust5.getText());
				if(pickupLanes[4] != null)
				{
					itemStatus.setText("Items: " + pickupLanes[4].getOrder().getItems());
					arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[4].getOrder().getPickupTime());
				}
				else
				{
					itemStatus.setText("Items: ");
					arrivalStatus.setText("Scheduled Arrival Time: ");
				}
				selected = 5;
			}
			else if(e.getSource().equals(deletecustomer)) //Note: Customer can't be deleted if already arrived because it wouldn't be on the JList
			{
				cc.getMc().getO1().removeOrder(customerList.getSelectedValue().getOrder());
				cc.removeCustomer(customerList.getSelectedValue());
			}
			else if(e.getSource().equals(addCustomer))
			{
				if(!name.equals("Empty"))
				{
					Customer isSelected = customerList.getSelectedValue();
					if(selected == 1 && pickupLanes[0] == null)
					{
						cust1.setText(name);
						status.setText("Space #1: " + name);
						pickupLanes[0] = isSelected;
						isSelected.setArrived(true);
						cc.setSpaces(pickupLanes);
						itemStatus.setText("Items: " + pickupLanes[0].getOrder().getItems());
						arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[0].getOrder().getPickupTime());
						if(isSelected.getOrder().isReady() == false)
						{
							Order toReplace = isSelected.getOrder();
							statusUpdate(toReplace);
							toReplace.setLate(true);
							cc.getMc().getO1().replaceOrder(isSelected.getOrder(), toReplace);
						}
					}
					else if(selected == 2 && pickupLanes[1] == null)
					{
						cust2.setText(name);
						status.setText("Space #2: " + name);
						pickupLanes[1] = isSelected;
						isSelected.setArrived(true);
						cc.setSpaces(pickupLanes);
						itemStatus.setText("Items: " + pickupLanes[1].getOrder().getItems());
						arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[1].getOrder().getPickupTime());
						if(isSelected.getOrder().isReady() == false)
						{
							Order toReplace = isSelected.getOrder();
							statusUpdate(toReplace);
							toReplace.setLate(true);
							cc.getMc().getO1().replaceOrder(isSelected.getOrder(), toReplace);
						}
					}
					else if(selected == 3 && pickupLanes[2] == null)
					{
						cust3.setText(name);
						status.setText("Space #3: " + name);
						pickupLanes[2] = isSelected;
						isSelected.setArrived(true);
						cc.setSpaces(pickupLanes);
						itemStatus.setText("Items: " + pickupLanes[2].getOrder().getItems());
						arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[2].getOrder().getPickupTime());
						if(isSelected.getOrder().isReady() == false)
						{
							Order toReplace = isSelected.getOrder();
							statusUpdate(toReplace);
							toReplace.setLate(true);
							cc.getMc().getO1().replaceOrder(isSelected.getOrder(), toReplace);
						}
					}
					else if(selected == 4 && pickupLanes[3] == null)
					{
						cust4.setText(name);
						status.setText("Space #4: " + name);
						pickupLanes[3] = isSelected;
						isSelected.setArrived(true);
						cc.setSpaces(pickupLanes);
						itemStatus.setText("Items: " + pickupLanes[3].getOrder().getItems());
						arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[3].getOrder().getPickupTime());
						if(isSelected.getOrder().isReady() == false)
						{
							Order toReplace = isSelected.getOrder();
							statusUpdate(toReplace);
							toReplace.setLate(true);
							cc.getMc().getO1().replaceOrder(isSelected.getOrder(), toReplace);
						}
					}
					else if(selected == 5 && pickupLanes[4] == null)
					{
						cust5.setText(name);
						status.setText("Space #5: " + name);
						pickupLanes[4] = isSelected;
						isSelected.setArrived(true);
						cc.setSpaces(pickupLanes);
						itemStatus.setText("Items: " + pickupLanes[4].getOrder().getItems());
						arrivalStatus.setText("Scheduled Arrival Time: " + pickupLanes[4].getOrder().getPickupTime());
						if(isSelected.getOrder().isReady() == false)
						{
							Order toReplace = isSelected.getOrder();
							statusUpdate(toReplace);
							toReplace.setLate(true);
							cc.getMc().getO1().replaceOrder(isSelected.getOrder(), toReplace);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Space #" + selected + " is already occupied.", "Info", JOptionPane.INFORMATION_MESSAGE); //Space already occupied
					}
					updateCustomers();
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "No customer selected.", "Info", JOptionPane.INFORMATION_MESSAGE); //Customer not selected
				}
			}
			else if(e.getSource().equals(completeOrder))
			{
				itemStatus.setText("Items: ");
				arrivalStatus.setText("Scheduled Arrival Time: ");
				if(selected == 1 && pickupLanes[0] != null)
				{
					cust1.setText("Empty");
					status.setText("Space #1: Empty");
					Customer toDelete = pickupLanes[0];
					cc.getMc().getO1().removeOrder(toDelete.getOrder());
					pickupLanes[0] = null;
					cc.removeCustomer(toDelete);
					cc.setSpaces(pickupLanes);
				}
				else if(selected == 2 && pickupLanes[1] != null)
				{
					cust2.setText("Empty");
					status.setText("Space #2: Empty");
					Customer toDelete = pickupLanes[1];
					cc.getMc().getO1().removeOrder(toDelete.getOrder());
					pickupLanes[1] = null;
					cc.removeCustomer(toDelete);
					cc.setSpaces(pickupLanes);
				}
				else if(selected == 3 && pickupLanes[2] != null)
				{
					cust3.setText("Empty");
					status.setText("Space #3: Empty");
					Customer toDelete = pickupLanes[2];
					cc.getMc().getO1().removeOrder(toDelete.getOrder());
					pickupLanes[2] = null;
					cc.removeCustomer(toDelete);
					cc.setSpaces(pickupLanes);
				}
				else if(selected == 4 && pickupLanes[3] != null)
				{
					cust4.setText("Empty");
					status.setText("Space #4: Empty");
					Customer toDelete = pickupLanes[3];
					cc.getMc().getO1().removeOrder(toDelete.getOrder());
					pickupLanes[3] = null;
					cc.removeCustomer(toDelete);
					cc.setSpaces(pickupLanes);
				}
				else if(selected == 5 && pickupLanes[4] != null)
				{
					cust5.setText("Empty");
					status.setText("Space #5: Empty");
					Customer toDelete = pickupLanes[4];
					cc.getMc().getO1().removeOrder(toDelete.getOrder());
					pickupLanes[4] = null;
					cc.removeCustomer(toDelete);
					cc.setSpaces(pickupLanes);
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "Space #" + selected + " is empty.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	class listListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource().equals(customerList) && (e.getButton() == MouseEvent.BUTTON3) && !customerList.isSelectionEmpty())
			{
				listpopup.show(getParent(), e.getX(), e.getY());
			}
			else
			{
				listpopup.setVisible(false);
			}
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			
		}	
	}
}
