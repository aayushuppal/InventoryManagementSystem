package warehouseInventorySystem;

public class PickingResult {
	public boolean demandMet;
	public String Location;
	public int requestReturned;
	public int requestRemaining;
	public String prodName;
	
	public PickingResult (updateResult updater) {
		this.Location = updater.location;
		this.requestReturned = updater.amountProcessed;
		this.requestRemaining = updater.amountRemaining;
		this.demandMet = updater.updateStatus;
		this.prodName = updater.productName;
	}
}
