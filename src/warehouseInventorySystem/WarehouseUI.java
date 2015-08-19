package warehouseInventorySystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class WarehouseUI {
	static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main (String args[]) {
		final WarehouseIMS w1 = new WarehouseIMS("W1");
		
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
				int in = Integer.parseInt(inputReader.readLine());
				
				switch (in) {
				case 1:
					System.out.println("enter product: ");
					String prod = inputReader.readLine();
					System.out.println("enter amount: ");
					int amount = Integer.parseInt(inputReader.readLine());
					w1.pickProduct(prod,amount);
					break;
					
				case 2:
					System.out.println("enter product: ");
					String prodRe = inputReader.readLine();
					System.out.println("enter amount: ");
					int amountRe = Integer.parseInt(inputReader.readLine());
					w1.restockProduct(prodRe,amountRe);
					break;
					
				case 3:
					System.out.println("enter product: ");
					String prodIn = inputReader.readLine();
					System.out.println("enter location: ");
					String locIn = inputReader.readLine();
					System.out.println("enter initial stock: ");
					int stockIn = Integer.parseInt(inputReader.readLine());
					w1.instantiateProduct(prodIn, locIn, stockIn);
					break;

				case 4:
					System.out.println("enter product: ");
					String prodDel = inputReader.readLine();
					w1.deleteProduct(prodDel);
					break;
					
				case 5:
					w1.printWarehouseData();
					break;
					
				case 0:
					inputReader.close();
					System.exit(0);
					
				default:
					System.out.println("invalid option selected");
					break;
				}
			}
			
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		
	}
}
