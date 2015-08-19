package warehouseInventorySystem;

public class RestockingResult {
	public boolean restockStatus;
	public String Location;
	public int restockDone;
	public int restockRemaining;
	public String prodId;
	
	public RestockingResult(InterimUpdate interim) {
		this.restockStatus = interim.updateStatus;
		this.Location = interim.location;
		this.restockDone = interim.amountProcessed;
		this.restockRemaining = interim.amountRemaining;
		this.prodId = interim.productId;
	}
}
