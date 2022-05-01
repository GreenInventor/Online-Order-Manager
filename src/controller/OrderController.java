package controller;

import java.util.ArrayList;
import javax.swing.JPanel;
import misc.PQueue;
import misc.SelectionSort;
import model.Order;
import view.NewOrderJPanel;
import view.OrdersJPanel;

/**
 * Order controller manages all the orders
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Mar 30, 2022
 */
public class OrderController
{	
	//Data Structure #1: Priority Queue
	private PQueue orderQueue = new PQueue(); 
	
	//Sort: Selection Sort
	private SelectionSort orderSorter = new SelectionSort();

	//Main Controller
	private MasterController mc;
	
	//View
	private JPanel opanel = new OrdersJPanel(this);
	private JPanel nopanel = new NewOrderJPanel(this);
	
	/**
	 * Default constructor, requires MasterController so this class can connect to all other parts of program
	 * @param mc
	 */
	public OrderController(MasterController mc)
	{
		this.mc = mc;
		mc.getCpanel().add(opanel, "orders");
		mc.getCpanel().add(nopanel, "neworder");
		
		//Test Orders
		/*
		Order o1 = new Order(new Customer("test"), 10, LocalTime.now());
		Order o2 = new Order(new Customer("test2"), 20, LocalTime.now());
		Order o3 = new Order(new Customer("test3"), 30, LocalTime.now());
		
		addOrder(o1);
		addOrder(o2);
		addOrder(o3);
		*/
	}

	/**
	 * Add order and update the GUI to reflect this
	 * @param order
	 */
	public void addOrder(Order order)
	{
		orderQueue.add(order);
		((OrdersJPanel) opanel).updateOrders();
	}
	
	/**
	 * Removes order from orderQueue
	 * @param order
	 */
	public void removeOrder(Order order)
	{
		orderQueue.remove(order);
		((OrdersJPanel) opanel).updateOrders();
	}
	
	/**
	 * Replace order o1 with o2
	 * @param o1
	 * @param o2
	 */
	public void replaceOrder(Order o1, Order o2)
	{
		if(o1.equals(o2)) //Order doesn't have to be replaced new order is equal to old one
		{
			return;
		}
		orderQueue.replace(o1, o2);
		((OrdersJPanel) opanel).updateOrders();
	}

	/**
	 * @return the orderQueue
	 */
	public PQueue getOrderQueue()
	{
		return orderQueue;
	}
	
	/**
	 * Initiates selection sort on orders
	 * @param by - what to sort by
	 * @return the orders after sorting
	 */
	public ArrayList<Order> sortOrderQueue(int by)
	{
		if(orderQueue.getOrderList().isEmpty())
		{
			return orderQueue.getOrderList();
		}
		return orderSorter.sortOrders(getOrderQueue().getOrderList(), by);
	}

	/**
	 * @return the nopanel
	 */
	public JPanel getNopanel() //New Order JPanel
	{
		return nopanel;
	}
	
	/**
	 * @return the opanel
	 */
	public JPanel getOpanel() //Order JPanel
	{
		return opanel;
	}

	/**
	 * @return the mc
	 */
	public MasterController getMc() //MasterController
	{
		return mc;
	}
}
