package eshop;



/**
 * 
 * @author Michael Winkler & Ido Nidbach
 * @date 23/07/20
 *
 */

public class Cart {

	/*
	 * Instance Variables:
	 */

	private static int currentID = 1000;
	private final int id;
	private int productCount;
	private Product[] products;
	private Customer customer;
	private Shop shop;

	/**
	 * @category Constructor
	 * @description This Constructor is used to create a cart with an id that start
	 *              at 1000, with an array with a few products from the shop in
	 *              order to conduct tests.
	 */

	public Cart() {
		this.id = currentID++;
		this.setProducts();
	}

	public Cart(Shop shop) {
		this.id = currentID++;
		this.setProducts();
		this.setShop(shop);
	}

	/**
	 * @category Getters
	 * @return This method will return the cart's ID.
	 */

	public int getCartID() {
		return this.id;
	}

	/**
	 * @category Getters
	 * @return This method will return the products (as objects) that are currently
	 *         in the cart.
	 */

	public Product[] getProducts() {
		return products;
	}

	/**
	 * @category Getters
	 * @return This method will return the customer (as an object) that is holding
	 *         this cart.
	 */

	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @category Getters
	 * @return will returns the cart's current shop.
	 */

	public Shop getShop() {
		return this.shop;
	}

	/**
	 * @category Setters
	 * @description This method is used assign the carts to the shop.
	 * @param shop - Represent's the current shop the cart belongs to.
	 */

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	/**
	 * @category Setters
	 * @description This method is used to create an empty array of product objects;
	 */

	public void setProducts() {
		this.products = new Product[100];
	}

	/**
	 * @category Setters
	 * @description This method is used to assign a customer to the cart.
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isAvailable() {

		if (this.getCustomer() == null) {

			return true;
		}

		return false;
	}

	/**
	 * @category Cart actions
	 * @description This method will iterate over the array of products to see how
	 *              many the customers has bought.
	 * @return - this method will return the number of products the customer bought.
	 */

	public int getProductCount() {
		int count = 0;
		for (int i = 0; i < this.getProducts().length; i++) {
			if (this.getProducts()[i] != null) {
				count++;
			}

		}
		this.productCount = count;
		return productCount;
	}
	
	/**
	 * @category Cart actions
	 * @description this method is used to add a new product to the shopping cart.
	 * @param productName - Represents the name of the product we want to add to the
	 *                    cart.
	 */
	public int addProduct(String productName) {

		for (int i = 0; i < this.getProducts().length; i++) {
			if (this.getProducts()[i] != null && i == this.getProducts().length - 1) {
				return 2;
			}
			if (this.getProducts()[i] == null) {
				int productIndex = this.shop.findProductIndex(productName);
				this.getProducts()[i] = this.shop.getProducts()[productIndex];
				this.fixProductArray();
				return 1;
			}

		}

		return 0;

	}
	
	public int addProductByIndex(int index) {
		for (int i = 0; i < this.getProducts().length; i++) {
			if (this.getProducts()[i] != null && i == this.getProducts().length - 1) {
				return 2;
			}
			if (this.getProducts()[i] == null) {
				
				this.getProducts()[i] = this.shop.getProducts()[index-1];
				this.fixProductArray();
				return 1;
			}

		}

		return 0;
	}
	/**
	 * @category Cart actions
	 * @description This method is used to remove a product from the shop.
	 * @param productName - Represents the product the customer would want to
	 *                    remove.
	 * @return This method will return 1 if it has succeed to remove a product, 0 if
	 *         not.
	 */

	public int removeProduct(String productName) {

		for (int i = 0; i < this.getProductCount(); i++) {
			if (this.getProducts()[i] != null) {
				if (this.getProducts()[i].getProductName().equalsIgnoreCase(productName)) {
					this.getProducts()[i] = null;
					this.fixProductArray();
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * @category Cart actions
	 * @description This method is used to clear the cart of shopping products.
	 */

	public void emptyCart() {
		this.products = new Product[100];
	}

	/**
	 * @category Cart actions
	 * @description This method is used to calculate how much the customer has saved
	 *              until now.
	 * @return This method will a double that represent's the saving of the
	 *         customer.
	 */

	private double calculateSavings() {
		double savings = 0;
		double priceAfterDiscount = this.calculateCartValue();
		for (int i = 0; i < this.getProductCount(); i++) {
			savings += this.getProducts()[i].getProductPrice();
		}
		savings -= priceAfterDiscount;
		return savings;
	}

	/**
	 * @category Cart actions
	 * @description This method is used to calculate how much costs all the product
	 *              in the customer's shopping cart.
	 * @return This method will return the shopping's cart value. (price after
	 *         discounts).
	 */

	public double calculateCartValue() {
		double value = 0;
		double discount = 0;
		if (this.getProductCount() == 0) {
			return 0;
		} else {
			for (int i = 0; i <= this.getProductCount(); i++) {
				if (this.getProducts()[i] != null) {
					// this calculate the discount :
					discount = this.getProducts()[i].getProductPrice() * this.getProducts()[i].getDiscountPercent();
					// this subtracts from the real price the discount to get the price after
					// discount.
					value += (this.getProducts()[i].getProductPrice() - discount);
				}
			}
		}

		return value;

	}

	/**
	 * @category Cart actions
	 * @description This method is used to show the current products that are inside
	 *              the cart, the cart value and the customer saving on this
	 *              purchase.
	 * @return This method returns a String that will show all the cart details
	 *         mentioned at the description.
	 */

	public String showCartContent() {
		String cartContent = "";
		for (int i = 0; i <= this.getProductCount(); i++) {
			if (this.getProducts()[i] != null) {
				cartContent += i + 1 + "." + this.getProducts()[i].getProductName() + "\n";
			} else {
				cartContent += "\n";
			}

		}

		cartContent += "\nCurrent cart value : " + String.format("%.2f", this.calculateCartValue())
				+ "\nUntil now you have saved: " + String.format("%.2f", this.calculateSavings());
		return cartContent;
	}

	/**
	 * @category Product Actions
	 * @description This private method is used to sort the product array each time
	 *              we add or remove a product using bubble sort.
	 */
	private void fixProductArray() {
		int length = this.getProducts().length;

		for (int i = 0; i < length; i++) {
			if (products[i] == null) {
				for (int j = i; j < length; j++) {
					if (products[j] != null) {
						Product tmp = products[j];
						products[j] = null;
						products[i] = tmp;
						break;
					}
				}
			} else {
				continue;
			}
		}
	}

}