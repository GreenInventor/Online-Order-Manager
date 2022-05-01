package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import controller.OrderController;
import model.Order;
import model.Shopper;

/**
 * OrderJPanel allows you to add, edit, delete, and manage orders.
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Apr 6, 2022
 */
public class OrdersJPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final int TIME = 2;
	private final int ITEMS = 3;
	
	private OrderController oc;
	private DefaultListModel<Order> aOrders = new DefaultListModel<Order>();
	
	private JButton addOrder = new JButton("New Order");
	private JButton assignShopper = new JButton("Assign Shopper");
	private JButton markFinished = new JButton("Mark Finished");
	private JList<Order> orderList = new JList<Order>(aOrders);
	private JScrollPane orderBar = new JScrollPane();
	private orderListener o1 = new orderListener();
	private listListener m1 = new listListener();
	private JPopupMenu listpopup = new JPopupMenu();
	private JMenuItem editorder = new JMenuItem("Edit Order");
	private JMenuItem deleteorder = new JMenuItem("Cancel Order");
	
	private int sortType = 1;
	
	/** 
	 * @param oc
	 */
	public OrdersJPanel(OrderController oc)
	{
		super();
		this.oc = oc;
		
		setLayout(null);
		orderBar.setViewportView(orderList);
		orderList.setLayoutOrientation(JList.VERTICAL);
		
		orderList.setBounds(50, 50, 800, 650);
		addOrder.setBounds(900, 50, 225, 75);
		assignShopper.setBounds(900, 150, 225, 75);
		markFinished.setBounds(900, 250, 225, 75);
		
		addOrder.addActionListener(o1);
		editorder.addActionListener(o1);
		deleteorder.addActionListener(o1);
		assignShopper.addActionListener(o1);
		markFinished.addActionListener(o1);
		orderList.addMouseListener(m1);
		this.addMouseListener(m1);
		
		listpopup.add(editorder);
		listpopup.add(deleteorder);
		
		add(orderList);
		add(addOrder);
		add(assignShopper);
		add(markFinished);
		add(listpopup);
	}
	
	public void updateOrders()
	{
		if(sortType == TIME)
		{
			aOrders.removeAllElements();
			aOrders.addAll(oc.sortOrderQueue(TIME));
		}
		else if(sortType == ITEMS)
		{
			aOrders.removeAllElements();
			aOrders.addAll(oc.sortOrderQueue(ITEMS));
		}
		else
		{
			aOrders.removeAllElements();
			aOrders.addAll(oc.getOrderQueue().getOrderList());
		}
	}
	
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(int sortType)
	{
		this.sortType = sortType;
	}
	
	class orderListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(addOrder))
			{
				oc.getMc().changeSlide("neworder", "New Order");
			}
			else if(e.getSource().equals(editorder))
			{
				oc.getMc().changeSlide("neworder", "Edit Order");
				((NewOrderJPanel) oc.getNopanel()).editOrder(orderList.getSelectedValue());
			}
			else if(e.getSource().equals(deleteorder))
			{
				oc.getMc().getC1().removeCustomer(orderList.getSelectedValue().getCustomer());
				oc.removeOrder(orderList.getSelectedValue());
			}
			else if(e.getSource().equals(assignShopper))
			{
				oc.getMc().changeSlide("assignshopper", "Assign Shopper");
				((AssignShopperJPanel) oc.getMc().getAsPanel()).changeBackMode(1);
				((AssignShopperJPanel) oc.getMc().getAsPanel()).updateAll();
			}
			else if(e.getSource().equals(markFinished))
			{
				if(!orderList.isSelectionEmpty())
				{
					Order rep = orderList.getSelectedValue();
					
					if(rep.isReady())
					{
						rep.setReady(false);
						if(rep.isLate())
						{
							rep.setStatus("Order late! Awaiting Shopper");
						}
						else
						{
							rep.setStatus("Awaiting Shopper");
						}
						rep.setShopper(null);
					}
					else if(orderList.getSelectedValue().getShopper() == null)
					{
						rep.setReady(true);
						if(rep.isLate())
						{
							rep.setStatus("Customer Waiting for Order");
						}
						else
						{
							rep.setStatus("Awaiting Pickup");
						}
					}
					else
					{
						Shopper srep = orderList.getSelectedValue().getShopper();
						Shopper srep2 = orderList.getSelectedValue().getShopper();
						rep.setReady(true);
						if(rep.isLate())
						{
							rep.setStatus("Customer Waiting for Order");
						}
						else
						{
							rep.setStatus("Awaiting Pickup");
						}
						srep2.setOrder(null);
						oc.getMc().getS1().replaceShopper(srep, srep2);
					}
					
					oc.replaceOrder(orderList.getSelectedValue(), rep);
					oc.getOrderQueue().prioritize(orderList.getSelectedIndex());
					updateOrders();
				}
			}
		}
	}

	class listListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource().equals(orderList) && (e.getButton() == MouseEvent.BUTTON3) && !orderList.isSelectionEmpty())
			{
				if(!orderList.getSelectedValue().getCustomer().isArrived() || !orderList.getSelectedValue().isLate())
				{
					listpopup.show(getParent(), e.getX(), e.getY());
				}
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
