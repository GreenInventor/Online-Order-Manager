package model;

import java.time.LocalTime;

/**
 * Order model
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 1.0
 * Mar 30, 2022
 */
public class Order
{
	/*
	 * Possible status:
	 * Awaiting Shopper
	 * Shopper Gathering Groceries
	 * Awaiting Pickup
	 * Customer Waiting for Order
	 * Order Late!
	 */
	private String status;
	private String s1 = "Awaiting Shopper";
	
	private boolean ready;
	private boolean late;
	
	private int items;
	private LocalTime pickupTime;
	
	private Customer customer;
	private Shopper shopper;
	
	/**
	 * Constructor for orders
	 * @param customer
	 * @param items
	 * @param pickupTime
	 */
	public Order(Customer customer, int items, LocalTime pickupTime)
	{
		this.customer = customer;
		this.items = items;
		this.pickupTime = pickupTime;
		status = s1;
		ready = false;
		late = false;
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @return the ready
	 */
	public boolean isReady()
	{
		return ready;
	}

	/**
	 * @param ready the ready to set
	 */
	public void setReady(boolean ready)
	{
		this.ready = ready;
	}
	
	/**
	 * @return the late
	 */
	public boolean isLate()
	{
		return late;
	}

	/**
	 * @param late the late to set
	 */
	public void setLate(boolean late)
	{
		this.late = late;
	}

	/**
	 * @return the items
	 */
	public int getItems()
	{
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(int items)
	{
		this.items = items;
	}

	/**
	 * @return the pickupTime
	 */
	public LocalTime getPickupTime()
	{
		return pickupTime;
	}

	/**
	 * @param pickupTime the pickupTime to set
	 */
	public void setPickupTime(LocalTime pickupTime)
	{
		this.pickupTime = pickupTime;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer()
	{
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	/**
	 * @return the shopper
	 */
	public Shopper getShopper()
	{
		return shopper;
	}

	/**
	 * @param shopper the shopper to set
	 */
	public void setShopper(Shopper shopper)
	{
		this.shopper = shopper;
	}

	@Override
	public String toString()
	{
		if(customer == null)
		{
			return "New Customer" + " at " + pickupTime + " [Status = " + status + ", Ready = " + ready + ", Items = " + items + "]";
		}
		return customer.getName() + " at " + pickupTime + " [Status = " + status + ", Ready = " + ready + ", Items = " + items + "]";
	}
}
