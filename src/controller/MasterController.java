package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import view.AssignShopperJPanel;
import view.CustomersJPanel;
import view.OrdersJPanel;

/**
 * The class that controls the controllers and everything else. Connects every part of the application together.
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Apr 6, 2022
 */
public class MasterController
{	
	//Sorting
	private final int PRIORITY = 1;
	private final int TIME = 2;
	private final int ITEMS = 3;
	
	//View
	private JFrame frame = new JFrame();
	private JPanel cpanel = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private JMenuBar mMenu = new JMenuBar();
	private JMenu nav = new JMenu("Nav");
	private JMenuItem orders = new JMenuItem("Orders");
	private JMenuItem shoppers = new JMenuItem("Shoppers");
	private JMenuItem customers = new JMenuItem("Customers");
	private JMenu sortOrder = new JMenu("Sort");
	private JMenuItem priorityOrder = new JMenuItem("Priority");
	private JMenuItem timeOrder = new JMenuItem("Time");
	private JMenuItem itemOrder = new JMenuItem("Items");
	private ActionListener a1 = new navListener();
	
	//Sub-Controllers
	private CustomerController c1;
	private OrderController o1;
	private ShopperController s1;
	
	//Shared Views
	private JPanel asPanel; //This view has to be shared because it requires aspects from Orders and Shoppers to work
	
	public MasterController()
	{
		super();
		
		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(cpanel);
		frame.setJMenuBar(mMenu);
		mMenu.add(nav);
		nav.add(orders);
		nav.add(shoppers);
		nav.add(customers);
		mMenu.add(sortOrder);
		sortOrder.add(priorityOrder);
		sortOrder.add(timeOrder);
		sortOrder.add(itemOrder);
		orders.addActionListener(a1);
		shoppers.addActionListener(a1);
		customers.addActionListener(a1);
		priorityOrder.addActionListener(a1);
		timeOrder.addActionListener(a1);
		itemOrder.addActionListener(a1);
		
		cpanel.setLayout(cardLayout);
		
		c1 = new CustomerController(this);
		o1 = new OrderController(this);
		s1 = new ShopperController(this);
		
		asPanel = new AssignShopperJPanel(s1, o1);
		cpanel.add(asPanel, "assignshopper");
		
		cardLayout.show(cpanel, "orders");
		frame.setTitle("Orders");
		
		frame.setVisible(true);
	}
	
	/**
	 * Change the component in card layout
	 * @param name
	 * @param title
	 */
	public void changeSlide(String name, String title)
	{
		cardLayout.show(cpanel, name);
		frame.setTitle(title);
		if(title.equals("Orders"))
		{
			sortOrder.setVisible(true);
		}
		else
		{
			sortOrder.setVisible(false);
		}
	}
	
	/**
	 * @return the cpanel
	 */
	public JPanel getCpanel() //JPanel that cardlayout is on
	{
		return cpanel;
	}

	/**
	 * @return the asPanel
	 */
	public JPanel getAsPanel() //Assign Shopper JPanel
	{
		return asPanel;
	}

	/**
	 * @return the o1
	 */
	public OrderController getO1() //OrderController
	{
		return o1;
	}

	/**
	 * @return the s1
	 */
	public ShopperController getS1() //ShopperController
	{
		return s1;
	}
	
	/**
	 * @return the c1
	 */
	public CustomerController getC1() //CustomerController
	{
		return c1;
	}

	class navListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(orders)) //Go to orders JPanel
			{
				changeSlide("orders", "Orders");
			}
			else if(e.getSource().equals(shoppers)) //Go to shoppers JPanel
			{
				changeSlide("shoppers", "Shoppers");
			}
			else if(e.getSource().equals(customers)) //Go to customers JPanel
			{
				changeSlide("customers", "Customers");
				((CustomersJPanel) c1.getlPanel()).updateCustomers();
			}
			else if(e.getSource().equals(priorityOrder)) //Sort by priority
			{
				((OrdersJPanel) o1.getOpanel()).setSortType(PRIORITY);
				((OrdersJPanel) o1.getOpanel()).updateOrders();
			}
			else if(e.getSource().equals(timeOrder)) //Sort by time
			{
				((OrdersJPanel) o1.getOpanel()).setSortType(TIME);
				((OrdersJPanel) o1.getOpanel()).updateOrders();
			}
			else if(e.getSource().equals(itemOrder)) //Sort by items
			{
				((OrdersJPanel) o1.getOpanel()).setSortType(ITEMS);
				((OrdersJPanel) o1.getOpanel()).updateOrders();
			}
		}
	}
}


