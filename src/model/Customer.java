package model;

/**
 * Customer model
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Mar 30, 2022
 */
public class Customer
{
	private String name;
	private boolean arrived;
	private int location;
	private Order order;

	/**
	 * @param name
	 */
	public Customer(String name)
	{
		this.name = name;
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
	 * @return the arrived
	 */
	public boolean isArrived()
	{
		return arrived;
	}

	/**
	 * @param arrived the arrived to set
	 */
	public void setArrived(boolean arrived)
	{
		this.arrived = arrived;
	}

	/**
	 * @return the location
	 */
	public int getLocation()
	{
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(int location)
	{
		this.location = location;
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

	@Override
	public String toString()
	{
		return name;
	}
}
