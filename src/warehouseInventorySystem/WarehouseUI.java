package warehouseInventorySystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class WarehouseUI {
	
	
	public static void main (String args[]) {
		final WarehouseIMS w1 = new WarehouseIMS("W1");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		
		w1.instantiateProduct("tape", "A1", 10);
		w1.instantiateProduct("iron", "A2", 10);
		w1.instantiateProduct("gum", "A5", 5);
		w1.instantiateProduct("lamp", "A3", 10);
		w1.instantiateProduct("monitor", "A4", 10);
		
		try {
			
			while (true) {
				System.out.println("----------------------------");
				System.out.println("enter one of the options"
						+ "\n1 to pick product "
						+ "\n2 to restock product "
						+ "\n3 to add new product"
						+ "\n4 to remove existing product"
						+ "\n5 to see available stock"
						+ "\n0 to exit");
				int in = Integer.parseInt(input.readLine());
				
				switch (in) {
				case 1:
					System.out.println("enter product: ");
					String prod = input.readLine();
					System.out.println("enter amount: ");
					int amount = Integer.parseInt(input.readLine());
					w1.pickProduct(prod,amount);
					break;
					
				case 2:
					System.out.println("enter product: ");
					String prodRe = input.readLine();
					System.out.println("enter amount: ");
					int amountRe = Integer.parseInt(input.readLine());
					w1.restockProduct(prodRe,amountRe);
					break;
					
				case 3:
					System.out.println("enter product: ");
					String prodIn = input.readLine();
					System.out.println("enter location: ");
					String locIn = input.readLine();
					System.out.println("enter initial stock: ");
					int stockIn = Integer.parseInt(input.readLine());
					w1.instantiateProduct(prodIn, locIn, stockIn);
					break;

				case 4:
					System.out.println("enter product: ");
					String prodDel = input.readLine();
					w1.deleteProduct(prodDel);
					break;
					
				case 5:
					w1.printWarehouseData();
					break;
					
				case 0:
					System.exit(-1);
					
				default:
					System.out.println("invalid option selected");
					break;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
