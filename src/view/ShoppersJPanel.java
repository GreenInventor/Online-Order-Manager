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

import controller.ShopperController;
import model.Order;
import model.Shopper;

/**
 * ShopperJPanel shows all shoppers and allows you to manage them
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Apr 6, 2022
 */
public class ShoppersJPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private ShopperController sc;
	private DefaultListModel<Shopper> aShoppers = new DefaultListModel<Shopper>();
	
	private JButton addShopper = new JButton("New Shopper");
	private JButton clockinout = new JButton("Clock In / Out");
	private JButton assignShopper = new JButton("Assign Shopper");
	private JButton shopperFinish = new JButton("Shopper Finished");
	private JList<Shopper> shopperList = new JList<Shopper>(aShoppers);
	private JScrollPane shopperBar = new JScrollPane();
	private JPopupMenu listpopup = new JPopupMenu();
	private JMenuItem editshopper = new JMenuItem("Edit Shopper");
	private JMenuItem deleteshopper = new JMenuItem("Delete Shopper");
	private shopperListener s1 = new shopperListener();
	private listListener m1 = new listListener();

	/**
	 * @param sc
	 */
	public ShoppersJPanel(ShopperController sc)
	{
		super();
		this.sc = sc;
		
		setLayout(null);
		shopperBar.setViewportView(shopperList);
		shopperList.setLayoutOrientation(JList.VERTICAL);
		
		shopperList.setBounds(50, 50, 800, 650);
		addShopper.setBounds(900, 50, 225, 75);
		clockinout.setBounds(900, 150, 225, 75);
		assignShopper.setBounds(900, 250, 225, 75);
		shopperFinish.setBounds(900, 350, 225, 75);
		
		addShopper.addActionListener(s1);
		clockinout.addActionListener(s1);
		assignShopper.addActionListener(s1);
		shopperFinish.addActionListener(s1);
		editshopper.addActionListener(s1);
		deleteshopper.addActionListener(s1);
		shopperList.addMouseListener(m1);
		this.addMouseListener(m1);
		
		listpopup.add(editshopper);
		listpopup.add(deleteshopper);
		
		add(shopperList);
		add(addShopper);
		add(clockinout);
		add(assignShopper);
		add(shopperFinish);
		add(listpopup);
	}
	
	/**
	 * Updates shoppers in JList after a change (Shopper added, edited, or removed)
	 */
	public void updateShoppers()
	{
		aShoppers.removeAllElements();
		aShoppers.addAll(sc.getShopperList());
	}
	
	/**
	 * ActionListener for ShoppersJPanel
	 * @author Tanner Patterson - tpatterson4@dmacc.edu
	 * @version 1.0
	 * Apr 27, 2022
	 */
	class shopperListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(addShopper))
			{
				sc.getMc().changeSlide("newshopper", "New Shopper");
			}
			else if(e.getSource().equals(editshopper))
			{
				sc.getMc().changeSlide("newshopper", "New Shopper");
				((NewShopperJPanel) sc.getNspanel()).editShopper(shopperList.getSelectedValue());
			}
			else if(e.getSource().equals(deleteshopper))
			{
				sc.removeShopper(shopperList.getSelectedValue());
			}
			else if(e.getSource().equals(clockinout))
			{
				if(!shopperList.isSelectionEmpty())
				{
					sc.clockInOutShopper(shopperList.getSelectedIndex());
				}
			}
			else if(e.getSource().equals(assignShopper))
			{
				sc.getMc().changeSlide("assignshopper", "Assign Shopper");
				((AssignShopperJPanel) sc.getMc().getAsPanel()).changeBackMode(2);
				((AssignShopperJPanel) sc.getMc().getAsPanel()).updateAll();
			}
			else if(e.getSource().equals(shopperFinish))
			{
				if(!shopperList.isSelectionEmpty())
				{
					Shopper rep = shopperList.getSelectedValue();
					Order orep = shopperList.getSelectedValue().getOrder();
					Order orep2 = shopperList.getSelectedValue().getOrder();
					rep.setOrder(null);
					orep2.setReady(true);
					orep2.setStatus("Awaiting Pickup"); //TODO what if customer is waiting for order?
					sc.replaceShopper(shopperList.getSelectedValue(), rep);
					sc.getMc().getO1().replaceOrder(orep, orep2);
					updateShoppers();
				}
			}
		}
	}
	
	/**
	 * MouseListener for ShopperJpanel
	 * @author Tanner Patterson - tpatterson4@dmacc.edu
	 * @version 1.0
	 * Apr 27, 2022
	 */
	class listListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource().equals(shopperList) && (e.getButton() == MouseEvent.BUTTON3) && !shopperList.isSelectionEmpty())
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
