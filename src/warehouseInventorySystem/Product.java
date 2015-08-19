package warehouseInventorySystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import warehouseInventorySystem.WarehouseIMS.REQTYPE;


public class Product {
	private String productName;
	private String location;
	
	private int inventoryLevel;
	
	
	public Product (String prodName, String loc) {
		this.productName = prodName;
		this.location = loc;
		this.inventoryLevel = 0;
	}
	
	public Product (String prodName, String loc, int stock) {
		this.productName = prodName;
		this.location = loc;
		this.inventoryLevel = stock;
	}

	public String getProductName() {
		return productName;
	}
	
	public String getLocation() {
		return location;
	}

	public int getInventoryLevel() {
		return inventoryLevel;
	}
	
	
	public synchronized updateResult updateProduct(String productName, int amount,REQTYPE type) {
		
		updateResult intermediate = new updateResult();
		intermediate.productName = this.productName;
		
		switch (type) {
		case PICK:
			int tmp = this.inventoryLevel >=amount ? amount : this.inventoryLevel;
			if (tmp < amount) {
				BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Press Y key to continue . . . ");
				String input ="";
				try {
					input = inp.readLine();
				} catch (IOException e) {
					e.printStackTrace();
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
			intermediate.amountRemaining = 0; // because presently no max constraint
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
