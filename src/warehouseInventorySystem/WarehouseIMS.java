package warehouseInventorySystem;

import java.util.HashMap;


public class WarehouseIMS implements InventoryManagementSystem {
	private static WarehouseIMS singleton = new WarehouseIMS( );
	private WarehouseIMS() { }
	public static WarehouseIMS getInstance() {
	      return singleton;
	   }
	 
	public static enum REQTYPE { PICK, RESTOCK };
	private HashMap<String, Product> productMap = new HashMap<String, Product>();
	
	
	@SuppressWarnings("serial")
	public static class WarehouseException extends RuntimeException
	{
		public WarehouseException(String msg)
		{
			super(msg);
		}
	}

	@SuppressWarnings("serial")
	public static class UnknownProductException extends WarehouseException
	{
		public UnknownProductException(String p)
		{
			super("Request for product: "+p+" is invalid");
		}
	}
	
	@SuppressWarnings("serial")
	public static class OutOfStockException extends WarehouseException
	{
		public OutOfStockException(String p)
		{
			super("Product: "+p+" is out of stock");
		}
	}
	
	
	
	
	public Product getProduct (String prodId) throws UnknownProductException {
		if (productMap.get(prodId) == null) {
			throw new UnknownProductException (prodId);
		}
		return productMap.get(prodId);
	}
	
	/**
	 * 
	 * instantiates product with 0 inventory level
	 */
	public Product instantiateProduct (String prodId, String prodLocation) {
		if (productMap.containsKey(prodId)) {
			System.out.println(prodId+" ERROR: product already exists @ "+productMap.get(prodId).getLocation()+" use restock to add more" );
			return productMap.get(prodId);
		} else {
			Product temp =  new Product (prodId, prodLocation);
			productMap.put(prodId, temp);
			return temp;
		}
	}
	
	/**
	 * 
	 * instantiates product with given stock amount as inventory level
	 */
	public Product instantiateProduct (String prodId, String prodLocation, int stock) {
		if (productMap.containsKey(prodId)) {
			System.out.println(prodId+" ERROR: product already exists @ "+productMap.get(prodId).getLocation()+" use restock to add more" );
			return productMap.get(prodId);
		} else {
			Product temp =  new Product (prodId, prodLocation, stock);
			productMap.put(prodId, temp);
			return temp;
		}
	}
	
	public void deleteProduct (String prodId) {
		try {
			if (this.productMap.containsKey(prodId)) {
				this.productMap.remove(prodId);
			} else {
				throw new UnknownProductException(prodId);
			}
		} catch (UnknownProductException e) {
			System.out.println(e.toString());
		}
	}
	
	
	
	public PickingResult pickProduct (String productId, int amountToPick) {
		try {
			InterimUpdate interim = getProduct(productId).updateProduct(amountToPick, REQTYPE.PICK);
			if (interim.updateStatus) {
				PickingResult pick = new PickingResult(interim);
				System.out.println("returning: "+pick.prodId
						+" demand met: "+pick.demandMet
						+" @ location: "+pick.Location
						+" request processed: "+pick.requestReturned
						+" request remaining: "+pick.requestRemaining);
				return pick;
			} else {
				throw new OutOfStockException (productId);
			}
		} catch (OutOfStockException e) {
			System.out.println(e.toString());
		} catch (UnknownProductException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	public RestockingResult restockProduct (String productId, int amountToRestock)  {
		try {
			InterimUpdate interim = getProduct(productId).updateProduct(amountToRestock, REQTYPE.RESTOCK);
			if (interim.updateStatus) {
				RestockingResult restock = new RestockingResult(interim);
				System.out.println("returning: "+restock.prodId
						+" demand met: "+restock.restockStatus
						+" @ location: "+restock.Location
						+" request processed: "+restock.restockDone
						+" request remaining: "+restock.restockRemaining);
				return restock;
			} else {
				throw new UnknownProductException(productId);
			}
		} catch (OutOfStockException e) {
			System.out.println(e.toString());
		} catch (UnknownProductException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public void printWarehouseData() {
		System.out.println("Product Id"+"\tLocation"+"\tInventory Level\n");
		for (Product p:productMap.values()) {
			System.out.println(p.getProductId()+"\t\t"+p.getLocation()+"\t\t"+p.getInventoryLevel()+"\n");
		}
	}

}
