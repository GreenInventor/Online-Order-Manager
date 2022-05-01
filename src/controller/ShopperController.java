package controller;

import java.time.LocalTime;
import java.util.LinkedList;

import javax.swing.JPanel;

import model.Shopper;
import view.NewShopperJPanel;
import view.ShoppersJPanel;

/**
 * 
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Mar 30, 2022
 */
public class ShopperController
{
	//Data Structure #2: Linked List
	private LinkedList<Shopper> shopperList = new LinkedList<Shopper>();
	
	//Main Controller
	private MasterController mc;
	
	//View
	private JPanel spanel = new ShoppersJPanel(this);
	private JPanel nspanel = new NewShopperJPanel(this);
	
	/**
	 * Default Constructor, MasterController parameter required so other parts of program can be connected
	 * @param mc
	 */
	public ShopperController(MasterController mc)
	{
		this.mc = mc;
		mc.getCpanel().add(spanel, "shoppers");
		mc.getCpanel().add(nspanel, "newshopper");
		
		//Test Shoppers
		/*
		Shopper s1 = new Shopper("Tanner");
		Shopper s2 = new Shopper("Tyler");
		Shopper s3 = new Shopper("Brian");
		
		addShopper(s1);
		addShopper(s2);
		addShopper(s3);*/
	}

	/**
	 * Add shoppper to list
	 * @param shopper
	 */
	public void addShopper(Shopper shopper)
	{
		shopperList.add(shopper);
		((ShoppersJPanel) spanel).updateShoppers();
	}
	
	/**
	 * Remove shopper from list
	 * @param shopper
	 */
	public void removeShopper(Shopper shopper)
	{
		shopperList.remove(shopper);
		((ShoppersJPanel) spanel).updateShoppers();
	}
	
	/**
	 * Replace shopper s1 with s2
	 * @param s1
	 * @param s2
	 */
	public void replaceShopper(Shopper s1, Shopper s2)
	{
		if(s1.equals(s2))
		{
			return;
		}
		for(int i = 0; i < shopperList.size(); i++)
		{
			if(shopperList.get(i).equals(s1))
			{
				shopperList.set(i, s2);
				((ShoppersJPanel) spanel).updateShoppers();
				return;
			}
		}
	}
	
	/**
	 * Clock in or out shopper
	 * @param shopper
	 */
	public void clockInOutShopper(int index)
	{
		try
		{
			if(shopperList.get(index).isClockedIn())
			{
				shopperList.get(index).setClockedIn(false);
				shopperList.get(index).setShiftEnd(LocalTime.now());
			}
			else
			{
				shopperList.get(index).setClockedIn(true);
				shopperList.get(index).setShiftEnd(null);
				shopperList.get(index).setShiftStart(LocalTime.now());
			}
			((ShoppersJPanel) spanel).updateShoppers();
		}
		catch(IndexOutOfBoundsException i)
		{
			System.out.println("Index " + index + " out of bounds");
		}
	}

	/**
	 * @return the shopperList
	 */
	public LinkedList<Shopper> getShopperList()
	{
		return shopperList;
	}

	/**
	 * @return the mc
	 */
	public MasterController getMc() //Master Controller
	{
		return mc;
	}

	/**
	 * @return the nspanel
	 */
	public JPanel getNspanel() //New Shopper JPanel
	{
		return nspanel;
	}

	/**
	 * @return the spanel
	 */
	public JPanel getSpanel() //Shopper JPanel
	{
		return spanel;
	}
}
