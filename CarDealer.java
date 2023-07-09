package carproject;
class Billing {
	    private String model;
	    private int showRoomPrice;

	    public Billing(String model, int price) {
	        this.model = model;
	        this.showRoomPrice = price;
	    }

	    public String getModel() {
	        return model;
	    }

	    public int getPrice() {
	        return showRoomPrice;
	    }
	}

	class AdditionalAccessories {
	    private String name;
	    private int cost;

	    public AdditionalAccessories(String name, int cost) {
	        this.name = name;
	        this.cost = cost;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getCost() {
	        return cost;
	    }
	}

	class CarDealer {
	    private Billing[] carModels;
	    private AdditionalAccessories[] additionalFeatures;

	    public CarDealer() {
	        carModels = new Billing[]{
	                new Billing("Polo Trendline", 870000),
	                new Billing("Polo Highline", 1009000),
	                new Billing("Virtus Trendline", 1105000),
	                new Billing("Virtus Highline", 1308000),
	                new Billing("Taigun Trendline", 1489000),
	                new Billing("Taigun Highline", 1542000),
	                new Billing("Taigun Topline", 1771000)
	        };

	        additionalFeatures = new AdditionalAccessories[]{
	                new AdditionalAccessories("RTO", 113990),
	                new AdditionalAccessories("Insurance", 47300),
	                new AdditionalAccessories("TCS charges", 11000),
	                new AdditionalAccessories("Additional Accessories", 15000)
	        };
	    }

	    public String calculateTotalCost(String carModel, boolean needInsurance, boolean needAccessories, int dealerDiscount) {
	    	Billing selectedCar = null;
	        for (Billing car : carModels) {
	            if (car.getModel().equals(carModel)) {
	                selectedCar = car;
	                break;
	            }
	        }

	        if (selectedCar == null) {
	            return "Invalid car model selected.";
	        }

	        int totalCost = selectedCar.getPrice();

	        if (needInsurance) {
	            AdditionalAccessories insurance = additionalFeatures[1]; // Insurance is at index 1
	            totalCost += insurance.getCost();
	        }

	        if (needAccessories) {
	            AdditionalAccessories accessories = additionalFeatures[3]; // Additional Accessories is at index 3
	            totalCost += accessories.getCost();
	        }

	        if (needInsurance || needAccessories) {
	            if (dealerDiscount > 30000) {
	                totalCost -= 30000;
	            } else {
	                totalCost -= dealerDiscount;
	            }
	        } else {
	            if (dealerDiscount > 0) {
	                return "Cannot apply a discount without selecting insurance or additional accessories. Total cost: " + totalCost;
	            }
	        }

	        totalCost += additionalFeatures[0].getCost(); // RTO is at index 0
	        totalCost += additionalFeatures[2].getCost(); // TCS charges is at index 2

	        return "Total cost: " + totalCost;
	    }
	}




package carproject;


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        CarDealer carDealer = new CarDealer();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Select car model: ");
        String carModel = scanner.nextLine();

        System.out.print("Do you need Insurance (yes/no): ");
        String insuranceInput = scanner.nextLine();
        boolean needInsurance = insuranceInput.equalsIgnoreCase("yes");

        System.out.print("Do you need Additional Accessories (yes/no): ");
        String accessoriesInput = scanner.nextLine();
        boolean needAccessories = accessoriesInput.equalsIgnoreCase("yes");

        System.out.print("Dealer discount: ");
        int dealerDiscount = scanner.nextInt();

        scanner.close();

        String totalCost = carDealer.calculateTotalCost(carModel, needInsurance, needAccessories, dealerDiscount);
        System.out.println(totalCost);
    }
}
