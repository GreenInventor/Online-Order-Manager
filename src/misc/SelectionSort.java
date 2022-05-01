package misc;

import java.util.ArrayList;

import model.Order;

/**
 * Improved selection sort to work with final
 * @author Tanner Patterson - tpatterson4@dmacc.edu
 * @version 2.0
 * Apr 23, 2022
 */
public class SelectionSort
{
	/**
	 * Selection Sort for Orders
	 * @param arr - the order ArrayList to sort
	 * @param sortBy - sort mode
	 * @return Sorted ArrayList
	 */
    public ArrayList<Order> sortOrders(ArrayList<Order> arr, int sortBy)
    {
    	var sort = new ArrayList<Order>();
    	sort.addAll(arr);
    	var n = sort.size();
  
        // One by one move boundary of unsorted subarray
        for(var i = 0; i < n - 1; i++)
        {
            // Find the minimum element in unsorted array
            var min_idx = i;
            for(var j = i + 1; j < n; j++)
            {
            	if(sortBy == 2)
            	{
            		if(sort.get(j).getPickupTime().isBefore(sort.get(min_idx).getPickupTime()))
                    {
                    	min_idx = j;
                    }
            	}
            	else if(sortBy == 3)
            	{
            		if(sort.get(j).getItems() < sort.get(min_idx).getItems())
            		{
            			min_idx = j;
            		}
            	}
            }
            
            // Swap the found minimum element with the first
            // element
            var temp = sort.get(min_idx);
            sort.set(min_idx, sort.get(i));
            sort.set(i, temp);
        }
		return sort;
    }
}
