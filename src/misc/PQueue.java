package misc;

import java.util.ArrayList;

import model.Order;

/**
 * Improved version of PQueue to work with final.
 * 
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 2.0
 * Apr 22, 2022
 */
public class PQueue 
{
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private OrderComparator ocomp = new OrderComparator();

	/**
	 * default constructor
	 */
	public PQueue() 
	{
		super();
	}
	
	/**
	 * @param orderList
	 */
	public PQueue(ArrayList<Order> orderList)
	{
		super();
		this.orderList = orderList;
	}

	/**
	 * Inserts Order e into Queue and sorts it by priority
	 * @param e - the order to add
	 */
	public void add(Order e)
	{
		orderList.add(e);
		prioritize(orderList.lastIndexOf(e));
	}
	
	/**
	 * Remove first instance of order from queue
	 * @param e - the order to remove
	 * @return the order that was removed or null if no jobs found
	 */
	public Order remove(Order rem)
	{
		for(Order e : orderList)
		{
			if(e.equals(rem))
			{
				orderList.remove(e);
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Replace order org with order n
	 * @param org
	 * @param n
	 */
	public void replace(Order org, Order n)
	{
		for(int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).equals(org))
			{
				orderList.set(i, n);
			}
		}
		prioritize(orderList.lastIndexOf(n));
	}
	
	/**
	 * Prioritize the orders
	 */
	public void prioritize(int index)
	{
		int currIndex, prevIndex;
		currIndex = index;
		if(currIndex - 1 >= 0)
		{
			prevIndex = currIndex - 1;
		
			Order curr = orderList.get(currIndex);
			Order prev = orderList.get(prevIndex);
			if(ocomp.compare(curr, prev) > 0)
			{
				//System.out.println("Priority is greater, moving up priority queue");
				while(ocomp.compare(curr, prev) > 0) //curr.getPriority() < prev.getPriority()
				{
					//System.out.println(curr.getPriority() + " > " + prev.getPriority() + "? " + (curr.getPriority() < prev.getPriority()));
					orderList.set(prevIndex, curr);
					orderList.set(currIndex, prev);
					
					//System.out.println((prevIndex - 1) + " >= 0? " + (prevIndex - 1 >= 0));
					if(prevIndex - 1 >= 0 && currIndex - 1 >= 0)
					{
						//System.out.println("If statement running");
						currIndex-=1;
						prevIndex-=1;
						curr = orderList.get(currIndex);
						prev = orderList.get(prevIndex);
					}
					else
					{
						return;
					}
				}
			}
			else if(ocomp.compare(curr, prev) < 0 && currIndex != orderList.size() - 1)
			{
				orderList.remove(currIndex);
				orderList.add(curr);
			}
		}
	}
	
	/**
	 * @return the orderList
	 */
	public ArrayList<Order> getOrderList()
	{
		return orderList;
	}
}
