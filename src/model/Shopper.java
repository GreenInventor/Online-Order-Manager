package model;

import java.time.LocalTime;

/**
 * Shopper model
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Mar 30, 2022
 */
public class Shopper
{
	private String name;
	private boolean clockedIn;
	
	private LocalTime shiftStart;
	private LocalTime shiftEnd;
	
	private Order order;	
	
	/**
	 * @param name
	 */
	public Shopper(String name)
	{
		super();
		this.name = name;
	}
	
	/**
	 * @param name
	 * @param clockedIn
	 * @param shiftStart
	 * @param shiftEnd
	 * @param order
	 */
	public Shopper(String name, boolean clockedIn, LocalTime shiftStart, LocalTime shiftEnd, Order order)
	{
		super();
		this.name = name;
		this.clockedIn = clockedIn;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.order = order;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the order
	 */
	public Order getOrder()
	{
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order)
	{
		this.order = order;
	}

	/**
	 * @return the clockedIn
	 */
	public boolean isClockedIn()
	{
		return clockedIn;
	}

	/**
	 * @param clockedIn the clockedIn to set
	 */
	public void setClockedIn(boolean clockedIn)
	{
		this.clockedIn = clockedIn;
	}

	/**
	 * @return the shiftStart
	 */
	public LocalTime getShiftStart()
	{
		return shiftStart;
	}

	/**
	 * @param shiftStart the shiftStart to set
	 */
	public void setShiftStart(LocalTime shiftStart)
	{
		this.shiftStart = shiftStart;
	}

	/**
	 * @return the shiftEnd
	 */
	public LocalTime getShiftEnd()
	{
		return shiftEnd;
	}

	/**
	 * @param shiftEnd the shiftEnd to set
	 */
	public void setShiftEnd(LocalTime shiftEnd)
	{
		this.shiftEnd = shiftEnd;
	}

	@Override
	public String toString()
	{
		if(order != null)
		{
			return name + " [Clocked In = " + clockedIn + ", Shift Start = "
					+ shiftStart + ", Shift End = " + shiftEnd + ", Order = " + order.getCustomer().getName() + "]";
		}
		else
		{
			return name + " [Clocked In = " + clockedIn + ", Shift Start = "
					+ shiftStart + ", Shift End = " + shiftEnd + "]";
		}
	}
}
