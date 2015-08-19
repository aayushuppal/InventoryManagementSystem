package warehouseInventorySystem;

import java.util.HashMap;


public class WarehouseIMS implements InventoryManagementSystem {
	public static enum REQTYPE { PICK, RESTOCK };
	public String warehouseId;
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
	
	
	public WarehouseIMS (String wareId) {
		this.warehouseId = wareId;
	}
	
	public Product getProduct (String prodName) throws UnknownProductException {
		if (productMap.get(prodName) == null) {
			throw new UnknownProductException (prodName);
		}
		return productMap.get(prodName);
	}
	
	public Product instantiateProduct (String prodName, String prodLocation) {
		if (productMap.containsKey(prodName)) {
			System.out.println(prodName+" ERROR: product already exists @ "+productMap.get(prodName).getLocation()+" use restock to add more" );
			return productMap.get(prodName);
		} else {
			Product temp =  new Product (prodName, prodLocation);
			productMap.put(prodName, temp);
			return temp;
		}
	}
	
	public Product instantiateProduct (String prodName, String prodLocation, int stock) {
		if (productMap.containsKey(prodName)) {
			System.out.println(prodName+" ERROR: product already exists @ "+productMap.get(prodName).getLocation()+" use restock to add more" );
			return productMap.get(prodName);
		} else {
			Product temp =  new Product (prodName, prodLocation, stock);
			productMap.put(prodName, temp);
			return temp;
		}
	}
	
	public void deleteProduct (String prodName) {
		try {
			if (this.productMap.containsKey(prodName)) {
				this.productMap.remove(prodName);
			} else {
				throw new UnknownProductException(prodName);
			}
		} catch (UnknownProductException e) {
			System.out.println(e.toString());
		}
		
	}
	
	
	
	public PickingResult pickProduct (String productName, int amount) {
		try {
			updateResult interim = getProduct(productName).updateProduct(productName, amount, REQTYPE.PICK);
			if (interim.updateStatus) {
				PickingResult pick = new PickingResult(interim);
				System.out.println("returning: "+pick.prodName
						+" demand met: "+pick.demandMet
						+" @ location: "+pick.Location
						+" request returned: "+pick.requestReturned
						+" request remaining: "+pick.requestRemaining);
				return pick;
			} else {
				throw new OutOfStockException (productName);
			}
		} catch (OutOfStockException e) {
			System.out.println(e.toString());
		} catch (UnknownProductException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public RestockingResult restockProduct (String productName, int amount)  {
		try {
			updateResult interim = getProduct(productName).updateProduct(productName, amount, REQTYPE.RESTOCK);
			if (interim.updateStatus) {
				return new RestockingResult(interim);
			} else {
				throw new UnknownProductException(productName);
			}
		} catch (OutOfStockException e) {
			System.out.println(e.toString());
		} catch (UnknownProductException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public void printWarehouseData() {
		System.out.println("Product Name"+"\tLocation"+"\tInventory Level\n");
		for (Product p:productMap.values()) {
			System.out.println(p.getProductName()+"\t\t"+p.getLocation()+"\t\t"+p.getInventoryLevel()+"\n");
		}
	}

}
