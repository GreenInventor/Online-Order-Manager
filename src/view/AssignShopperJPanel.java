package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.OrderController;
import controller.ShopperController;
import model.Order;
import model.Shopper;

/**
 * AssignShopperJPanel allows you to assign shoppers to orders
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Apr 6, 2022
 */
public class AssignShopperJPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private ShopperController sc;
	private OrderController oc;
	private DefaultListModel<Order> avOrders = new DefaultListModel<Order>();
	private DefaultListModel<Shopper> avShoppers = new DefaultListModel<Shopper>();
	
	private JButton back = new JButton("Back");
	private JButton confirm = new JButton("Assign");
	private JList<Order> orderList = new JList<Order>(avOrders);
	private JList<Shopper> shopperList = new JList<Shopper>(avShoppers);
	private JScrollPane orderBar = new JScrollPane();
	private JScrollPane shopperBar = new JScrollPane();
	private buttonListener b1 = new buttonListener();
	
	private int source = 1;
	
	/**
	 * Default Constructor, must have ShopperController and OrderController to connect to rest of program
	 * @param sc
	 * @param oc
	 */
	public AssignShopperJPanel(ShopperController sc, OrderController oc)
	{
		super();
		this.sc = sc;
		this.oc = oc;
		
		setLayout(null);
		orderBar.setViewportView(orderList);
		orderList.setLayoutOrientation(JList.VERTICAL);
		shopperBar.setViewportView(shopperList);
		shopperList.setLayoutOrientation(JList.VERTICAL);
		
		orderList.setBounds(50, 50, 500, 300);
		shopperList.setBounds(600, 50, 500, 300);
		back.setBounds(300, 500, 225, 75);
		confirm.setBounds(650, 500, 225, 75);
		
		back.addActionListener(b1);
		confirm.addActionListener(b1);
		
		add(orderList);
		add(shopperList);
		add(back);
		add(confirm);
	}
	
	/**
	 * Update JLists
	 */
	public void updateAll()
	{
		avOrders.removeAllElements();
		avShoppers.removeAllElements();
		
		ArrayList<Order> oList = oc.getOrderQueue().getOrderList();
		LinkedList<Shopper> sList = sc.getShopperList();
		int size = 0;
		
		if(!oList.isEmpty())
		{
			for(int i = 0; i < oList.size(); i++)
			{
				if(oList.get(i).isReady() == false && oList.get(i).getShopper() == null)
				{
					avOrders.add(size, oList.get(i));
					size++;
				}
			}
		}
		if(!sList.isEmpty())
		{
			size = 0;
			for(int i = 0; i < sList.size(); i++)
			{
				if(sList.get(i).isClockedIn() == true && sList.get(i).getOrder() == null)
				{
					avShoppers.add(size, sList.get(i));
					size++;
				}
			}
		}
	}
	
	/**
	 * Change what back button does depending on which JPanel Assign Orders was pressed
	 * @param source
	 */
	public void changeBackMode(int source)
	{
		this.source = source;
	}
	
	class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(back))
			{
				if(source == 1)
				{
					oc.getMc().changeSlide("orders", "Orders");
				}
				else if(source == 2)
				{
					sc.getMc().changeSlide("shoppers", "Shoppers");
				}
				((OrdersJPanel) oc.getOpanel()).updateOrders();
				((ShoppersJPanel) sc.getSpanel()).updateShoppers();
			}
			else if(e.getSource().equals(confirm))
			{
				Order oRep = orderList.getSelectedValue();
				Shopper sRep = shopperList.getSelectedValue();
				oRep.setShopper(sRep);
				if(oRep.isLate())
				{
					oRep.setStatus("Order Late! " + sRep.getName() + " Gathering Groceries");
				}
				else
				{
					oRep.setStatus(sRep.getName() + " Gathering Groceries");
				}
				sRep.setOrder(oRep);
				oc.replaceOrder(orderList.getSelectedValue(), oRep);
				sc.replaceShopper(shopperList.getSelectedValue(), sRep);
				updateAll();
			}
		}
	}
}
