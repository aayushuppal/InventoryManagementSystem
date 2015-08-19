package warehouseInventorySystem;

public class PickingResult {
	public boolean demandMet;
	public String Location;
	public int requestReturned;
	public int requestRemaining;
	public String prodId;
	
	public PickingResult (InterimUpdate updater) {
		this.Location = updater.location;
		this.requestReturned = updater.amountProcessed;
		this.requestRemaining = updater.amountRemaining;
		this.demandMet = updater.updateStatus;
		this.prodId = updater.productId;
	}
}
