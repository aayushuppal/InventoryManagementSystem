package warehouseInventorySystem;

public class RestockingResult {
	public boolean restockDone;
	public String Location;
	public int stockDone;
	public int stockRemaining;
	public String prodName;
	
	public RestockingResult(updateResult interim) {
		this.restockDone = interim.updateStatus;
		this.Location = interim.location;
		this.stockDone = interim.amountProcessed;
		this.stockRemaining = interim.amountRemaining;
		this.prodName = interim.productName;
	}
}
