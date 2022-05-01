package controller;

import java.util.LinkedList;

import javax.swing.JPanel;

import model.Customer;
import view.CustomersJPanel;

/**
 * CustomerController manages customers
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version
 * Mar 30, 2022
 */
public class CustomerController
{
	private final int STARTSPACES = 5;
	private LinkedList<Customer> customerList = new LinkedList<Customer>(); //All customers including those that haven't arrived yet
	
	//Data Structure #3: Array
	private Customer[] spaces = new Customer[STARTSPACES]; //Only customers that have arrived
	
	//Main Controller
	private MasterController mc;
	
	//View
	private JPanel lPanel = new CustomersJPanel(this);
	
	/**
	 * Default constructor, must have MasterController so all other parts of program can be connected
	 * @param mc
	 */
	public CustomerController(MasterController mc)
	{
		this.mc = mc;
		mc.getCpanel().add(lPanel, "customers");
	}

	/**
	 * Add customer to list
	 * @param c
	 */
	public void addCustomer(Customer c)
	{
		customerList.add(c);
		((CustomersJPanel) lPanel).updateCustomers();
	}
	
	/**
	 * Remove customer from list
	 * @param c
	 */
	public void removeCustomer(Customer c)
	{
		customerList.remove(c);
		((CustomersJPanel) lPanel).updateCustomers();
		
	}
	
	/**
	 * Replace customer c1 with c2
	 * @param c1
	 * @param c2
	 */
	public void replaceCustomer(Customer c1, Customer c2)
	{
		if(c1.equals(c2)) 
		{
			return; //No need to replace if customers equal each other
		}
		for(int i = 0; i < customerList.size(); i++)
		{
			if(customerList.get(i).equals(c1))
			{
				customerList.set(i, c2);
				((CustomersJPanel) lPanel).updateCustomers();
				return;
			}
		}
	}

	/**
	 * @return the spaces
	 */
	public Customer[] getSpaces()
	{
		return spaces;
	}

	/**
	 * @param spaces the spaces to set
	 */
	public void setSpaces(Customer[] spaces)
	{
		this.spaces = spaces;
	}

	/**
	 * @return the customerList
	 */
	public LinkedList<Customer> getCustomerList()
	{
		return customerList;
	}

	/**
	 * @return the lPanel
	 */
	public JPanel getlPanel()
	{
		return lPanel;
	}

	/**
	 * @return the mc
	 */
	public MasterController getMc()
	{
		return mc;
	}
}
