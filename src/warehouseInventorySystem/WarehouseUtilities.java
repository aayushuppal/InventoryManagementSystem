package warehouseInventorySystem;


public class WarehouseUtilities 
{
	public static String join(String... msgs)
	{
		StringBuilder sb = new StringBuilder();
		for (String m : msgs)
		{
			sb.append(m).append(", ");
		}
		
		return sb.toString();
	}	
}

