package eshop;

import java.util.Random;



/**
 * 
 * @author Michael Winkler & Ido Nidbach
 * @date 23/07/20
 *
 */
public class DBHandler {

	/**
	 * Instance Variables :
	 * 
	 */

	private String products = "allspice basil bay-leaves black-pepper cardamom cayennne-pepper chili-pepper cinnamon cloves coriander cumin curry dill garam-masala ginger mint mustard nutmeg oregano red-pepper flakes paprika rosemary sage artichokes asparagus broccoli brussel-sprouts cabbage celery cucumber green-beans leafy-greens green-onions green-peppers snow peas spinach zucchini beets radishes red-pepper red-potatoes tomatoes carrots squash sweet-potatoes yellow-pepper cauliflower garlic leeks ginger mushrooms onions parsnips turnips eggplant red-cabbage avocados green-apples green-grapes melon kiwi limes green-pears berries cherries pink-grapefruit red-grapes watermelon apricots cantaloupe lemons mangoes nectarines oranges papayas peaches pineapple tangerines Chicken-Nuggets Pizza-Dough Tea Butter Cheese Eggs Milk Tortillas Yogurt Bacon Chicken Ostrich Sausage Flounder Haddock Halibut Red-snapper Salmon";
	private String[] arr = products.split(" ");
	private Cart[] cartArray = new Cart[100];
	private Customer[] customerArray = new Customer[100];
	Random rnd = new Random();

	/**
	 * @category Getters
	 * @return an empty customer array.
	 */

	public Customer[] getCustomerArray() {
		return this.customerArray;
	}

	/**
	 * @category Getters
	 * @return an empty Products array.
	 */

	public String[] getProductsArray() {
		return this.arr;
	}



	/**
	 * @category Customers
	 * @description This private method is used to generate a random customer name.
	 *              This method is used by the "generateCustomerArray" method.
	 * @return
	 */
	public String generateCustomerName() {
		String name = "";
		for (int i = 0; i < 5; i++) {
			char ch = (char) (rnd.nextInt(26) + 97);
			name += ch;
		}

		return name;
	}
	/**
	 * @category Customers
	 * @description This method is used to generate a random password. This method will be used in the random's shop constructor.
	 * @return
	 */
	public String generateCustomerPassword() {
		String password = "";
		for (int i = 0; i < 5; i++) {
			char ch = (char) (rnd.nextInt(26) + 97);
			password += ch;
			int randomDig = rnd.nextInt(10) + 1;
			password += randomDig;
		}

		return password;
	}
	
	/**
	 * @category Customers
	 * @description This method is used to generate an array of customers with
	 *              random user name and password.
	 * @return This method returns an array of customers the have username and
	 *         passwords.
	 */

	public Customer[] generateCustomerArray() {
		Customer[] tempCustomerArray = new Customer[100];
		// fills the customer array with random names.

		for (int i = 0; i < tempCustomerArray.length - 10; i++) {
			tempCustomerArray[i] = new Customer();
		}

		// checks if there is the same name twice.
		for (int i = 0; i < tempCustomerArray.length - 10; i++) {
			for (int j = 1; j < i; j++) {
				if (tempCustomerArray[i].getUserName().equals(tempCustomerArray[j].getUserName())) {
					tempCustomerArray[i] = new Customer();
				}
			}
		}

		return tempCustomerArray;
	}

	/**
	 * @category Carts
	 * @description This method is used to generate a cart array and assign each
	 *              cart the shop it belongs to.
	 * @param shop - The shop the the cart belongs to.
	 * @return This method return an array of carts with shops assigned to them.
	 */
	public Cart[] generateCartArray(Shop shop) {

		for (int i = 0; i < cartArray.length - 10; i++) {
			cartArray[i] = new Cart();
			cartArray[i].setShop(shop);
		}
		return cartArray;
	}

	/**
	 * @category Product
	 * @description This private method is used to generate a random price. This
	 *              method will be used in the "generateProductsArray" method.
	 * @return This method will return a random price.
	 */

	private double generateRandomPrice() {
		double price = (rnd.nextDouble() + 1) * 10;
		String str = String.format("%.2f", price);
		double finalPrice = Double.parseDouble(str);
		return finalPrice;
	}
	/**
	 * @category Products
	 * @description This method is used to generate an array of products and assign
	 *              to them a random price using the "generateRandomPrice" method.
	 * @return This methods returns an array of products assigned with prices.
	 */


	public Product[] generateProductsArray(Product[] products) {
		for (int i = 0; i < 40; i++) {
			products[i] = new Product(arr[i], this.generateRandomPrice());
		}
		return products;
	}

	/**
	 * @category Products
	 * @param products Represent's the product array we would like to fill with products
	 * @param productCount - Represents how much products we would like to insert to the shop.
	 * @return Will return a new array with products and prices.
	 */
	
	public Product[] fillEmptyProductArray(Product[] products, int productCount) {
		
		for(int i = 0; i < productCount; i++) {
			products[i] = new Product(this.arr[i], this.generateRandomPrice());
		}
	
		return products;
		
		
	}


}
