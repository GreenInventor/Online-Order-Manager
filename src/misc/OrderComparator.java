package misc;

import java.time.LocalTime;
import java.util.Comparator;
import model.Order;

/**
 * The compare method for sorting the orders.
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Mar 30, 2022
 */
public class OrderComparator implements Comparator<Order>
{	
	/*
	private String s1 = "Awaiting Shopper";
	private String s2 = "Shopper Gathering Groceries";
	private String s3 = "Awaiting Pickup";
	private String s4 = "Customer Waiting for Order";
	private String s5 = "Order Late! Awaiting Shopper";*/
	
	private LocalTime t1;
	
	@Override
	public int compare(Order o1, Order o2)
	{	
		var priority = 0;
		t1 = LocalTime.now();
		if(!o1.isReady()) //If order isn't ready then increase priority
		{
			priority += 1;
		}
		if(!o2.isReady())
		{
			priority -= 1;
		}
		if(o1.getPickupTime().isBefore(t1)) //If order is late then increase priority
		{
			priority += 1;
		}
		if(o2.getPickupTime().isBefore(t1))
		{
			priority -= 1;
		}
		if(o1.isLate()) //If customer has arrived than increase priority
		{
			priority += 1;
		}
		if(o2.isLate())
		{
			priority -= 1;
		}
		if(o1.getPickupTime().isBefore(o2.getPickupTime())) //If pickup time is before other order than increase priority
		{
			priority += 1;
		}
		if(o2.getPickupTime().isBefore(o1.getPickupTime()))
		{
			priority -= 1;
		}
		//System.out.println("Priority: " + priority);
		return priority;
	}
}
