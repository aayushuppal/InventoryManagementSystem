package warehouseInventorySystem;

import java.io.IOException;

import warehouseInventorySystem.WarehouseIMS.REQTYPE;
import static warehouseInventorySystem.WarehouseUI.inputReader;



public class Product {
	private String productId;
	private String location;
	
	private int inventoryLevel;
	
	
	public Product (String prodId, String loc) {
		this.productId = prodId;
		this.location = loc;
		this.inventoryLevel = 0;
	}
	
	public Product (String prodId, String loc, int stock) {
		this.productId = prodId;
		this.location = loc;
		this.inventoryLevel = stock;
	}

	public String getProductId() {
		return productId;
	}
	
	public String getLocation() {
		return location;
	}

	public int getInventoryLevel() {
		return inventoryLevel;
	}
	
	/**
	 * this synchronized implementation of the instance method on a product object ensures correct update and return values
	 */
	public synchronized InterimUpdate updateProduct(int amount,REQTYPE type) {
		
		InterimUpdate intermediate = new InterimUpdate();
		intermediate.productId = productId;
		
		switch (type) {
		case PICK:
			int tmp = inventoryLevel >=amount ? amount : inventoryLevel;
			if (tmp < amount) {
				System.out.println("only "+tmp+" available out of requested "+amount+"Press Y to pick this amount:");
				String input ="";
				try {
					input = inputReader.readLine();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
				if (input.equals("Y")) {
					intermediate.amountProcessed = tmp;
				} else {
					intermediate.amountProcessed = 0;
				}
			} else {
				intermediate.amountProcessed = tmp;
			}
			
			this.inventoryLevel = this.inventoryLevel - intermediate.amountProcessed;
			intermediate.amountRemaining = amount - intermediate.amountProcessed;
			intermediate.location = this.location;
			intermediate.updateStatus = intermediate.amountProcessed != 0 ? true : false;
			break;
			
		case RESTOCK:
			intermediate.amountProcessed = amount;
			this.inventoryLevel = this.inventoryLevel + intermediate.amountProcessed;
			intermediate.amountRemaining = 0; // because presently no max constraint to stock product
			intermediate.location = this.location;
			intermediate.updateStatus = true;
			break;
				
		default:
			intermediate.updateStatus = false;
			break;
		}
		
		return intermediate;
	}
	
	

}
