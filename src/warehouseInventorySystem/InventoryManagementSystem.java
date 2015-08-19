package warehouseInventorySystem;
/**
 * Implementations of this interface must be thread-safe. Access to shared data must be
synchronized as methods of this
 * interface may be executed from multiple threads.
 * */
public interface InventoryManagementSystem {
 /**
 * Deduct 'amountToPick' of the given 'productId' from inventory.
 *
 * @param productId
 * @param amountToPick
 *
 * @return to be implemented
 */
 PickingResult pickProduct(String productId, int amountToPick);
 /**
 * Add 'amountToRestock' of the given productId to inventory.
 *
 * @param productId
 * @param amountToRestock
 *
 * @return to be implemented
 */
 RestockingResult restockProduct(String productId, int amountToRestock);
}
